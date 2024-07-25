package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import visual.components.CustomTextField;

import java.awt.Color;

import backend.classes.MedicalEmployee;
import backend.classes.Patient;
import backend.classes.Query;
import backend.controller.HospitalController;
import backend.enums.QueryTime;
import backend.enums.UserType;
import backend.interfaces.GeneralCallback;
import backend.interfaces.UserSelectionCallback;
import backend.utils.IdGenerator;

import com.toedter.calendar.JDateChooser;

import javax.swing.JComboBox;

public class UpdateCreateReadQuery extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JLabel lblTitle = new JLabel("Crear cita");
    private CustomTextField idTextField = new CustomTextField();
    private CustomTextField doctorTextField = new CustomTextField();
    private CustomTextField precioTextField = new CustomTextField();
    private JDateChooser dateChooser = new JDateChooser();
    private JComboBox rangeOfTimesComboBox = new JComboBox();
    private MedicalEmployee selectedMedicalEmployee;
    private Patient selectedPatient; 
    private JButton okButton = new JButton("Crear");
    private JButton btnSearchDoctor = new JButton("Buscar");
    private JLabel lblUser = new JLabel("Doctor:");
    private JButton cancelButton = new JButton("Cancelar");
    private UserType userTypeToRender;
    private GeneralCallback callback;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UpdateCreateReadQuery dialog = new UpdateCreateReadQuery(null, null, UserType.MEDICAL_EMPLOYEE);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public UpdateCreateReadQuery(Query query, GeneralCallback callback, UserType userTypeToRender) {
    	this.userTypeToRender = userTypeToRender;
    	this.callback = callback;
        initializeComponentWithQuery(query, userTypeToRender);
        setBounds(100, 100, 602, 550);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(Color.decode("#c0d0ef"));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setLocationRelativeTo(null);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.decode("#0f1c30"));
        headerPanel.setBounds(0, 0, 584, 50);
        headerPanel.setLayout(null);
        lblTitle.setBounds(228, 13, 107, 25);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD, 20));
        headerPanel.add(lblTitle);
        contentPanel.add(headerPanel);

        JLabel lblId = new JLabel("ID:");
        lblId.setForeground(Color.decode("#0f1c30"));
        lblId.setFont(lblId.getFont().deriveFont(Font.BOLD, 14));
        lblId.setBounds(23, 77, 85, 25);
        contentPanel.add(lblId);


        idTextField.setEditable(false);
        idTextField.setBounds(120, 79, 167, 22);
        contentPanel.add(idTextField);
        idTextField.setColumns(10);
        doctorTextField.setEditable(false);
        doctorTextField.setColumns(10);
        doctorTextField.setBounds(120, 126, 167, 22);
        contentPanel.add(doctorTextField);

        lblUser.setForeground(Color.decode("#0f1c30"));
        lblUser.setFont(lblId.getFont().deriveFont(Font.BOLD, 14));
        lblUser.setBounds(23, 124, 85, 25);
        contentPanel.add(lblUser);


        btnSearchDoctor.setBackground(Color.decode("#0f1c30"));
        btnSearchDoctor.setForeground(Color.WHITE);
        btnSearchDoctor.setBorderPainted(false);
        btnSearchDoctor.setBounds(313, 125, 97, 25);
        btnSearchDoctor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RenderUsers renderDoctors = new RenderUsers(HospitalController.getInstance().getMedicalEmployees(), UserType.MEDICAL_EMPLOYEE, selectedUser -> {
                    selectedMedicalEmployee = (MedicalEmployee) selectedUser;
                    if (selectedMedicalEmployee == null)
                        return;
                    initalizeFieldWithMedicalEmployee();
                    fillComboBoxWithAvailableTimes();
                }, null);
                renderDoctors.setModal(true);
                renderDoctors.setVisible(true);
            }
        });
        contentPanel.add(btnSearchDoctor);
        precioTextField.setEditable(false);
        precioTextField.setColumns(10);
        precioTextField.setBounds(120, 176, 167, 22);
        contentPanel.add(precioTextField);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setForeground(Color.decode("#0f1c30"));
        lblPrecio.setFont(lblPrecio.getFont().deriveFont(Font.BOLD, 14));
        lblPrecio.setBounds(23, 174, 85, 25);
        contentPanel.add(lblPrecio);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setForeground(Color.decode("#0f1c30"));
        lblFecha.setFont(lblFecha.getFont().deriveFont(Font.BOLD, 14));
        lblFecha.setBounds(23, 221, 85, 25);
        contentPanel.add(lblFecha);

        dateChooser.setBounds(120, 221, 167, 22);
        contentPanel.add(dateChooser);

        JLabel lblHorariosDisponibles = new JLabel("Horarios disponibles:");
        lblHorariosDisponibles.setForeground(Color.decode("#0f1c30"));
        lblHorariosDisponibles.setFont(lblHorariosDisponibles.getFont().deriveFont(Font.BOLD, 14));
        lblHorariosDisponibles.setBounds(23, 269, 153, 25);
        contentPanel.add(lblHorariosDisponibles);
        rangeOfTimesComboBox.setEnabled(false);


        rangeOfTimesComboBox.setBounds(184, 271, 113, 22);
        contentPanel.add(rangeOfTimesComboBox);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(query == null)
                            createQuery();
                        else
                        	updateQuery();
                        
                        if (callback != null) {
                            callback.onPressOk();
                        }
                    }
                });
                getRootPane().setDefaultButton(okButton);
            }
            {
                cancelButton.setActionCommand("Cancel");
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                buttonPane.add(cancelButton);
            }
        }

        idTextField.getDocument().addDocumentListener(new SimpleDocumentListener());
        doctorTextField.getDocument().addDocumentListener(new SimpleDocumentListener());
        precioTextField.getDocument().addDocumentListener(new SimpleDocumentListener());
        dateChooser.addPropertyChangeListener("date", e -> validateFields());
        rangeOfTimesComboBox.addActionListener(e -> validateFields());

        validateFields();
        if(query != null && userTypeToRender == userTypeToRender.MEDICAL_EMPLOYEE)
        	rangeOfTimesComboBox.setEnabled(true);
    }

    private void fillComboBoxWithAvailableTimes() {
        if (selectedMedicalEmployee != null) {
            ArrayList<LocalTime> availableTimes = selectedMedicalEmployee.getRangeOfQueryTime();
            rangeOfTimesComboBox.removeAllItems();

            for (LocalTime time : availableTimes) {

                LocalTime endTime = time.plusMinutes(selectedMedicalEmployee.getIntervalMinutesOfQueryTime());
                String formattedTime = String.format("%s / %s", time, endTime);


                rangeOfTimesComboBox.addItem(formattedTime);
            }

            if(userTypeToRender == UserType.PATIENT) {
            	rangeOfTimesComboBox.setEnabled(false);         	
            }
            else
            	rangeOfTimesComboBox.setEnabled(true);
            validateFields();
        }
    }

    private void initializeComponentWithQuery(Query query, UserType userTypeToRender) {
        if (query == null) {
            idTextField.setText(IdGenerator.generarID());
            return;
        }
        MedicalEmployee medicalEmployee = (MedicalEmployee) HospitalController.getInstance().findEmployeeById(query.getDoctorID());
        Patient patient = HospitalController.getInstance().findPatientById(query.getPatientID());
        selectedPatient = patient;
        selectedMedicalEmployee = medicalEmployee;
        
        if(userTypeToRender == userTypeToRender.PATIENT) {
            okButton.setVisible(false);
            lblUser.setText("Paciente:");
            doctorTextField.setText(patient.getUserName());
            dateChooser.setEnabled(false);
            cancelButton.setText("Cerrar");
        }
        else if(userTypeToRender == userTypeToRender.MEDICAL_EMPLOYEE) {
            doctorTextField.setText(medicalEmployee.getUserName());
            rangeOfTimesComboBox.setEnabled(true);
        }
        
        lblTitle.setText("Mi cita");
        okButton.setText("Actualizar");
        idTextField.setText(query.getId());
        precioTextField.setText(String.valueOf(query.getFee()));
        dateChooser.setDate(query.getDate());
        btnSearchDoctor.setVisible(false);
        fillComboBoxWithAvailableTimes();

        
        LocalTime queryStartTime = query.getStartingTime();
        LocalTime queryEndTime = query.getEndingTime();
        if (queryStartTime != null && queryEndTime != null) {
            String formattedTime = String.format("%s / %s", queryStartTime, queryEndTime);
            rangeOfTimesComboBox.setSelectedItem(formattedTime);
        }
    }

    private void initalizeFieldWithMedicalEmployee() {
        doctorTextField.setText(selectedMedicalEmployee.getUserName());
        precioTextField.setText(String.valueOf(selectedMedicalEmployee.getQueryPrice()));
    }

    private void createQuery() {
    	try {
            String id = idTextField.getText();
            System.out.println(id);
            String doctorId = selectedMedicalEmployee.getId();
            float fee = Float.parseFloat(precioTextField.getText());
            Date date = dateChooser.getDate();
            QueryTime queryTime = selectedMedicalEmployee.getQueryTime();

            String selectedTimeString = (String) rangeOfTimesComboBox.getSelectedItem();
            if (selectedTimeString == null || !selectedTimeString.contains(" / ")) {
                throw new IllegalArgumentException("Invalid time selection");
            }

            String[] times = selectedTimeString.split(" / ");
            LocalTime startingTime = LocalTime.parse(times[0]);
            LocalTime endingTime = LocalTime.parse(times[1]);

            System.out.println(startingTime);
            System.out.println(endingTime);

            HospitalController.getInstance().createQuery(id, HospitalController.getInstance().getCurrentPatient().getId(), doctorId, fee, date, queryTime, startingTime, endingTime);
            JOptionPane.showMessageDialog(this, "Cita creada", "", JOptionPane.INFORMATION_MESSAGE);
            resetFields();
        } catch (IllegalArgumentException | IllegalStateException e) {
        	JOptionPane.showMessageDialog(this, "Error creando la cita: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        	System.err.println(e.getMessage());
        }

    }

    private void updateQuery() {
        try {
            String id = idTextField.getText();

            Date date = dateChooser.getDate();

            String selectedTimeString = (String) rangeOfTimesComboBox.getSelectedItem();
            if (selectedTimeString == null || !selectedTimeString.contains(" / ")) {
                throw new IllegalArgumentException("Invalid time selection");
            }

            String[] times = selectedTimeString.split(" / ");
            LocalTime startingTime = LocalTime.parse(times[0]);
            LocalTime endingTime = LocalTime.parse(times[1]);

            System.out.println(startingTime);
            System.out.println(endingTime);
            HospitalController.getInstance().updateQuery(
                id,
                date,
                startingTime,
                endingTime
            );

            JOptionPane.showMessageDialog(this, "Cita actualizada", "", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (IllegalArgumentException | IllegalStateException e) {
            JOptionPane.showMessageDialog(this, "Error actualizando la cita: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.err.println(e.getMessage());
        }
    }

    private void validateFields() {
        boolean isValid = !idTextField.getText().isEmpty() &&
                          !doctorTextField.getText().isEmpty() &&
                          !precioTextField.getText().isEmpty() &&
                          dateChooser.getDate() != null &&
                          rangeOfTimesComboBox.getSelectedItem() != null;

        okButton.setEnabled(isValid);
    }

    private void resetFields() {
        idTextField.setText(IdGenerator.generarID());
        doctorTextField.setText("");
        precioTextField.setText("");
        dateChooser.setDate(null);
        rangeOfTimesComboBox.removeAllItems();
        rangeOfTimesComboBox.setEnabled(false);
        selectedMedicalEmployee = null;
        validateFields();
    }

    private class SimpleDocumentListener implements javax.swing.event.DocumentListener {
        @Override
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            validateFields();
        }

        @Override
        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            validateFields();
        }

        @Override
        public void changedUpdate(javax.swing.event.DocumentEvent e) {
            validateFields();
        }
    }
}
