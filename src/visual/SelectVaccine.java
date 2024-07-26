package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import backend.classes.Disease;
import backend.classes.Vaccine;
import backend.controller.HospitalController;
import backend.interfaces.GeneralCallback;

public class SelectVaccine extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JLabel lblTitle = new JLabel("Vacunas");
    private JTable table;
    private VaccineTableModel modeloTable;
    private ArrayList<Vaccine> vacunasDelPaciente;
    private GeneralCallback callback;
    private ArrayList<String> selectedVacunasId = new ArrayList<>();
    private JButton okButton = new JButton("Seleccionar");

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            SelectVaccine dialog = new SelectVaccine(new ArrayList<>(), null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public SelectVaccine(ArrayList<Vaccine> vacunasDelPaciente, GeneralCallback callback) {
        this.callback = callback;
        this.vacunasDelPaciente = vacunasDelPaciente;

        setBounds(100, 100, 658, 612);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(Color.decode("#c0d0ef"));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setLocationRelativeTo(null);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.decode("#0f1c30"));
        headerPanel.setBounds(0, 0, 640, 50);
        headerPanel.setLayout(null);
        lblTitle.setBounds(12, 13, 616, 25);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD, 20));
        headerPanel.add(lblTitle);
        contentPanel.add(headerPanel);

        JPanel panel = new JPanel();
        panel.setBounds(10, 63, 618, 454);
        contentPanel.add(panel);
        panel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 618, 454);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scrollPane);

        modeloTable = new VaccineTableModel(new ArrayList<>(), vacunasDelPaciente);
        table = new JTable(modeloTable);
        table.setDefaultRenderer(Object.class, new DisabledRowTableCellRenderer());
        table.setBounds(0, 0, 618, 454);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                updateSelectedVaccines();
            }
        });
        scrollPane.setViewportView(table);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        okButton.setActionCommand("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (callback != null) {
                    callback.onGetObject(selectedVacunasId);
                }
                dispose();
            }
        });
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);

        renderVaccines();
        okButton.setEnabled(false);
    }

    private void updateSelectedVaccines() {
        int[] indexes = table.getSelectedRows();
        selectedVacunasId.clear();

        for (int i : indexes) {
            if (modeloTable.isRowEnabled(i)) {
                selectedVacunasId.add(table.getValueAt(i, 0).toString());
                System.out.println(table.getValueAt(i, 0).toString());
            }
        }
        okButton.setEnabled(!selectedVacunasId.isEmpty());
    }

    public void renderVaccines() {
        ArrayList<Vaccine> vaccines = HospitalController.getInstance().getVaccines();
        modeloTable = new VaccineTableModel(vaccines, vacunasDelPaciente);
        table.setModel(modeloTable);
        table.repaint();
    }

    private class VaccineTableModel extends DefaultTableModel {
        private ArrayList<Vaccine> vaccines;
        private ArrayList<Boolean> disabledRows;

        public VaccineTableModel(ArrayList<Vaccine> vaccines, ArrayList<Vaccine> patientVaccines) {
            super(new String[]{"ID", "Nombre", "Enfermedad", "Edad minima aplicable", "Edad maxima aplicabl"}, 0);
            this.vaccines = vaccines;
            this.disabledRows = new ArrayList<>(vaccines.size());

            for (Vaccine vaccine : vaccines) {
                Disease disease = HospitalController.getInstance().findDiseaseById(vaccine.getDiseaseId());
                addRow(new Object[]{vaccine.getId(),
                        vaccine.getName(),
                        disease.getName(),
                        vaccine.getMinAge(),
                        vaccine.getMaxAge()});
                boolean isDisabled = patientVaccines.stream()
                        .anyMatch(patientVaccine -> patientVaccine.getId().equals(vaccine.getId()));
                disabledRows.add(isDisabled);
            }
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

        public boolean isRowEnabled(int row) {
            return !disabledRows.get(row);
        }
    }

    private class DisabledRowTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            VaccineTableModel model = (VaccineTableModel) table.getModel();

            if (!model.isRowEnabled(row)) {
                c.setBackground(Color.LIGHT_GRAY);
                c.setForeground(Color.GRAY);
            } else if (isSelected) {
                c.setBackground(new Color(184, 207, 229)); // Color de selecci�n de JTable
                c.setForeground(table.getSelectionForeground());
            } else {
                c.setBackground(table.getBackground());
                c.setForeground(table.getForeground());
            }

            return c;
        }
    }
}