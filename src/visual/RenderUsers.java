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

import backend.classes.Employee;
import backend.classes.MedicalEmployee;
import backend.classes.Patient;
import backend.classes.User;
import backend.controller.HospitalController;
import backend.enums.Specialty;
import backend.enums.UserType;
import backend.interfaces.CardPressedCallback;
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
    private JLabel lblTitle = new JLabel("Doctores");
    private JPanel cardsPanel = new JPanel(new GridLayout(0, 3, 20, 20));
    private UserSelectionCallback callback;
    private CardPressedCallback onPressedCardCallback;
    private UserType userTypeToRender;
    private final JButton createUserBtn = new JButton("Agregar");
    private final JButton deleteUserBtn = new JButton("Eliminar");
    private boolean creationAvailable = false;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                RenderUsers dialog = new RenderUsers(null, UserType.MEDICAL_EMPLOYEE, null, null, false);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public RenderUsers(ArrayList<? extends User> users, UserType userTypeToRender, UserSelectionCallback callback, CardPressedCallback onPressedCardCallback, boolean creationAvailable) {
        this.users = new ArrayList<>(users);
        this.callback = callback;
        this.onPressedCardCallback = onPressedCardCallback;
        this.userTypeToRender = userTypeToRender;
        this.creationAvailable = creationAvailable;
        initializeUI();
        renderCards(userTypeToRender);
    }

    private void initializeUI() {
    	if(userTypeToRender == UserType.PATIENT)
    		lblTitle.setText("Pacientes");
    	else if(userTypeToRender == UserType.MEDICAL_EMPLOYEE)
    		lblTitle.setText("Doctores");
    	else 
    		lblTitle.setText("Empleados");
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
        
        if(onPressedCardCallback != null) {
        	okButton.setVisible(false);
        	cancelButton.setText("Cerrar");
        }
        if(creationAvailable == false) {
        	createUserBtn.setVisible(false);
            deleteUserBtn.setEnabled(false);
        	deleteUserBtn.setVisible(false);
        }
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
        
        deleteUserBtn.addActionListener(e -> deleteUser());
        
        buttonPane.add(deleteUserBtn);
        
        buttonPane.add(createUserBtn);
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);

        return buttonPane;
    }
    
    private void refreshCardsPanel() {
        cardsPanel.removeAll();
        renderCards(userTypeToRender);
        cardsPanel.revalidate();
        cardsPanel.repaint();
    }

    private void deleteUser() {
        try {
            if(selectedUser == null)
                return;
            
            System.out.println(selectedUser.getUserName());
            if(userTypeToRender == UserType.PATIENT)
                HospitalController.getInstance().removePatient(selectedUser.getId());
            else
                HospitalController.getInstance().removeEmployee(selectedUser.getId());
            
            users.remove(selectedUser);
            selectedUser = null;
            selectedCard = null;
            okButton.setEnabled(false);
            deleteUserBtn.setEnabled(false);
            
            refreshCardsPanel();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addUser(User user) {
        users.add(user);
        refreshCardsPanel();
    }

    private void renderCards(UserType userTypeToRender) {
        cardsPanel.removeAll();
        for (User user : users) {
            UserCard card = createCardForUser(user, userTypeToRender);
            card.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (onPressedCardCallback != null) {
                        onPressedCardCallback.onCardPressed(user); 
                    }
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
            deleteUserBtn.setSelected(false);
        }
        if (selectedCard != card) {
            card.setSelected(true);
            selectedCard = card;
            selectedUser = user;
            okButton.setEnabled(true);
            deleteUserBtn.setEnabled(true);
        } else {
            selectedCard = null;
            selectedUser = null;
            okButton.setEnabled(false);
            deleteUserBtn.setEnabled(false);
        }
    }
}