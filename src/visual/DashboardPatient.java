package visual;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import backend.classes.Employee;
import backend.classes.MedicalEmployee;
import backend.classes.Query;
import backend.controller.HospitalController;
import backend.enums.QueryTime;
import backend.enums.Specialty;
import visual.components.CustomTextField;
import visual.components.MainPanel;
import visual.components.QueryCard;
import visual.components.RoundedPanel;
import visual.components.SliderPanel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class DashboardPatient {
    private JFrame frame;
    private JTextField textFieldBuscar;
    private ArrayList<Query> queries = new ArrayList<>();
    private JPanel cardPanel = new JPanel();
    private GridBagConstraints gbc = new GridBagConstraints();
    private SliderPanel sliderPanel;
    private MainPanel mainPanel;


    public static void main(String[] args) {

        try {
            DashboardPatient window = new DashboardPatient();
            window.frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public DashboardPatient() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1382, 961);
        frame.getContentPane().setBackground(Color.decode("#668dc0"));
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);

        initializeDummyData();
        queries = HospitalController.getInstance().getConsultations();

        ArrayList<JButton> buttons = new ArrayList<>();
        JButton citasButton = new JButton("Citas");
        citasButton.addActionListener(e -> System.out.println("Citas clicked"));
        buttons.add(citasButton);

        JButton perfilButton = new JButton("Perfil");
        perfilButton.addActionListener(e -> System.out.println("Perfil clicked"));
        buttons.add(perfilButton);

        JButton ajustesButton = new JButton("Ajustes");
        ajustesButton.addActionListener(e -> System.out.println("Ajustes clicked"));
        buttons.add(ajustesButton);

        sliderPanel = new SliderPanel("Hospital", buttons);
        sliderPanel.setBounds(5, 26, 209, 883);
        frame.getContentPane().add(sliderPanel);

        mainPanel = new MainPanel();
        frame.getContentPane().add(mainPanel);

        renderAppointmentsScreen();
    }

    private void initializeDummyData() {
        ArrayList<Specialty> especialidades = new ArrayList<>();
        especialidades.add(Specialty.EMERGENCY_MEDICINE);
        MedicalEmployee doctor1 = new MedicalEmployee("1", "Javier1", "opa", "123456", new Date(), 100, especialidades, LocalTime.now(), LocalTime.now(), null);
        MedicalEmployee doctor2 = new MedicalEmployee("2", "Javier2", "opa", "123456", new Date(), 100, especialidades, LocalTime.now(), LocalTime.now(), null);
        MedicalEmployee doctor3 = new MedicalEmployee("3", "Javier3", "opa", "123456", new Date(), 100, especialidades, LocalTime.now(), LocalTime.now(), null);
        MedicalEmployee doctor4 = new MedicalEmployee("4", "Javier4", "opa", "123456", new Date(), 100, especialidades, LocalTime.now(), LocalTime.now(), null);
        MedicalEmployee doctor5 = new MedicalEmployee("5", "Javier5", "opa", "123456", new Date(), 100, especialidades, LocalTime.now(), LocalTime.now(), null);
        MedicalEmployee doctor6 = new MedicalEmployee("6", "Javier6", "opa", "123456", new Date(), 100, especialidades, LocalTime.now(), LocalTime.now(), null);
        MedicalEmployee doctor7 = new MedicalEmployee("7", "Javier7", "opa", "123456", new Date(), 100, especialidades, LocalTime.now(), LocalTime.now(), null);

        Query query1 = new Query("1", "1", "1", 100.0f, new Date(), true, QueryTime.NINETY_MINUTES, new Date());
        Query query2 = new Query("1", "1", "2", 100.0f, new Date(), true, QueryTime.NINETY_MINUTES, new Date());
        Query query3 = new Query("1", "1", "3", 100.0f, new Date(), true, QueryTime.NINETY_MINUTES, new Date());
        Query query4 = new Query("1", "1", "4", 100.0f, new Date(), true, QueryTime.NINETY_MINUTES, new Date());
        Query query5 = new Query("1", "1", "5", 100.0f, new Date(), true, QueryTime.NINETY_MINUTES, new Date());
        Query query6 = new Query("1", "1", "6", 100.0f, new Date(), true, QueryTime.NINETY_MINUTES, new Date());
        Query query7 = new Query("1", "1", "7", 100.0f, new Date(), true, QueryTime.NINETY_MINUTES, new Date());
        HospitalController.getInstance().addConsultation(query1);
        HospitalController.getInstance().addConsultation(query2);
        HospitalController.getInstance().addConsultation(query3);
        HospitalController.getInstance().addConsultation(query4);
        HospitalController.getInstance().addConsultation(query5);
        HospitalController.getInstance().addConsultation(query6);
        HospitalController.getInstance().addConsultation(query7);

        HospitalController.getInstance().addEmployee(doctor1);
        HospitalController.getInstance().addEmployee(doctor2);
        HospitalController.getInstance().addEmployee(doctor3);
        HospitalController.getInstance().addEmployee(doctor4);
        HospitalController.getInstance().addEmployee(doctor5);
        HospitalController.getInstance().addEmployee(doctor6);
        HospitalController.getInstance().addEmployee(doctor7);
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
                filterQueriesByDoctorName();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterQueriesByDoctorName();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterQueriesByDoctorName();
            }
        });

        mainPanel.add(textFieldBuscar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBounds(27, 181, 602, 710);
        mainPanel.add(scrollPane);

        cardPanel.setBackground(Color.WHITE);
        cardPanel.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 1.0;

        filterQueriesByDoctorName();

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

        JLabel lblBalance = new JLabel("1000 $RD");
        lblBalance.setBounds(229, 85, 154, 16);
        lblBalance.setForeground(Color.decode("#668dc0"));
        rightPanel.add(lblBalance);
        Font currentBalanceFont = lblBalance.getFont();
        Font newBalanceFont = currentBalanceFont.deriveFont(16f);
        lblBalance.setFont(newBalanceFont);

        JLabel lblNombre = new JLabel("Javier Gondres");
        lblNombre.setBounds(229, 41, 184, 31);
        lblNombre.setForeground(Color.decode("#668dc0"));
        rightPanel.add(lblNombre);
        Font currentNombreFont = lblNombre.getFont();
        Font newNombreFont = currentNombreFont.deriveFont(Font.BOLD, 20f);
        lblNombre.setFont(newNombreFont);

        JPanel panel = new JPanel();
        panel.setBounds(12, 164, 389, 391);
        rightPanel.add(panel);

        JButton btnCreateNewQuery = new JButton("Agendar nueva cita");
        btnCreateNewQuery.setBounds(52, 651, 336, 40);
        rightPanel.add(btnCreateNewQuery);

        frame.revalidate();
        frame.repaint();
    }

    private void filterQueriesByDoctorName() {
        String searchText = textFieldBuscar.getText().toLowerCase();
        ArrayList<Query> filteredQueries = new ArrayList<>();

        for (Query query : queries) {
            Employee doctor = HospitalController.getInstance().findEmployeeById(query.getDoctorID());
            if (doctor.getUserName().toLowerCase().contains(searchText)) {
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
            Employee doctor = HospitalController.getInstance().findEmployeeById(query.getDoctorID());
            QueryCard queryCard = new QueryCard(doctor.getUserName(), "10/10/2024", "12:00PM - 01:00PM");
            queryCard.setPreferredSize(new Dimension(450, 179));

            queryCard.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    System.out.println("Se hizo clic en el QueryCard #" + doctor.getUserName());
                }
            });

            cardPanel.add(queryCard, gbc);
        }


        cardPanel.revalidate();
        cardPanel.repaint();
    }
}














