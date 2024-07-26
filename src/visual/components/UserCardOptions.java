package visual.components;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class UserCardOptions extends JDialog {
    private final JPanel panel = new JPanel();
    private JLabel lblTitle = new JLabel("Opciones");
    private JButton cancelButton = new JButton("Cerrar");
    private ArrayList<JButton> optionButtons;

    public static void main(String[] args) {
        try {
            ArrayList<JButton> buttons = new ArrayList<>();
            UserCardOptions dialog = new UserCardOptions(buttons);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserCardOptions(ArrayList<JButton> optionButtons) {
        this.optionButtons = optionButtons;

        setBounds(100, 100, 522, 357);
        setTitle("Opciones");
        setSize(634, 454);
        setLocationRelativeTo(null);
        setModalityType(ModalityType.APPLICATION_MODAL);

        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());

        JPanel headerPanel = createHeaderPanel();
        JPanel buttonPane = createButtonPane();
        JScrollPane optionsPanel = createOptionsPanel();

        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(optionsPanel, BorderLayout.CENTER);

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

    private JPanel createButtonPane() {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);

        return buttonPane;
    }

    private JScrollPane createOptionsPanel() {
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setBackground(Color.WHITE);
        optionsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        for (JButton button : optionButtons) {
            button.setBackground(Color.decode("#0f1c30"));
            button.setForeground(Color.WHITE);
            button.setBorderPainted(false);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getPreferredSize().height));
            optionsPanel.add(button);
            optionsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        JScrollPane scrollPane = new JScrollPane(optionsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setBackground(Color.WHITE);

        return scrollPane;
    }
}