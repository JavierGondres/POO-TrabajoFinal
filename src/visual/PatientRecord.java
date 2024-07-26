package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import backend.classes.Disease;
import backend.classes.Patient;
import backend.controller.HospitalController;
import backend.interfaces.GeneralCallback;
import visual.components.CustomTextField;

import com.toedter.calendar.JDateChooser;

public class PatientRecord extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JLabel lblTitle = new JLabel("Historial Clinico");
    private CustomTextField textFieldName = new CustomTextField();
    private CustomTextField textFieldLastName = new CustomTextField();
    private CustomTextField textFieldPeso = new CustomTextField();
    private CustomTextField textFieldAltura = new CustomTextField();
    private JDateChooser dateChooser = new JDateChooser();
    private JButton cancelButton = new JButton("Cerrar");
    private JButton okButton = new JButton("Guardar");
    private JTable tableEnfermedadesActual;
    private DefaultTableModel modeloEnfermedadesActuales;
    private Patient patient;
    private backend.classes.Record patientRecord;
    private ArrayList<Disease> currentDiseases = new ArrayList<>();

    public static void main(String[] args) {
        try {
            PatientRecord dialog = new PatientRecord(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PatientRecord(Patient patient) {
        this.patient = patient;
        initializeComponents();
        initializeForm();
        setupListeners();
    }

    private void initializeComponents() {
        setBounds(100, 100, 658, 679);
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

        JLabel lblUsername = new JLabel("Nombre:");
        lblUsername.setForeground(Color.decode("#0f1c30"));
        lblUsername.setFont(lblUsername.getFont().deriveFont(Font.BOLD, 14));
        lblUsername.setBounds(12, 79, 85, 25);
        contentPanel.add(lblUsername);

        textFieldName.setEditable(false);
        textFieldName.setBounds(109, 81, 167, 22);
        contentPanel.add(textFieldName);
        textFieldName.setColumns(10);
        
        JLabel lblLastname = new JLabel("Apellidos:");
        lblLastname.setForeground(Color.decode("#0f1c30"));
        lblLastname.setFont(lblUsername.getFont().deriveFont(Font.BOLD, 14));
        lblLastname.setBounds(364, 77, 85, 25);
        contentPanel.add(lblLastname);

        textFieldLastName.setEditable(false);
        textFieldLastName.setColumns(10);
        textFieldLastName.setBounds(461, 79, 167, 22);
        contentPanel.add(textFieldLastName);

        JLabel lblPeso = new JLabel("Peso:");
        lblPeso.setForeground(Color.decode("#0f1c30"));
        lblPeso.setFont(lblPeso.getFont().deriveFont(Font.BOLD, 14));
        lblPeso.setBounds(12, 117, 85, 25);
        contentPanel.add(lblPeso);

        textFieldPeso.setEditable(false);
        textFieldPeso.setColumns(10);
        textFieldPeso.setBounds(109, 120, 167, 22);
        contentPanel.add(textFieldPeso);
        
        JLabel lblAltura = new JLabel("Altura:");
        lblAltura.setForeground(Color.decode("#0f1c30"));
        lblAltura.setFont(lblAltura.getFont().deriveFont(Font.BOLD, 14));
        lblAltura.setBounds(364, 115, 85, 25);
        contentPanel.add(lblAltura);

        textFieldAltura.setEditable(false);
        textFieldAltura.setColumns(10);
        textFieldAltura.setBounds(461, 118, 167, 22);
        contentPanel.add(textFieldAltura);
         
        JLabel lblEnfermedadesActuales = new JLabel("Enfermedades actuales:");
        lblEnfermedadesActuales.setForeground(Color.decode("#0f1c30"));
        lblEnfermedadesActuales.setFont(lblEnfermedadesActuales.getFont().deriveFont(Font.BOLD, 14));
        lblEnfermedadesActuales.setBounds(12, 166, 191, 25);
        contentPanel.add(lblEnfermedadesActuales);
        
        JPanel panelEnfermedadesActuales = new JPanel();
        panelEnfermedadesActuales.setBounds(12, 198, 616, 215);
        contentPanel.add(panelEnfermedadesActuales);
        panelEnfermedadesActuales.setLayout(null);
        
        JScrollPane scrollPaneEnfermedadesActuales = new JScrollPane();
        scrollPaneEnfermedadesActuales.setBounds(0, 0, 616, 215);
        scrollPaneEnfermedadesActuales.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelEnfermedadesActuales.add(scrollPaneEnfermedadesActuales);

        modeloEnfermedadesActuales = new DefaultTableModel(
            new String[]{"ID", "Nombre", "Es vacunable", "Prioridad"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableEnfermedadesActual = new JTable(modeloEnfermedadesActuales);
        tableEnfermedadesActual.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPaneEnfermedadesActuales.setViewportView(tableEnfermedadesActual);

        JLabel lblFecha = new JLabel("Ultima modificacion:");
        lblFecha.setForeground(Color.decode("#0f1c30"));
        lblFecha.setFont(lblFecha.getFont().deriveFont(Font.BOLD, 14));
        lblFecha.setBounds(12, 559, 146, 25);
        contentPanel.add(lblFecha);

        dateChooser.setBounds(170, 562, 167, 22);
        dateChooser.setEnabled(false);
        contentPanel.add(dateChooser);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        buttonPane.add(okButton);
        buttonPane.add(cancelButton);
        getRootPane().setDefaultButton(okButton);
    }

    private void initializeForm() {
        if (patient == null) return;
        patientRecord = HospitalController.getInstance().getRecord(patient.getId());
        textFieldName.setText(patient.getUserName());
        textFieldLastName.setText(patient.getLastName());
        textFieldAltura.setText(String.valueOf(patient.getHeight()));
        textFieldPeso.setText(String.valueOf(patient.getWeigth()));
        
        if (patientRecord != null) {
            dateChooser.setDate(patientRecord.getLastModification());
            currentDiseases = patientRecord.getDiseaseHistory();
            renderCurrentDiseases();
        }
    }

    private void setupListeners() {
        JButton btnAddEnfermedadActual = new JButton("Agregar");
        btnAddEnfermedadActual.setBounds(399, 167, 97, 25);
        contentPanel.add(btnAddEnfermedadActual);
        btnAddEnfermedadActual.addActionListener(e -> openSelectDiseaseDialog());

        JButton btnEliminarEnfermedadActual = new JButton("Eliminar");
        btnEliminarEnfermedadActual.setBounds(531, 167, 97, 25);
        contentPanel.add(btnEliminarEnfermedadActual);
        btnEliminarEnfermedadActual.addActionListener(e -> removeSelectedDisease());

        okButton.addActionListener(e -> saveRecord());
        cancelButton.addActionListener(e -> dispose());
    }

    private void openSelectDiseaseDialog() {
        SelectDisease selectD = new SelectDisease(currentDiseases, new GeneralCallback() {
            @Override
            public void onGetObject(Object object) {
                addSelectedDiseases(object);
            }
        });
        selectD.setModal(true);
        selectD.setVisible(true);
    }

    private void addSelectedDiseases(Object object) {
        ArrayList<String> idsOfEnfermedades = (ArrayList<String>) object;
        for (String id : idsOfEnfermedades) {
            Disease disease = HospitalController.getInstance().findDiseaseById(id);
            if (disease != null) {
                addRowCurrentDisease(disease);
                currentDiseases.add(disease);
            }
        }
    }

    private void removeSelectedDisease() {
        int selectedRow = tableEnfermedadesActual.getSelectedRow();
        if (selectedRow != -1) {
            String diseaseId = (String) modeloEnfermedadesActuales.getValueAt(selectedRow, 0);
            
            // Eliminar de la tabla
            modeloEnfermedadesActuales.removeRow(selectedRow);
            
            // Eliminar del ArrayList currentDiseases
            currentDiseases.removeIf(disease -> disease.getId().equals(diseaseId));
        }
    }

    private void renderCurrentDiseases() {
        modeloEnfermedadesActuales.setRowCount(0);
        for (Disease disease : patientRecord.getDiseaseHistory()) {
            addRowCurrentDisease(disease);
        }
    }

    private void addRowCurrentDisease(Disease disease) {
        modeloEnfermedadesActuales.addRow(new Object[]{
            disease.getId(),
            disease.getName(),
            disease.isVaccinable(),
            disease.getPriority()
        });
    }

    private void saveRecord() {
        ArrayList<Disease> updatedDiseases = new ArrayList<>();
        for (int i = 0; i < modeloEnfermedadesActuales.getRowCount(); i++) {
            String diseaseId = (String) modeloEnfermedadesActuales.getValueAt(i, 0);
            Disease disease = HospitalController.getInstance().findDiseaseById(diseaseId);
            if (disease != null) {
                updatedDiseases.add(disease);
            }
        }

        patientRecord.setDiseaseHistory(updatedDiseases);
        patientRecord.setLastModification(new Date());

        HospitalController.getInstance().updateRecord(patient.getId(), patientRecord);

        dispose();
    }
}