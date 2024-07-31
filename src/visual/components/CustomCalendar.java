package visual.components;

import javax.swing.*;
import backend.classes.*;
import backend.controller.HospitalController;
import visual.utils.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormatSymbols;
import java.time.LocalTime;
import java.util.*;
import java.util.List;

public class CustomCalendar extends JPanel {
    
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Custom Calendar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        
        HospitalController.getInstance().addPatient(new Patient("quesito", "quesito", "quesito", "quesito", null, 0, null, 1, 2));
        HospitalController.getInstance().addEmployee(new MedicalEmployee("quesitini", "quesito", "quesito", "quesito", null, 0F, null, null, null, null, null, 0F));
        HospitalController.getInstance().createQuery("hola", "quesito", "quesitini", serialVersionUID, new Date(), null, LocalTime.NOON, LocalTime.MIDNIGHT);

        // Add CustomCalendar panel
        CustomCalendar calendarPanel = new CustomCalendar(HospitalController.getInstance().findUserById("quesito"));
        frame.add(calendarPanel);

        // Make frame visible
        frame.setVisible(true);
    }

    private static final long serialVersionUID = 1L;
    private JPanel calendarPanel;
    private JLabel monthYearLabel;
    private JScrollPane scrollPane;
    private List<Query> queries;
    private List<Date> queryDates;

    private Calendar currentCalendar;

    public CustomCalendar(User currentUser) {
        super();

        queryDates = new ArrayList<>();
        queries = new ArrayList<>();

        if (currentUser != null) {
            if (currentUser instanceof Patient) {
                queries = HospitalController.getInstance().getPatientActiveQueries(currentUser.getId());
                populateQueryDates(queries);
            }
            if (currentUser instanceof MedicalEmployee) {
                queries = HospitalController.getInstance().getMedicalEmployeeActiveQueries(currentUser.getId());
                populateQueryDates(queries);
            }
        }

        setBorder(new CircularBorder(Color.WHITE, 0, 100));
        setLayout(new BorderLayout());

        currentCalendar = new GregorianCalendar();

        // Create JScrollPane
        scrollPane = new JScrollPane();
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        calendarPanel = new JPanel();
        calendarPanel.setLayout(new GridLayout(0, 7, 5, 5));

        scrollPane.setViewportView(calendarPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Create navigation panel with buttons and month-year label
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BorderLayout());

        // Previous button
        JButton prevButton = new JButton("Anterior");
        prevButton.addActionListener(e -> {
            currentCalendar.add(Calendar.MONTH, -1);
            updateCalendar();
        });
        navPanel.add(prevButton, BorderLayout.WEST);

        // Month-Year label
        monthYearLabel = new JLabel();
        monthYearLabel.setHorizontalAlignment(SwingConstants.CENTER);
        monthYearLabel.setFont(new Font("Serif", Font.BOLD, 16));
        navPanel.add(monthYearLabel, BorderLayout.CENTER);

        // Next button
        JButton nextButton = new JButton("Siguiente");
        nextButton.addActionListener(e -> {
            currentCalendar.add(Calendar.MONTH, 1);
            updateCalendar();
        });
        navPanel.add(nextButton, BorderLayout.EAST);

        add(navPanel, BorderLayout.NORTH);

        updateCalendar();
    }

    private void populateQueryDates(List<Query> queries) {
        queryDates.clear();
        for (Query query : queries) {
            queryDates.add(query.getDate());
        }
    }

    private void updateCalendar() {
        calendarPanel.removeAll();

        int month = currentCalendar.get(Calendar.MONTH);
        int year = currentCalendar.get(Calendar.YEAR);
        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int firstDayOfMonth = cal.get(Calendar.DAY_OF_WEEK);
        int daysInLastMonth = new GregorianCalendar(year, month - 1, 1).getActualMaximum(Calendar.DAY_OF_MONTH);

        // Update month-year label
        String[] monthNames = new DateFormatSymbols(new Locale("es")).getMonths();
        monthYearLabel.setText(monthNames[month].substring(0, 1).toUpperCase() + monthNames[month].substring(1) + " " + year);

        // Add day labels for the days of the week
        String[] dayNames = {"Dom", "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb"};
        for (String dayName : dayNames) {
            JLabel label = createWeekDays(dayName);
            calendarPanel.add(label);
        }

        int cont = 0;

        for (int i = firstDayOfMonth - 1; i > 0; i--) {
            JLabel label = createDayLabel(String.valueOf(daysInLastMonth - i + 1));
            label.setBackground(ColorPallete.mainColor_Dark.darker());
            label.setForeground(ColorPallete.mainColor_Light);
            calendarPanel.add(label);
            cont++;
        }

        for (int i = 1; i <= daysInMonth; i++) {
            JLabel label = createDayLabel(String.valueOf(i));
            if (isQueryScheduled(year, month, i)) {
            	final int ind = i;
                label.setBackground(Color.MAGENTA);
                label.setForeground(ColorPallete.mainColor_Light);
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        showQueryPopup(year, month, ind);
                    }
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        setCursor(Cursor.getDefaultCursor());
                    }
                });
            }
            calendarPanel.add(label);
            cont++;
        }

        for (int i = 1; cont % 7 != 0; i++, cont++) {
            JLabel label = createDayLabel(String.valueOf(i));
            label.setBackground(ColorPallete.mainColor_Dark.darker());
            label.setForeground(ColorPallete.mainColor_Light);
            calendarPanel.add(label);
        }

        calendarPanel.revalidate();
        calendarPanel.repaint();
    }

    private boolean isQueryScheduled(int year, int month, int day) {
        for (Date date : queryDates) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            if (cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) == month && cal.get(Calendar.DAY_OF_MONTH) == day) {
                return true;
            }
        }
        return false;
    }

    private void showQueryPopup(int year, int month, int day) {
        StringBuilder popupContent = new StringBuilder();
        popupContent.append("Citas para el: ").append(day).append(" ").append(new DateFormatSymbols(new Locale("es")).getMonths()[month]).append(" ").append(year).append(":\n\n");

        for (Query query : queries) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(query.getDate());
            if (cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) == month && cal.get(Calendar.DAY_OF_MONTH) == day) {
                popupContent.append("Query ID: ").append(query.getId()).append("\n");
                popupContent.append("Doctor: ").append(HospitalController.getInstance().findEmployeeById(query.getDoctorID()).getUserName()).append('\n');
                popupContent.append("Hora de Inicio: ").append(query.getStartingTime().toString()).append("\n");
                popupContent.append("\n");
            }
        }

        JOptionPane.showMessageDialog(this, popupContent.toString(), "Detalles", JOptionPane.INFORMATION_MESSAGE);
    }

    private JLabel createWeekDays(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setOpaque(true);
        label.setPreferredSize(new Dimension(40, 40));
        label.setBackground(ColorPallete.mainColor_Dark);
        label.setForeground(Color.WHITE);
        return label;
    }
    
    private JLabel createDayLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBorder(new CircularBorder(Color.WHITE, 0, 40));
        label.setPreferredSize(new Dimension(40, 40));
        label.setBackground(text.isEmpty() ? Color.LIGHT_GRAY : ColorPallete.mainColor);
        label.setForeground(Color.WHITE);
        return label;
    }
}
