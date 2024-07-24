package visual;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import backend.classes.MedicalEmployee;
import backend.classes.User;
import backend.controller.HospitalController;
import backend.enums.Specialty;
import backend.enums.UserType;
import backend.interfaces.UserSelectionCallback;
import visual.components.DoctorCard;
import visual.components.UserCard;

public class RenderUsers extends JDialog {
    private final JPanel panel = new JPanel();
    private ArrayList<User> users = new ArrayList<>();
    private UserCard selectedCard = null;
    private User selectedUser = null;
    private JButton okButton = new JButton("Seleccionar");
    private JButton cancelButton = new JButton("Cancelar");
    private JLabel lblTitle = new JLabel("");
    private JPanel cardsPanel = new JPanel(new GridLayout(0, 3, 20, 20));
    private UserSelectionCallback callback;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                RenderUsers dialog = new RenderUsers(null, null);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public RenderUsers(UserType userTypeToRender, UserSelectionCallback callback) {
    	this.callback = callback;
        initializeDataByTypeOfUser(userTypeToRender);
        initializeUI();
        renderCards(userTypeToRender);
    }

    private void initializeUI() {
        setTitle("Seleccion de " + lblTitle.getText());
        setSize(939, 712);
        setLocationRelativeTo(null);
        setModalityType(ModalityType.APPLICATION_MODAL);

        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());

        JPanel headerPanel = createHeaderPanel();
        JScrollPane scrollPane = createScrollPane();
        JPanel buttonPane = createButtonPane();

        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);

        lblTitle.setForeground(Color.decode("#0f1c30"));
        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD, 20));
        headerPanel.add(lblTitle, BorderLayout.NORTH);

        JPanel dividerPanel = new JPanel();
        dividerPanel.setPreferredSize(new Dimension(1, 3));
        dividerPanel.setBackground(Color.decode("#0f1c30"));
        headerPanel.add(dividerPanel, BorderLayout.SOUTH);

        return headerPanel;
    }

    private JScrollPane createScrollPane() {
        cardsPanel.setBackground(Color.WHITE);
        cardsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane(cardsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        return scrollPane;
    }

    private JPanel createButtonPane() {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

        okButton.setEnabled(false);
        okButton.addActionListener(e -> {
            if(selectedUser != null) {
                if (callback != null) {
                    callback.onUserSelected(selectedUser);
                }
            }
            dispose();
        });
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);

        return buttonPane;
    }

    private void initializeDataByTypeOfUser(UserType userTypeToRender) {
        switch(userTypeToRender) {
            case PATIENT:
                users = new ArrayList<>(HospitalController.getInstance().getPatients());
                lblTitle.setText("Pacientes");
                break;
            case MEDICAL_EMPLOYEE:
                users = new ArrayList<>(HospitalController.getInstance().getMedicalEmployees());
                lblTitle.setText("Doctores");
                break;
            default:
                users = new ArrayList<>(HospitalController.getInstance().getEmployees());
                lblTitle.setText("Empleados administrativos");
                break;
        }
    }
    
    private void renderCards(UserType userTypeToRender) {
        for (User user : users) {
            UserCard card = createCardForUser(user, userTypeToRender);
            card.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    selectCard(card, user);
                }
            });
            cardsPanel.add(card);
        }
    }

    private UserCard createCardForUser(User user, UserType userTypeToRender) {
        if(userTypeToRender == UserType.MEDICAL_EMPLOYEE) {
            String specialties = ((MedicalEmployee)user).getSpecialities().stream()
                    .map(this::formatSpecialty)
                    .collect(Collectors.joining(", "));
            return new DoctorCard("/assets/images/cita-medica.png", user.getUserName(), specialties);
        } else {
            return new UserCard("/assets/images/cita-medica.png", user.getUserName());
        }
    }
    
    private String formatSpecialty(Specialty specialty) {
        return specialty.name().replace('_', ' ').toLowerCase();
    }
    
    private void selectCard(UserCard card, User user) {
        if (selectedCard != null) {
            selectedCard.setSelected(false);
        }
        if (selectedCard != card) {
            card.setSelected(true);
            selectedCard = card;
            selectedUser = user;
            okButton.setEnabled(true);
        } else {
            selectedCard = null;
            selectedUser = null;
            okButton.setEnabled(false);
        }
    }
}