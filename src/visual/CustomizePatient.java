package visual;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class CustomizePatient extends JDialog {
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JPanel BckgroungTheme;
    private CircularImage PhotoPerfil;
    private JTextField currentPasswordField;
    private JTextField newPasswordField;
    private JTextField confirmPasswordField;
    private JPanel menuPanel;
    private JPanel panel;
    private JLabel lblCurrentPassword;
    private JLabel lblNewPassword;
    private JLabel lblConfirmPassword;
    private JButton btnUpdatePassword;

    // Labels for patient details
    private JLabel lblGender;
    private JLabel lblBloodType;
    private JLabel lblAllergies;
    private JLabel lblDiseases;
    private JLabel lblHeight;
    private JLabel lblWeight;
    private JLabel lblPatientID;
    private JLabel lblLastVisit;
    private JTextField txtGender;
    private JTextField txtBloodType;
    private JTextField txtAllergies;
    private JTextField txtDiseases;
    private JTextField txtHeight;
    private JTextField txtWeight;
    private JTextField txtPatientID;
    private JTextField txtLastVisit;

    // New labels and fields for profile section
    private JLabel lblFirstName;
    private JLabel lblLastName;
    private JLabel lblBirthDate;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JSpinner birthDateSpinner;

    public static void main(String[] args) {
        try {
            CustomizePatient dialog = new CustomizePatient();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CustomizePatient() {
        setModal(true);
        setBounds(100, 100, 965, 499);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout());

        // Panel de menú vertical
        menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(150, getHeight()));
        menuPanel.setBackground(ColorPallete.Panel);
        menuPanel.setBorder(new CircularBorder(Color.BLACK, 1, 35));
        menuPanel.setLayout(new GridLayout(10, 1, 5, 5));
        contentPanel.add(menuPanel, BorderLayout.WEST);

        // Creación de los botones del menú
        JButton myDetailsButton = new JButton("Mis detalles");
        JButton profileButton = new JButton("Perfil");
        JButton passwordButton = new JButton("Contraseña");
        JButton billingButton = new JButton("Balance");
        JButton emailButton = new JButton("Email");
        JButton notificationsButton = new JButton("Notificación");

        // Configuración de los botones del menú
        configureMenuButton(myDetailsButton);
        configureMenuButton(profileButton);
        configureMenuButton(passwordButton);
        configureMenuButton(billingButton);
        configureMenuButton(emailButton);
        configureMenuButton(notificationsButton);

        // Agregar los botones al panel del menú
        menuPanel.add(myDetailsButton);
        menuPanel.add(profileButton);
        menuPanel.add(passwordButton);
        menuPanel.add(billingButton);
        menuPanel.add(emailButton);
        menuPanel.add(notificationsButton);

        // Panel principal
        panel = new JPanel();
        panel.setBorder(new CircularBorder(Color.BLACK, 1, 35));
        panel.setBackground(ColorPallete.mainColor_Light);
        contentPanel.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        // Encabezado con imagen de fondo
        BckgroungTheme = new JPanel();
        BckgroungTheme.setLayout(null);
        BckgroungTheme.setBounds(10, 10, 769, 151);
        BckgroungTheme.setBorder(new CircularBorder(Color.BLACK, 1, 35));
        panel.add(BckgroungTheme);

        ImageIcon backgroundIcon = new ImageIcon("C:\\Users\\Scarlet\\OneDrive\\Documentos\\Java Proyects\\POO-TrabajoFinal\\BackGroundPanelPhoto.png");
        Image backgroundImage = backgroundIcon.getImage();
        Image scaledImage = backgroundImage.getScaledInstance(BckgroungTheme.getWidth(), BckgroungTheme.getHeight(), Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(scaledImage));
        backgroundLabel.setBounds(0, 0, BckgroungTheme.getWidth(), BckgroungTheme.getHeight());
        backgroundLabel.setBorder(new CircularBorder(ColorPallete.mainColor_Light, 1, 35));
        BckgroungTheme.add(backgroundLabel);

        // Foto de perfil
        PhotoPerfil = new CircularImage("C:\\Users\\Scarlet\\Downloads\\cute.png");
        PhotoPerfil.setSize(100, 100);
        PhotoPerfil.repaint();
        BckgroungTheme.add(PhotoPerfil);
        PhotoPerfil.setLayout(null);
        BckgroungTheme.setComponentZOrder(backgroundLabel, BckgroungTheme.getComponentCount() - 1);

        // Campo de texto para contraseña actual
        lblCurrentPassword = new JLabel("Current password");
        lblCurrentPassword.setBounds(40, 200, 110, 25);
        panel.add(lblCurrentPassword);
        lblCurrentPassword.setVisible(false);

        currentPasswordField = new JPasswordField();
        currentPasswordField.setBounds(200, 200, 200, 25);
        currentPasswordField.setBorder(new CircularBorder(Color.GRAY, 1, 15));
        panel.add(currentPasswordField);
        currentPasswordField.setVisible(false);

        // Campo de texto para nueva contraseña
        lblNewPassword = new JLabel("New password");
        lblNewPassword.setBounds(40, 250, 99, 25);
        panel.add(lblNewPassword);
        lblNewPassword.setVisible(false);

        newPasswordField = new JPasswordField();
        newPasswordField.setBounds(200, 250, 200, 25);
        newPasswordField.setBorder(new CircularBorder(Color.GRAY, 1, 15));
        panel.add(newPasswordField);
        newPasswordField.setVisible(false);

        // Campo de texto para confirmar nueva contraseña
        lblConfirmPassword = new JLabel("Confirm new password");
        lblConfirmPassword.setBounds(40, 300, 134, 25);
        panel.add(lblConfirmPassword);
        lblConfirmPassword.setVisible(false);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(200, 300, 200, 25);
        confirmPasswordField.setBorder(new CircularBorder(Color.GRAY, 1, 15));
        panel.add(confirmPasswordField);
        confirmPasswordField.setVisible(false);

        // Botón de actualización
        btnUpdatePassword = new JButton("Update password");
        btnUpdatePassword.setBorder(new CircularBorder(Color.GRAY, 1, 15));
        btnUpdatePassword.setBounds(200, 350, 150, 25);
        btnUpdatePassword.setVisible(false);
        btnUpdatePassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para actualizar la contraseña
                JOptionPane.showMessageDialog(null, "Password updated!");
            }
        });
        panel.add(btnUpdatePassword);

        // Labels for patient details
        lblGender = new JLabel("Gender");
        lblGender.setBounds(40, 200, 110, 25);
        lblGender.setVisible(false);
        panel.add(lblGender);

        lblBloodType = new JLabel("Blood Type");
        lblBloodType.setBounds(40, 250, 99, 25);
        lblBloodType.setVisible(false);
        panel.add(lblBloodType);

        lblAllergies = new JLabel("Allergies");
        lblAllergies.setBounds(40, 300, 110, 25);
        lblAllergies.setVisible(false);
        panel.add(lblAllergies);

        lblDiseases = new JLabel("Diseases");
        lblDiseases.setBounds(40, 350, 300, 25);
        lblDiseases.setVisible(false);
        panel.add(lblDiseases);

        lblHeight = new JLabel("Height");
        lblHeight.setBounds(450, 200, 74, 25);
        lblHeight.setVisible(false);
        panel.add(lblHeight);

        lblWeight = new JLabel("Weight");
        lblWeight.setBounds(450, 250, 74, 25);
        lblWeight.setVisible(false);
        panel.add(lblWeight);

        lblPatientID = new JLabel("Patient ID");
        lblPatientID.setBounds(450, 300, 74, 25);
        lblPatientID.setVisible(false);
        panel.add(lblPatientID);

        lblLastVisit = new JLabel("Last Visit");
        lblLastVisit.setBounds(450, 350, 74, 25);
        lblLastVisit.setVisible(false);
        panel.add(lblLastVisit);
        
        txtGender = new JTextField();
        txtGender.setBounds(160, 200, 200, 25);
        txtGender.setBorder(new CircularBorder(Color.GRAY, 1, 15));
        txtGender.setVisible(false);
        panel.add(txtGender);

        txtBloodType = new JTextField();
        txtBloodType.setBounds(160, 250, 200, 25);
        txtBloodType.setBorder(new CircularBorder(Color.GRAY, 1, 15));
        txtBloodType.setVisible(false);
        panel.add(txtBloodType);

        txtAllergies = new JTextField();
        txtAllergies.setBounds(160, 300, 200, 25);
        txtAllergies.setBorder(new CircularBorder(Color.GRAY, 1, 15));
        txtAllergies.setVisible(false);
        panel.add(txtAllergies);

        txtDiseases = new JTextField();
        txtDiseases.setBounds(160, 350, 200, 25);
        txtDiseases.setBorder(new CircularBorder(Color.GRAY, 1, 15));
        txtDiseases.setVisible(false);
        panel.add(txtDiseases);

        txtHeight = new JTextField();
        txtHeight.setBounds(520, 200, 200, 25);
        txtHeight.setBorder(new CircularBorder(Color.GRAY, 1, 15));
        txtHeight.setVisible(false);
        panel.add(txtHeight);

        txtWeight = new JTextField();
        txtWeight.setBounds(520, 250, 200, 25);
        txtWeight.setBorder(new CircularBorder(Color.GRAY, 1, 15));
        txtWeight.setVisible(false);
        panel.add(txtWeight);

        txtPatientID = new JTextField();
        txtPatientID.setBounds(520, 300, 200, 25);
        txtPatientID.setBorder(new CircularBorder(Color.GRAY, 1, 15));
        txtPatientID.setVisible(false);
        panel.add(txtPatientID);

        txtLastVisit = new JTextField();
        txtLastVisit.setBounds(520, 350, 200, 25);
        txtLastVisit.setBorder(new CircularBorder(Color.GRAY, 1, 15));
        txtLastVisit.setVisible(false);
        panel.add(txtLastVisit);

        // New labels and fields for profile section
        lblFirstName = new JLabel("First Name");
        lblFirstName.setBounds(40, 200, 110, 25);
        lblFirstName.setVisible(false);
        panel.add(lblFirstName);

        lblLastName = new JLabel("Last Name");
        lblLastName.setBounds(40, 250, 110, 25);
        lblLastName.setVisible(false);
        panel.add(lblLastName);

        lblBirthDate = new JLabel("Birth Date");
        lblBirthDate.setBounds(40, 300, 110, 25);
        lblBirthDate.setVisible(false);
        panel.add(lblBirthDate);

        txtFirstName = new JTextField();
        txtFirstName.setBounds(160, 200, 200, 25);
        txtFirstName.setBorder(new CircularBorder(Color.GRAY, 1, 15));
        txtFirstName.setVisible(false);
        panel.add(txtFirstName);

        txtLastName = new JTextField();
        txtLastName.setBounds(160, 250, 200, 25);
        txtLastName.setBorder(new CircularBorder(Color.GRAY, 1, 15));
        txtLastName.setVisible(false);
        panel.add(txtLastName);

        birthDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(birthDateSpinner, "dd/MM/yyyy");
        birthDateSpinner.setEditor(dateEditor);
        birthDateSpinner.setBounds(160, 300, 200, 25);
        birthDateSpinner.setBorder(new CircularBorder(Color.GRAY, 1, 15));
        birthDateSpinner.setVisible(false);
        panel.add(birthDateSpinner);

        // Separador
        JSeparator separator = new JSeparator();
        separator.setBackground(Color.BLACK);
        separator.setBounds(22, 185, 757, 2);
        panel.add(separator);
    }

    private void configureMenuButton(JButton button) {
        button.setBackground(ColorPallete.Panel);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Tahoma", Font.BOLD, 12));
        button.setBorder(new CircularBorder(ColorPallete.Panel, 1, 25));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setMargin(new Insets(10, 15, 10, 15));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = button.getText();
                switch (command) {
                    case "Mis detalles":
                        showPatientDetails(true);
                        showPasswordFields(false);
                        showProfileFields(false);
                        break;
                    case "Perfil":
                        showProfileFields(true);
                        showPatientDetails(false);
                        showPasswordFields(false);
                        break;
                    case "Contraseña":
                        showPatientDetails(false);
                        showPasswordFields(true);
                        showProfileFields(false);
                        break;
                    case "Balance":
                        // Acción para "Balance"
                        JOptionPane.showMessageDialog(null, "Clicked: Balance");
                        break;
                    case "Email":
                        // Acción para "Email"
                        JOptionPane.showMessageDialog(null, "Clicked: Email");
                        break;
                    case "Notificación":
                        // Acción para "Notificación"
                        JOptionPane.showMessageDialog(null, "Clicked: Notificación");
                        break;
                    default:
                        // Acción predeterminada
                        JOptionPane.showMessageDialog(null, "Clicked: " + command);
                        break;
                }
            }
        });
    }

    private void showPasswordFields(boolean visible) {
        lblCurrentPassword.setVisible(visible);
        currentPasswordField.setVisible(visible);
        lblNewPassword.setVisible(visible);
        newPasswordField.setVisible(visible);
        lblConfirmPassword.setVisible(visible);
        confirmPasswordField.setVisible(visible);
        btnUpdatePassword.setVisible(visible);
    }

    private void showPatientDetails(boolean visible) {
        lblGender.setVisible(visible);
        lblBloodType.setVisible(visible);
        lblAllergies.setVisible(visible);
        lblDiseases.setVisible(visible);
        lblHeight.setVisible(visible);
        lblWeight.setVisible(visible);
        lblPatientID.setVisible(visible);
        lblLastVisit.setVisible(visible);
        txtGender.setVisible(visible);
        txtBloodType.setVisible(visible);
        txtAllergies.setVisible(visible);
        txtDiseases.setVisible(visible);
        txtHeight.setVisible(visible);
        txtWeight.setVisible(visible);
        txtPatientID.setVisible(visible);
        txtLastVisit.setVisible(visible);
    }

    private void showProfileFields(boolean visible) {
        lblFirstName.setVisible(visible);
        txtFirstName.setVisible(visible);
        lblLastName.setVisible(visible);
        txtLastName.setVisible(visible);
        lblBirthDate.setVisible(visible);
        birthDateSpinner.setVisible(visible);
    }
}
