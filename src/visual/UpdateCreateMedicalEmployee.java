package visual;

import backend.controller.HospitalController;
import backend.file.FileHandler;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import backend.classes.MedicalEmployee;
import backend.enums.QueryTime;
import backend.enums.Specialty;
import backend.utils.IdGenerator;
import visual.components.CustomTextField;
import javax.swing.JFormattedTextField;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class UpdateCreateMedicalEmployee extends JDialog {
    private final JPanel contentPanel = new JPanel();
    private JLabel lblTitle = new JLabel("Doctor");
    private CustomTextField fieldNombre = new CustomTextField();
    private JDateChooser dateChooser = new JDateChooser();
    private JButton cancelButton = new JButton("Cerrar");
    private CustomTextField fieldID = new CustomTextField();
    private JSpinner shiftStartSpinner = new JSpinner(new SpinnerDateModel());
    private JSpinner shiftEndSpinner = new JSpinner(new SpinnerDateModel());
    private CustomTextField fieldContrasenia = new CustomTextField();
    private CustomTextField fieldLastName = new CustomTextField();
    private MedicalEmployee medicalEmployee;
    private JComboBox<QueryTime> comboBoxQueryTime = new JComboBox<>();
    private JList<String> listSpecialties = new JList<String>();
    private DefaultListModel<String> specialtiesModel = new DefaultListModel<>();
    private FileHandler fileHandler;
    private String filePath;
    private HospitalController hospitalController;

    public static void main(String[] args) {
        try {
            UpdateCreateMedicalEmployee dialog = new UpdateCreateMedicalEmployee(null, null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UpdateCreateMedicalEmployee(RenderUsers parent, MedicalEmployee employee) {
        super(parent, "Agregar/Actualizar Empleado Médico", true);
        fileHandler = new FileHandler();
        this.filePath = "C:\\Users\\Scarlet\\OneDrive\\Documentos\\Java Proyects\\POO-TrabajoFinal\\src\\backend\\GeneralFile.txt";
        this.medicalEmployee = employee;
        initializeForm();
        setBounds(100, 100, 602, 749);
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
        lblTitle.setBounds(12, 13, 560, 25);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD, 20));
        headerPanel.add(lblTitle);
        contentPanel.add(headerPanel);

        fieldNombre.setColumns(10);
        fieldNombre.setBounds(109, 140, 167, 22);
        contentPanel.add(fieldNombre);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setForeground(Color.decode("#0f1c30"));
        lblNombre.setFont(lblNombre.getFont().deriveFont(Font.BOLD, 14));
        lblNombre.setBounds(12, 138, 67, 25);
        contentPanel.add(lblNombre);

        JLabel lblFecha = new JLabel("Fecha de nacimiento:");
        lblFecha.setForeground(Color.decode("#0f1c30"));
        lblFecha.setFont(lblFecha.getFont().deriveFont(Font.BOLD, 14));
        lblFecha.setBounds(12, 290, 170, 25);
        contentPanel.add(lblFecha);

        dateChooser.setBounds(178, 293, 167, 22);
        contentPanel.add(dateChooser);

        fieldID.setEditable(false);
        fieldID.setColumns(10);
        fieldID.setBounds(109, 89, 167, 22);
        contentPanel.add(fieldID);

        JLabel lblID = new JLabel("ID:");
        lblID.setForeground(Color.decode("#0f1c30"));
        lblID.setFont(lblID.getFont().deriveFont(Font.BOLD, 14));
        lblID.setBounds(12, 87, 67, 25);
        contentPanel.add(lblID);

        fieldContrasenia.setText("");
        fieldContrasenia.setColumns(10);
        fieldContrasenia.setBounds(109, 232, 167, 22);
        contentPanel.add(fieldContrasenia);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setForeground(new Color(15, 28, 48));
        lblPassword.setFont(lblPassword.getFont().deriveFont(14f));
        lblPassword.setBounds(12, 230, 89, 25);
        contentPanel.add(lblPassword);

        JLabel especialidadeslbl = new JLabel("Especialidades:");
        especialidadeslbl.setForeground(new Color(15, 28, 48));
        especialidadeslbl.setFont(especialidadeslbl.getFont().deriveFont(14f));
        especialidadeslbl.setBounds(12, 342, 120, 25);
        contentPanel.add(especialidadeslbl);

        Arrays.stream(Specialty.values()).forEach(specialty -> specialtiesModel.addElement(specialty.name()));

        listSpecialties.setModel(specialtiesModel);
        listSpecialties.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JScrollPane scrollPane = new JScrollPane(listSpecialties);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(133, 345, 156, 133);
        contentPanel.add(scrollPane);

        JLabel lblQuery = new JLabel("Duración de las citas:");
        lblQuery.setForeground(new Color(15, 28, 48));
        lblQuery.setFont(lblQuery.getFont().deriveFont(14f));
        lblQuery.setBounds(12, 491, 150, 25);
        contentPanel.add(lblQuery);

        comboBoxQueryTime.setBounds(175, 493, 156, 22);
        for (QueryTime queryTime : QueryTime.values()) {
            comboBoxQueryTime.addItem(queryTime);
        }
        contentPanel.add(comboBoxQueryTime);

        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);

        NumberFormatter numberFormatter = new NumberFormatter(numberFormat);
        numberFormatter.setAllowsInvalid(false);
        numberFormatter.setMinimum(0.0);

        JFormattedTextField precioField = new JFormattedTextField(numberFormatter);
        precioField.setValue(0.0);
        precioField.setColumns(10);
        precioField.setBounds(147, 531, 167, 22);
        contentPanel.add(precioField);

        JLabel preciolbl = new JLabel("Precio de consulta:");
        preciolbl.setForeground(new Color(15, 28, 48));
        preciolbl.setFont(preciolbl.getFont().deriveFont(14f));
        preciolbl.setBounds(12, 529, 135, 25);
        contentPanel.add(preciolbl);

        JLabel lblShiftStart = new JLabel("Inicio de turno:");
        lblShiftStart.setForeground(Color.decode("#0f1c30"));
        lblShiftStart.setFont(lblShiftStart.getFont().deriveFont(Font.BOLD, 14));
        lblShiftStart.setBounds(12, 567, 120, 25);
        contentPanel.add(lblShiftStart);

        JSpinner.DateEditor startTimeEditor = new JSpinner.DateEditor(shiftStartSpinner, "HH:mm");
        shiftStartSpinner.setEditor(startTimeEditor);
        shiftStartSpinner.setBounds(140, 569, 100, 22);
        contentPanel.add(shiftStartSpinner);

        JLabel lblShiftEnd = new JLabel("Fin de turno:");
        lblShiftEnd.setForeground(Color.decode("#0f1c30"));
        lblShiftEnd.setFont(lblShiftEnd.getFont().deriveFont(Font.BOLD, 14));
        lblShiftEnd.setBounds(12, 607, 120, 25);
        contentPanel.add(lblShiftEnd);

        JSpinner.DateEditor endTimeEditor = new JSpinner.DateEditor(shiftEndSpinner, "HH:mm");
        shiftEndSpinner.setEditor(endTimeEditor);
        shiftEndSpinner.setBounds(140, 609, 100, 22);
        contentPanel.add(shiftEndSpinner);

        JLabel labelLastName = new JLabel("Apellido:");
        labelLastName.setForeground(new Color(15, 28, 48));
        labelLastName.setFont(labelLastName.getFont().deriveFont(14f));
        labelLastName.setBounds(12, 186, 67, 25);
        contentPanel.add(labelLastName);

        fieldLastName.setText("");
        fieldLastName.setColumns(10);
        fieldLastName.setBounds(109, 188, 167, 22);
        contentPanel.add(fieldLastName);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        cancelButton.setActionCommand("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JButton buttonOk = new JButton();
        if (medicalEmployee == null) {
            fieldID.setText(IdGenerator.generarID());
            buttonOk.setText("Crear");
        } else {
            lblTitle.setText("Actualizar");
            buttonOk.setText("Actualizar");
        }

        buttonOk.setActionCommand("OK");
        buttonPane.add(buttonOk);
        buttonPane.add(cancelButton);

        buttonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = fieldID.getText();
                String name = fieldNombre.getText();
                String lastName = fieldLastName.getText();
                String password = fieldContrasenia.getText();
                java.util.Date birthDate = dateChooser.getDate();
                List<String> selectedSpecialtyNames = listSpecialties.getSelectedValuesList();

                List<Specialty> selectedSpecialties = selectedSpecialtyNames.stream()
                        .map(choosen -> Specialty.valueOf(choosen))
                        .collect(Collectors.toList());

                Specialty[] specialtiesArray = selectedSpecialties.toArray(new Specialty[0]);

                QueryTime queryTime = (QueryTime) comboBoxQueryTime.getSelectedItem();
                LocalTime shiftStart = ((SpinnerDateModel) shiftStartSpinner.getModel()).getDate().toInstant()
                        .atZone(java.time.ZoneId.systemDefault()).toLocalTime();
                LocalTime shiftEnd = ((SpinnerDateModel) shiftEndSpinner.getModel()).getDate().toInstant()
                        .atZone(java.time.ZoneId.systemDefault()).toLocalTime();
                float consultationPrice = ((Number) precioField.getValue()).floatValue();

                if (medicalEmployee == null) {
                    medicalEmployee = new MedicalEmployee(id, name, lastName, password, birthDate, consultationPrice,
                            new ArrayList<>(Arrays.asList(specialtiesArray)), shiftStart, shiftEnd,
                            new File("C:\\Users\\Scarlet\\Downloads\\Desktop Photos\\universe.png"), queryTime, 100);
                    hospitalController.getInstance().addEmployee(medicalEmployee);

                } else {
                    medicalEmployee.setUserName(name);
                    medicalEmployee.setLastName(lastName);
                    medicalEmployee.setPassword(password);
                    medicalEmployee.setBirthday(birthDate);
                    medicalEmployee.setSpecialities(new ArrayList<>(Arrays.asList(specialtiesArray)));
                    medicalEmployee.setQueryTime(queryTime);
                    medicalEmployee.setShiftStart(String.valueOf(shiftStart));
                    medicalEmployee.setShiftEnd(String.valueOf(shiftEnd));
                    hospitalController.addEmployee(medicalEmployee);
                }
                try {
                    fileHandler.appendToFile(filePath, hospitalController.getInstance().serializeToJson());
                    HospitalController.getInstance().saveDataToFile(filePath);
                    dispose();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(UpdateCreateMedicalEmployee.this, "Error al guardar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void initializeForm() {
        if (medicalEmployee == null) {
            fieldID.setText(IdGenerator.generarID());
            return;
        }

        lblTitle.setText("Actualizar");
        fieldNombre.setText(medicalEmployee.getUserName());
        fieldID.setText(medicalEmployee.getId());
        dateChooser.setDate(medicalEmployee.getBirthday());
        fieldContrasenia.setText(medicalEmployee.getPassword());
        fieldLastName.setText(medicalEmployee.getLastName());

        List<Specialty> specialities = new ArrayList<>(medicalEmployee.getSpecialities());
        List<String> specialtiesList = specialities.stream()
                .map(Enum::name)
                .collect(Collectors.toList());
        int[] selectedIndices = specialtiesList.stream()
                .mapToInt(specialty -> specialtiesModel.indexOf(specialty))
                .toArray();
        listSpecialties.setSelectedIndices(selectedIndices);

        comboBoxQueryTime.setSelectedItem(medicalEmployee.getQueryTime());
        shiftStartSpinner.setValue(java.util.Date.from(medicalEmployee.getShiftStart().atDate(java.time.LocalDate.now()).atZone(java.time.ZoneId.systemDefault()).toInstant()));
        shiftEndSpinner.setValue(java.util.Date.from(medicalEmployee.getShiftEnd().atDate(java.time.LocalDate.now()).atZone(java.time.ZoneId.systemDefault()).toInstant()));
    }

    public MedicalEmployee getNewMedicalEmployee() {
            return medicalEmployee;
    }
}
