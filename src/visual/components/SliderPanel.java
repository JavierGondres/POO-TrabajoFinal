package visual.components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SliderPanel extends JPanel {
    private String title;
    private ArrayList<JButton> buttons;
    private JPanel buttonsContainer;

    public SliderPanel(String title, ArrayList<JButton> buttons) {
        this.title = title;
        this.buttons = buttons;
        setBackground(Color.decode("#668dc0"));
        setLayout(new BorderLayout());
        setBounds(5, 5, 209, 904);
        
        generateContent();
    }
    
    private void generateContent() {
        createTitlePanel();
        createButtonsPanel();
    }

    private void createTitlePanel() {
        JPanel titleContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titleContainer.setOpaque(false);
        titleContainer.setPreferredSize(new Dimension(197, 55));
        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
        titleContainer.add(titleLabel);
        add(titleContainer, BorderLayout.NORTH);
    }

    private void createButtonsPanel() {
        buttonsContainer = new JPanel();
        buttonsContainer.setOpaque(false);
        buttonsContainer.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 0, 10, 0);

        for (JButton button : buttons) {
            styleButton(button);
            buttonsContainer.add(button, gbc);
        }

        gbc.weighty = 1;
        buttonsContainer.add(Box.createVerticalGlue(), gbc);

        JScrollPane scrollPane = new JScrollPane(buttonsContainer);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void styleButton(JButton button) {
        button.setPreferredSize(new Dimension(180, 40));
        button.setMaximumSize(new Dimension(180, 40));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(102, 141, 192));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}