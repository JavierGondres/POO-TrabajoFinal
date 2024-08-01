package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import backend.classes.*;
import backend.classes.Record;
import backend.controller.HospitalController;
import backend.enums.*;
import visual.components.CustomTextField;
import visual.components.MainPanel;
import visual.components.QueryCard;
import visual.components.RoundedPanel;
import visual.components.SliderPanel;
import visual.components.UserCardOptions;

public class DashboardMedicalEmployee {

    private JFrame frame;
    private JTextField textFieldBuscar;
    private ArrayList<Query> queries = new ArrayList<>();
    private JPanel cardPanel = new JPanel();
    private GridBagConstraints gbc = new GridBagConstraints();
    private SliderPanel sliderPanel;
    private MainPanel mainPanel;
    private MedicalEmployee currentMedicalEmployee;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DashboardMedicalEmployee window = new DashboardMedicalEmployee();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public DashboardMedicalEmployee() {
        currentMedicalEmployee = HospitalController.getInstance().getCurrentMedicalEmployee();
        frame = new JFrame();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1382, 778);
        frame.getContentPane().setBackground(Color.decode("#668dc0"));
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);

        initializeDummyData();
        queries = HospitalController.getInstance().getConsultations();

        ArrayList<SliderPanel.ButtonInfo> buttonInfoList = new ArrayList<>();

        JButton citasButton = new JButton("Citas");
        citasButton.addActionListener(e -> renderScreen(DashboardMedicalEmployeeScreens.APPOINTMENTS));
        buttonInfoList.add(new SliderPanel.ButtonInfo(citasButton, "/assets/images/medical-appointment.png"));

        JButton perfilButton = new JButton("Perfil");
        perfilButton.addActionListener(e -> renderScreen(DashboardMedicalEmployeeScreens.PROFILE));
        buttonInfoList.add(new SliderPanel.ButtonInfo(perfilButton, null));

        JButton myPatientsButton = new JButton("Mis pacientes");
        myPatientsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RenderUsers renderPatients = new RenderUsers(
                        HospitalController.getInstance().getPatientsFromMedicalEmployee(currentMedicalEmployee.getId()),
                        UserType.PATIENT,
                        null,
                        user -> {

                            ArrayList<JButton> buttonsCard = generateUserCardOptions((Patient)user);
                            UserCardOptions userCardOptions = new UserCardOptions(buttonsCard);
                            userCardOptions.setModal(true);
                            userCardOptions.setVisible(true);
                        },
                        false
                );

                renderPatients.setModal(true);
                renderPatients.setVisible(true);
            }
        });
        buttonInfoList.add(new SliderPanel.ButtonInfo(myPatientsButton, null));

        JButton ajustesButton = new JButton("Ajustes");
        ajustesButton.addActionListener(e -> System.out.println("Ajustes clicked"));
        buttonInfoList.add(new SliderPanel.ButtonInfo(ajustesButton, null));

        sliderPanel = new SliderPanel("Hospital", buttonInfoList);
        sliderPanel.setBackground(Color.decode("#668dc0"));
        sliderPanel.setBounds(5, 26, 245, 704);
        frame.getContentPane().add(sliderPanel);


        mainPanel = new MainPanel();
        frame.getContentPane().add(mainPanel);
        renderScreen(DashboardMedicalEmployeeScreens.APPOINTMENTS);
    }

    private ArrayList<JButton> generateUserCardOptions(Patient patient) {
        ArrayList<JButton> buttonsCard = new ArrayList<>();
        JButton infoButton = new JButton("Ver informacion");
        infoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PatientInformation patientInformation = new PatientInformation(patient);
                patientInformation.setModal(true);
                patientInformation.setVisible(true);
            }
        });
        buttonsCard.add(infoButton);

        JButton historialButton = new JButton("Historial");
        historialButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PatientRecord patientRecord = new PatientRecord(patient);
                patientRecord.setModal(true);
                patientRecord.setVisible(true);
            }
        });
        buttonsCard.add(historialButton);

        return buttonsCard;
    }

    private void initializeDummyData() {
        ArrayList<Specialty> especialidades = new ArrayList<>();
        especialidades.add(Specialty.EMERGENCY_MEDICINE);
        Patient patient1 = HospitalController.getInstance().getCurrentPatient();

        Query query1 = new Query("1", patient1.getId(), currentMedicalEmployee.getId(), 100.0f, new Date(), true, QueryTime.THIRTY_MINUTES, LocalTime.of(9, 0), LocalTime.of(9, 30));
        Query query2 = new Query("2", patient1.getId(), currentMedicalEmployee.getId(), 100.0f, new Date(), true, QueryTime.THIRTY_MINUTES, LocalTime.of(9, 30), LocalTime.of(10, 0));
        Query query3 = new Query("3", patient1.getId(), currentMedicalEmployee.getId(), 100.0f, new Date(), true, QueryTime.THIRTY_MINUTES, LocalTime.of(10, 0), LocalTime.of(10, 30));
        Query query4 = new Query("4", patient1.getId(), currentMedicalEmployee.getId(), 100.0f, new Date(), true, QueryTime.THIRTY_MINUTES, LocalTime.of(10, 30), LocalTime.of(11, 0));
        Query query5 = new Query("5", patient1.getId(), currentMedicalEmployee.getId(), 100.0f, new Date(), true, QueryTime.THIRTY_MINUTES, LocalTime.of(11, 0), LocalTime.of(11, 30));
        Query query6 = new Query("6", patient1.getId(), currentMedicalEmployee.getId(), 100.0f, new Date(), true, QueryTime.THIRTY_MINUTES, LocalTime.of(11, 30), LocalTime.of(12, 0));
        Query query7 = new Query("7", patient1.getId(), currentMedicalEmployee.getId(), 100.0f, new Date(), true, QueryTime.THIRTY_MINUTES, LocalTime.of(12, 0), LocalTime.of(12, 30));
        HospitalController.getInstance().addConsultation(query1);
        HospitalController.getInstance().addConsultation(query2);
        HospitalController.getInstance().addConsultation(query3);
        HospitalController.getInstance().addConsultation(query4);
        HospitalController.getInstance().addConsultation(query5);
        HospitalController.getInstance().addConsultation(query6);
        HospitalController.getInstance().addConsultation(query7);

        Disease enfermedad1 = new Disease("1", "enfermedad1", false, Priority.ALLERGIC);
        Disease enfermedad2 = new Disease("2", "enfermedad2", false, Priority.ALLERGIC);
        Disease enfermedad3 = new Disease("3", "enfermedad3", false, Priority.ALLERGIC);
        Disease enfermedad4 = new Disease("4", "enfermedad4", false, Priority.ALLERGIC);

        HospitalController.getInstance().addDisease(enfermedad1);
        HospitalController.getInstance().addDisease(enfermedad2);
        HospitalController.getInstance().addDisease(enfermedad3);
        HospitalController.getInstance().addDisease(enfermedad4);

        Vaccine vaccine1 = new Vaccine("1", "vacuna1", "1", 5, 10);
        Vaccine vaccine2 = new Vaccine("2", "vacuna2", "2", 5, 10);
        Vaccine vaccine3 = new Vaccine("3", "vacuna3", "3", 5, 10);
        Vaccine vaccine4 = new Vaccine("4", "vacuna4", "4", 6, 10);

        HospitalController.getInstance().addVaccine(vaccine1);
        HospitalController.getInstance().addVaccine(vaccine2);
        HospitalController.getInstance().addVaccine(vaccine3);
        HospitalController.getInstance().addVaccine(vaccine4);
        try {        	
        	Record record = HospitalController.getInstance().getRecord(patient1.getId());
            ArrayList<Disease> enfermedades = new ArrayList<>();
            enfermedades.add(enfermedad1);
            enfermedades.add(enfermedad2);
            
            ArrayList<Vaccine> vaccines = new ArrayList<>();
            vaccines.add(vaccine1);
            vaccines.add(vaccine2);
            record.setVaccines(vaccines);
            HospitalController.getInstance().updateRecord(patient1.getId(), record);
        }catch(IllegalArgumentException e) {
        	HospitalController.getInstance().initializePatientRecord(patient1);
        	Record record = HospitalController.getInstance().getRecord(patient1.getId());
            ArrayList<Disease> enfermedades = new ArrayList<>();
            enfermedades.add(enfermedad1);
            enfermedades.add(enfermedad2);
            record.setDiseaseHistory(enfermedades);
            HospitalController.getInstance().updateRecord(patient1.getId(), record);
            ArrayList<Vaccine> vaccines = new ArrayList<>();
            vaccines.add(vaccine1);
            vaccines.add(vaccine2);
            record.setVaccines(vaccines);
            HospitalController.getInstance().updateRecord(patient1.getId(), record);
        }


    }

    private void renderScreen(DashboardMedicalEmployeeScreens screen) {
        switch (screen) {
            case APPOINTMENTS:
                renderAppointmentsScreen();
                break;
            case PROFILE:
                renderProfileScreen();
                break;
            default:
                break;
        }
    }

    public void renderAppointmentsScreen() {
        mainPanel.removeAll();
        JLabel lblMisCitas = new JLabel("Mis Citas");
        lblMisCitas.setForeground(Color.decode("#668dc0"));
        lblMisCitas.setBounds(27, 25, 207, 71);
        Font currentFont = lblMisCitas.getFont();
        Font newFont = currentFont.deriveFont(Font.BOLD, 40f);
        lblMisCitas.setFont(newFont);
        mainPanel.add(lblMisCitas);

        JLabel lblBuscar = new JLabel("Buscar");
        lblBuscar.setForeground(Color.decode("#668dc0"));
        lblBuscar.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblBuscar.setBounds(27, 112, 66, 22);
        mainPanel.add(lblBuscar);

        URL imageURL = getClass().getResource("/assets/images/search.png");
        ImageIcon searchIcon = new ImageIcon(imageURL);
        Image img = searchIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(img);

        textFieldBuscar = new CustomTextField(searchIcon);
        textFieldBuscar.setBounds(98, 109, 207, 30);
        textFieldBuscar.setColumns(10);
        textFieldBuscar.setBackground(new Color(245, 245, 245));
        textFieldBuscar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterQueriesByPatientName();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterQueriesByPatientName();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterQueriesByPatientName();
            }
        });

        mainPanel.add(textFieldBuscar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBounds(27, 181, 602, 541);
        mainPanel.add(scrollPane);

        cardPanel.setBackground(Color.WHITE);
        cardPanel.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 1.0;

        filterQueriesByPatientName();

        scrollPane.setViewportView(cardPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        cardPanel.revalidate();

        RoundedPanel rightPanel = new RoundedPanel();
        rightPanel.setShadowEnabled(false);
        rightPanel.setHoverEnabled(false);
        rightPanel.setBackground(Color.decode("#c0d0ef"));
        rightPanel.setCornerRadii(0, 30, 30, 0);
        rightPanel.setBounds(683, 0, 413, 904);
        mainPanel.add(rightPanel);
        rightPanel.setLayout(null);

        JLabel lblBalance = new JLabel(HospitalController.getInstance().getCurrentUser().getBalance() + " $RD");
        lblBalance.setBounds(229, 85, 154, 16);
        lblBalance.setForeground(Color.decode("#668dc0"));
        rightPanel.add(lblBalance);
        Font currentBalanceFont = lblBalance.getFont();
        Font newBalanceFont = currentBalanceFont.deriveFont(16f);
        lblBalance.setFont(newBalanceFont);

        JLabel lblNombre = new JLabel(HospitalController.getInstance().getCurrentUser().getUserName() + " " + HospitalController.getInstance().getCurrentUser().getLastName());
        lblNombre.setBounds(229, 41, 184, 31);
        lblNombre.setForeground(Color.decode("#668dc0"));
        rightPanel.add(lblNombre);
        Font currentNombreFont = lblNombre.getFont();
        Font newNombreFont = currentNombreFont.deriveFont(Font.BOLD, 20f);
        lblNombre.setFont(newNombreFont);

        JPanel panel = new JPanel();
        panel.setBounds(12, 164, 389, 391);
        rightPanel.add(panel);

        frame.revalidate();
        frame.repaint();
    }

    public interface GeneralCallback {
        void onPressOk();
    }

    public void renderProfileScreen() {
        mainPanel.removeAll();

        frame.revalidate();
        frame.repaint();
    }

    private void filterQueriesByPatientName() {
        String searchText = textFieldBuscar.getText().toLowerCase();
        ArrayList<Query> filteredQueries = new ArrayList<>();

        for (Query query : queries) {
            Patient patient = HospitalController.getInstance().findPatientById(query.getPatientID());
            if (patient.getUserName().toLowerCase().contains(searchText)) {
                filteredQueries.add(query);
            }
        }

        updateQueryCards(filteredQueries);
    }

    private void updateQueryCards(ArrayList<Query> filteredQueries) {
        cardPanel.removeAll();
        cardPanel.revalidate();
        cardPanel.repaint();
        for (Query query : filteredQueries) {
            String time = query.getStartingTime().toString() + " / " + query.getEndingTime().toString();
            Patient patient = HospitalController.getInstance().findPatientById(query.getPatientID());

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = sdf.format(query.getDate());

            QueryCard queryCard = new QueryCard(patient.getUserName(), formattedDate, time);
            queryCard.setPreferredSize(new Dimension(450, 179));
            queryCard.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    UpdateCreateReadQuery component = new UpdateCreateReadQuery(query, new backend.interfaces.GeneralCallback() {
                        @Override
                        public void onPressOk() {
                            filterQueriesByPatientName();
                        }
                    }, UserType.PATIENT);
                    component.setModal(true);
                    component.setVisible(true);
                }
            });
            cardPanel.add(queryCard, gbc);
        }
        cardPanel.revalidate();
        cardPanel.repaint();
    }

	public JFrame getFrame() {
		return frame;
	}


}
