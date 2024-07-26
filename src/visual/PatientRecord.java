package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import backend.classes.Disease;
import backend.classes.Patient;
import backend.classes.Vaccine;
import backend.controller.HospitalController;
import backend.interfaces.GeneralCallback;
import visual.components.CustomTextField;

import com.toedter.calendar.JDateChooser;

import javax.swing.table.TableModel;

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
    private ArrayList<Disease> patientDiseases = new ArrayList<>();
    private ArrayList<Vaccine> patientVaccines = new ArrayList<>();
    private JTable tableVacunas;
    private DefaultTableModel modeloVacunas;

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
        setBounds(100, 100, 658, 756);
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

        JLabel lblEnfermedades = new JLabel("Enfermedades:");
        lblEnfermedades.setForeground(Color.decode("#0f1c30"));
        lblEnfermedades.setFont(lblEnfermedades.getFont().deriveFont(Font.BOLD, 14));
        lblEnfermedades.setBounds(12, 166, 215, 25);
        contentPanel.add(lblEnfermedades);

        JPanel panelEnfermedades = new JPanel();
        panelEnfermedades.setBounds(12, 198, 616, 172);
        contentPanel.add(panelEnfermedades);
        panelEnfermedades.setLayout(null);

        JScrollPane scrollPaneEnfermedades = new JScrollPane();
        scrollPaneEnfermedades.setBounds(0, 0, 616, 172);
        scrollPaneEnfermedades.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelEnfermedades.add(scrollPaneEnfermedades);

        modeloEnfermedadesActuales = new DefaultTableModel(
                new String[]{"ID", "Nombre", "Es vacunable", "Prioridad"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableEnfermedadesActual = new JTable(modeloEnfermedadesActuales);
        tableEnfermedadesActual.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        scrollPaneEnfermedades.setViewportView(tableEnfermedadesActual);


        JLabel lblVacunas = new JLabel("Vacunas:");
        lblVacunas.setForeground(Color.decode("#0f1c30"));
        lblVacunas.setFont(lblVacunas.getFont().deriveFont(Font.BOLD, 14));
        lblVacunas.setBounds(12, 383, 215, 25);
        contentPanel.add(lblVacunas);

        JPanel panelVacunas = new JPanel();
        panelVacunas.setLayout(null);
        panelVacunas.setBounds(12, 415, 616, 172);
        contentPanel.add(panelVacunas);

        JScrollPane scrollPaneVacunas = new JScrollPane();
        scrollPaneVacunas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneVacunas.setBounds(0, 0, 616, 172);
        panelVacunas.add(scrollPaneVacunas);

        modeloVacunas = new DefaultTableModel(
                new String[]{"ID", "Nombre", "Enfermedad", "Edad minima aplicable", "Edad maxima aplicable"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableVacunas = new JTable(modeloVacunas);
        tableVacunas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        scrollPaneVacunas.setViewportView(tableVacunas);

        JLabel lblFecha = new JLabel("Ultima modificacion:");
        lblFecha.setForeground(Color.decode("#0f1c30"));
        lblFecha.setFont(lblFecha.getFont().deriveFont(Font.BOLD, 14));
        lblFecha.setBounds(12, 605, 146, 25);
        contentPanel.add(lblFecha);

        dateChooser.setBounds(170, 608, 167, 22);
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
            patientDiseases = new ArrayList<>(patientRecord.getDiseaseHistory());
            patientVaccines = new ArrayList<>(patientRecord.getVaccines());
            renderCurrentDiseases();
            renderVaccines();
        }
    }

    private void setupListeners() {
        JButton btnAddEnfermedadActual = new JButton("Agregar");
        btnAddEnfermedadActual.setBounds(399, 167, 97, 25);
        contentPanel.add(btnAddEnfermedadActual);
        btnAddEnfermedadActual.addActionListener(e -> openSelectDiseaseDialog());

        JButton btnEliminarEnfermedadActual = new JButton("Eliminar");
        btnEliminarEnfermedadActual.setBounds(531, 167, 97, 25);
        btnEliminarEnfermedadActual.addActionListener(e -> removeSelectedDisease());
        contentPanel.add(btnEliminarEnfermedadActual);

        JButton buttonAddVacuna = new JButton("Agregar");
        buttonAddVacuna.setBounds(399, 384, 97, 25);
        buttonAddVacuna.addActionListener(e -> openSelectVaccineDialog());
        contentPanel.add(buttonAddVacuna);

        JButton buttonDeletVacuna = new JButton("Eliminar");
        buttonDeletVacuna.setBounds(531, 384, 97, 25);
        buttonDeletVacuna.addActionListener(e -> removeSelectedVaccine());
        contentPanel.add(buttonDeletVacuna);

        okButton.addActionListener(e -> saveRecord());
        cancelButton.addActionListener(e -> dispose());
    }

    private void openSelectDiseaseDialog() {
        SelectDisease selectD = new SelectDisease(patientDiseases, new GeneralCallback() {
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
                patientDiseases.add(disease);
            }
        }
    }

    private void removeSelectedDisease() {
        int[] selectedRows = tableEnfermedadesActual.getSelectedRows();
        if (selectedRows.length > 0) {
            // Ordenar los índices en orden descendente para evitar problemas al eliminar
            Arrays.sort(selectedRows);
            for (int i = selectedRows.length - 1; i >= 0; i--) {
                String diseaseId = (String) modeloEnfermedadesActuales.getValueAt(selectedRows[i], 0);
                modeloEnfermedadesActuales.removeRow(selectedRows[i]);
                patientDiseases.removeIf(disease -> disease.getId().equals(diseaseId));
            }
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


    private void openSelectVaccineDialog() {
        SelectVaccine selectV = new SelectVaccine(patientVaccines, new GeneralCallback() {
            @Override
            public void onGetObject(Object object) {
            	addSelectedVaccines(object);
            }
        });
        selectV.setModal(true);
        selectV.setVisible(true);
    }

    private void renderVaccines() {
        modeloVacunas.setRowCount(0);
        for (Vaccine vaccine : patientRecord.getVaccines()) {
            addRowVaccines(vaccine);
        }
    }

    private void addRowVaccines(Vaccine vaccine) {
        Disease disease = HospitalController.getInstance().findDiseaseById(vaccine.getDiseaseId());
        modeloVacunas.addRow(new Object[]{
                vaccine.getId(),
                vaccine.getName(),
                disease.getName(),
                vaccine.getMinAge(),
                vaccine.getMaxAge(),
        });
    }

    private void addSelectedVaccines(Object object) {
        ArrayList<String> idsOfVaccines = (ArrayList<String>) object;
        for (String id : idsOfVaccines) {
            Vaccine vaccine = HospitalController.getInstance().findVaccineById(id);
            if (vaccine != null) {
                addRowVaccines(vaccine);
                patientVaccines.add(vaccine);
            }
        }
    }

    private void removeSelectedVaccine() {
        int[] selectedRows = tableVacunas.getSelectedRows();
        if (selectedRows.length > 0) {
            // Ordenar los índices en orden descendente para evitar problemas al eliminar
            Arrays.sort(selectedRows);
            for (int i = selectedRows.length - 1; i >= 0; i--) {
                String vaccineId = (String) modeloVacunas.getValueAt(selectedRows[i], 0);
                modeloVacunas.removeRow(selectedRows[i]);
                patientVaccines.removeIf(vaccine -> vaccine.getId().equals(vaccineId));
            }
        }
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

        ArrayList<Vaccine> updatedVaccines = new ArrayList<>();
        for (int i = 0; i < modeloVacunas.getRowCount(); i++) {
            String vaccineId = (String) modeloVacunas.getValueAt(i, 0);
            Vaccine vaccine = HospitalController.getInstance().findVaccineById(vaccineId);
            if (vaccine != null) {
                updatedVaccines.add(vaccine);
            }
        }


        backend.classes.Record updatedRecord = new backend.classes.Record(patientRecord);
        updatedRecord.setDiseaseHistory(updatedDiseases);
        updatedRecord.setVaccines(updatedVaccines);
        updatedRecord.setLastModification(new Date());

        HospitalController.getInstance().updateRecord(patient.getId(), updatedRecord);

        JOptionPane.showMessageDialog(this, "Record actualizado", "", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}