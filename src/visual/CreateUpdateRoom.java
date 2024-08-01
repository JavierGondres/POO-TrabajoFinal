package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import backend.classes.MedicalEmployee;
import backend.classes.Patient;
import backend.classes.Query;
import backend.classes.Room;
import backend.controller.HospitalController;
import backend.enums.QueryTime;
import backend.enums.UserType;
import backend.interfaces.GeneralCallback;
import backend.utils.IdGenerator;
import visual.components.CustomTextField;

public class CreateUpdateRoom extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JLabel lblTitle = new JLabel("Crear habitacion");
    private CustomTextField idTextField = new CustomTextField();
    private CustomTextField doctorTextField = new CustomTextField();
    private CustomTextField patientTextField = new CustomTextField();
    private JDateChooser dateChooser = new JDateChooser();
    private MedicalEmployee selectedMedicalEmployee;
    private Patient selectedPatient; 
    private JButton okButton = new JButton("Crear");
    private JButton btnSearchDoctor = new JButton("Buscar");
    private JButton btnSearchPatient = new JButton("Buscar");
    private JLabel lblUser = new JLabel("Doctor:");
    private JButton cancelButton = new JButton("Cancelar");
    private GeneralCallback callback;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateUpdateRoom dialog = new CreateUpdateRoom(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateUpdateRoom(Room room, GeneralCallback callback) {
    	this.callback = callback;
        initializeComponentWithRoom(room);
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
        lblTitle.setBounds(0, 13, 584, 25);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
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
                }, null, false);
                renderDoctors.setModal(true);
                renderDoctors.setVisible(true);
            }
        });
        contentPanel.add(btnSearchDoctor);
        
        patientTextField.setEditable(false);
        patientTextField.setColumns(10);
        patientTextField.setBounds(120, 176, 167, 22);
        contentPanel.add(patientTextField);
        btnSearchPatient.setBackground(Color.decode("#0f1c30"));
        btnSearchPatient.setForeground(Color.WHITE);
        btnSearchPatient.setBorderPainted(false);
        btnSearchPatient.setBounds(313, 176, 97, 25);
        btnSearchPatient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RenderUsers renderDoctors = new RenderUsers(HospitalController.getInstance().getPatients(), UserType.PATIENT, selectedUser -> {
                    selectedPatient = (Patient) selectedUser;
                    if (selectedPatient == null)
                        return;
                    initalizeFieldWithPatient();
                }, null, false);
                renderDoctors.setModal(true);
                renderDoctors.setVisible(true);
            }
        });
        contentPanel.add(btnSearchPatient);

        JLabel lblPatient = new JLabel("Paciente:");
        lblPatient.setForeground(Color.decode("#0f1c30"));
        lblPatient.setFont(lblPatient.getFont().deriveFont(Font.BOLD, 14));
        lblPatient.setBounds(23, 174, 85, 25);
        contentPanel.add(lblPatient);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setForeground(Color.decode("#0f1c30"));
        lblFecha.setFont(lblFecha.getFont().deriveFont(Font.BOLD, 14));
        lblFecha.setBounds(23, 221, 85, 25);
        contentPanel.add(lblFecha);

        dateChooser.setBounds(120, 221, 167, 22);
        contentPanel.add(dateChooser);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            
            JButton reset = new JButton("Limpiar");
            reset.setActionCommand("OK");
            reset.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	clean();
                }
            });
            buttonPane.add(reset);
            {
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(room == null)
                            createRoom();
                        else
                        	updateRoom();
                        
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
    }

    private void initializeComponentWithRoom(Room room) {
        if (room == null) {
            idTextField.setText(IdGenerator.generarID());
            dateChooser.setDate(new Date());
            dateChooser.setEnabled(false);
            return;
        }
        if(room.getDoctorID() != null) {
        	MedicalEmployee medicalEmployee = (MedicalEmployee) HospitalController.getInstance().findEmployeeById(room.getDoctorID());
            doctorTextField.setText(medicalEmployee.getUserName());
        	selectedMedicalEmployee = medicalEmployee;
        }
        if(room.getPatientID() != null) {
            Patient patient = HospitalController.getInstance().findPatientById(room.getPatientID());
            selectedPatient = patient;
            patientTextField.setText(patient.getUserName());
        }
        Patient patient = HospitalController.getInstance().findPatientById(room.getPatientID());
        selectedPatient = patient;
        lblTitle.setText("Habitacion");
        okButton.setText("Actualizar");
        idTextField.setText(room.getId());
        dateChooser.setDate(room.getDate());
        dateChooser.setEnabled(false);
    }

    private void initalizeFieldWithMedicalEmployee() {
        doctorTextField.setText(selectedMedicalEmployee.getUserName());
    }
    
    private void initalizeFieldWithPatient() {
    	patientTextField.setText(selectedPatient.getUserName());
    }

    private void createRoom() {
    	try {
            String id = idTextField.getText();
            String doctorId=null;
            String patientId = null;
            if(selectedMedicalEmployee != null) {
            	 doctorId = selectedMedicalEmployee.getId();
            }
            if(selectedPatient !=null) {
            	
            	 patientId = selectedPatient.getId();
            }
            Date date = dateChooser.getDate();
            Room room = null; 
            if(patientId != null)
            	room = new Room(id, patientId , doctorId, false, date);
            else
            	room = new Room(id, patientId , doctorId, true, date);
            HospitalController.getInstance().addRoom(room);
            JOptionPane.showMessageDialog(this, "Habitacion creada", "", JOptionPane.INFORMATION_MESSAGE);
            resetFields();
        } catch (IllegalArgumentException | IllegalStateException e) {
        	JOptionPane.showMessageDialog(this, "Error creando la habitacion: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        	System.err.println(e.getMessage());
        }

    }

    private void updateRoom() {
        try {
            String id = idTextField.getText();
            String doctorId=null;
            String patientId = null;
            if(selectedMedicalEmployee != null) {
            	 doctorId = selectedMedicalEmployee.getId();
            }
            if(selectedPatient !=null) {
            	
            	 patientId = selectedPatient.getId();
            }
            Date date = dateChooser.getDate();
            Room room = null; 
            		
            if(patientId != null)
            	room = new Room(id, patientId , doctorId, false, date);
            else
            	room = new Room(id, patientId , doctorId, true, date);
            
            HospitalController.getInstance().updateRoom(room);

            JOptionPane.showMessageDialog(this, "Habitacion actualizada", "", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (IllegalArgumentException | IllegalStateException e) {
            JOptionPane.showMessageDialog(this, "Error actualizando la Habitacion: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.err.println(e.getMessage());
        }
    }

    private void resetFields() {
        idTextField.setText(IdGenerator.generarID());
        doctorTextField.setText("");
        patientTextField.setText("");
        dateChooser.setDate(new Date());
        selectedMedicalEmployee = null;
        selectedPatient = null;
    }
    private void clean() {
        doctorTextField.setText("");
        patientTextField.setText("");
        dateChooser.setDate(new Date());
        selectedMedicalEmployee = null;
        selectedPatient = null;
    }
}
