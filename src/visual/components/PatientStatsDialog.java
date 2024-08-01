package visual.components;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import backend.controller.HospitalController;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;

import visual.utils.ColorPallete;
import backend.classes.Patient;

public class PatientStatsDialog extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 850;
    private static final int HEIGHT = 650;
    private JPanel chartPanelContainer;
    private CardLayout cardLayout;
    private List<Patient> patients;
    private int currentYear;

    public PatientStatsDialog(Frame parent, boolean modal) {
        super(parent, "Estadísticas de Pacientes", modal);
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(parent);

        // Initialize patient data
        patients = HospitalController.getInstance().getPatients();
        if (patients.isEmpty()) {
            generateTestData(); // Generate test data if no patients are available
        }

        // Create the container for the chart panels with CardLayout
        chartPanelContainer = new JPanel();
        cardLayout = new CardLayout();
        chartPanelContainer.setLayout(cardLayout);

        // Create initial chart panel for the current year
        currentYear = Calendar.getInstance().get(Calendar.YEAR);
        updateChartPanelForYear(currentYear);

        // Create navigation buttons
        JPanel buttonPanel = new JPanel();
        JButton prevYearButton = new JButton("Anterior Año");
        JButton nextYearButton = new JButton("Siguiente Año");

        prevYearButton.addActionListener(e -> navigateYear(-1));
        nextYearButton.addActionListener(e -> navigateYear(1));

        buttonPanel.add(prevYearButton);
        buttonPanel.add(nextYearButton);

        add(chartPanelContainer, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void navigateYear(int direction) {
        currentYear += direction;
        updateChartPanelForYear(currentYear);
        setTitle("Estadísticas de Pacientes - Año " + currentYear);
    }

    private void updateChartPanelForYear(int year) {
        // Check if the panel for the new year already exists
        String yearKey = Integer.toString(year);
        JPanel chartPanel = (JPanel) findChartPanel(yearKey);
        if (chartPanel == null) {
            chartPanel = createChartPanelForYear(year);
            chartPanel.setName(yearKey); // Set the name for the panel
            chartPanelContainer.add(chartPanel, yearKey);
        }

        cardLayout.show(chartPanelContainer, yearKey);
    }

    private Component findChartPanel(String yearKey) {
        for (Component comp : chartPanelContainer.getComponents()) {
            if (yearKey.equals(((JPanel) comp).getName())) {
                return comp;
            }
        }
        return null;
    }

    private JPanel createChartPanelForYear(int year) {
        JPanel panel = new JPanel(new BorderLayout());
        JFreeChart chart = createChart(createDatasetForYear(year));
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        panel.add(chartPanel, BorderLayout.CENTER);
        return panel;
    }

    private JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Pacientes Registrados Por Mes - " + currentYear,
                "Mes",
                "Número de Pacientes",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        // Get the plot and style it
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(ColorPallete.mainColor); // Fondo del gráfico
        plot.setDomainGridlinePaint(Color.BLUE); // Línea vertical de cuadrícula
        plot.setRangeGridlinePaint(Color.BLUE); // Línea horizontal de cuadrícula
        plot.setDomainGridlineStroke(new BasicStroke(1.0f));
        plot.setRangeGridlineStroke(new BasicStroke(1.0f));

        // Customize the renderer for the bars
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, ColorPallete.mainColor_Dark); // Color de las barras
        renderer.setBarPainter(new StandardBarPainter()); // Painter para un estilo de barra más limpio

        // Set the chart background color
        chart.setBackgroundPaint(ColorPallete.mainColor_Light);

        // Customize the range axis (Y-axis) to show discrete values
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setAutoTickUnitSelection(false);

        // Calculate the tick unit based on the maximum value
        int maxValue = getMaxValueFromDataset(dataset);
        rangeAxis.setTickUnit(createTickUnit(maxValue));

        return chart;
    }

    // Helper method to get the maximum value from the dataset
    private int getMaxValueFromDataset(CategoryDataset dataset) {
        int max = 0;
        for (int i = 0; i < dataset.getRowCount(); i++) {
            for (int j = 0; j < dataset.getColumnCount(); j++) {
                Number value = dataset.getValue(i, j);
                if (value != null) {
                    max = Math.max(max, value.intValue());
                }
            }
        }
        return max;
    }

    // Helper method to create a tick unit for the range axis
    private NumberTickUnit createTickUnit(int maxValue) {
        int baseTickUnit = 1;
        while (baseTickUnit < maxValue) {
            baseTickUnit *= 2;
        }
        return new NumberTickUnit(baseTickUnit);
    }


    private CategoryDataset createDatasetForYear(int year) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Count patients per month for the specified year
        Map<String, Integer> monthCounts = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        for (Patient patient : patients) {
            calendar.setTime(patient.getRegisterDate());
            int patientMonth = calendar.get(Calendar.MONTH) + 1; // Months are 0-based in Calendar
            int patientYear = calendar.get(Calendar.YEAR);
            if (patientYear == year) {
                String key = Integer.toString(patientMonth); // Key format: MM
                monthCounts.put(key, monthCounts.getOrDefault(key, 0) + 1);
            }
        }

        // Add data to dataset
        for (int month = 1; month <= 12; month++) {
            String monthKey = Integer.toString(month); // Format MM
            dataset.addValue(monthCounts.getOrDefault(monthKey, 0), "Patients", getMonthName(month));
        }

        return dataset;
    }

    private String getMonthName(int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month - 1);
        return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, new Locale("es"));
    }

    private void generateTestData() {
        // Generate test data for each month of the current year
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        patients.clear(); // Clear existing patients before adding test data
        for (int month = 1; month <= 12; month++) {
            Patient patient = new Patient("Nombre", "Apellido", "ID", "Direccion", null, 0, null, 1, 2);
            calendar.set(currentYear, month - 1, 1);
            patient.setRegisterDate(calendar.getTime());
            patients.add(patient);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            PatientStatsDialog dialog = new PatientStatsDialog(frame, true);
            dialog.setVisible(true);
        });
    }
}
