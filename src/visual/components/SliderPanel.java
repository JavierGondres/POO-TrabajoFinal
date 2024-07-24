package visual.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URL;
import java.util.ArrayList;

public class SliderPanel extends JPanel {
    private String title;
    private ArrayList<ButtonInfo> buttonInfoList;
    private JPanel buttonsContainer;

    public SliderPanel(String title, ArrayList<ButtonInfo> buttonInfoList) {
        this.title = title;
        this.buttonInfoList = buttonInfoList;
        setBackground(Color.decode("#668dc0"));
        setLayout(null);  // Usar null layout para posicionar elementos manualmente
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
        titleContainer.setBounds(0, 0, 209, 55);
        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
        titleContainer.add(titleLabel);
        add(titleContainer);
    }

    private void createButtonsPanel() {
        buttonsContainer = new JPanel();
        buttonsContainer.setOpaque(false);
        buttonsContainer.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(30, 0, 10, 0);
        for (ButtonInfo buttonInfo : buttonInfoList) {
            JPanel buttonPanel = createButtonWithImage(buttonInfo);
            buttonsContainer.add(buttonPanel, gbc);
        }
        gbc.weighty = 1;
        buttonsContainer.add(Box.createVerticalGlue(), gbc);
        JScrollPane scrollPane = new JScrollPane(buttonsContainer);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0, 55, 209, 849);
        add(scrollPane);
    }

    private JPanel createButtonWithImage(ButtonInfo buttonInfo) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        
        JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        contentPanel.setOpaque(false);
        
        if (buttonInfo.getImagePath() != null && !buttonInfo.getImagePath().isEmpty()) {
            URL imageURL = getClass().getResource(buttonInfo.getImagePath());
            if (imageURL != null) {
                ImageIcon icon = new ImageIcon(imageURL);
                Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                JLabel imageLabel = new JLabel(new ImageIcon(img));
                contentPanel.add(imageLabel);
            }
        }
        
        styleButton(buttonInfo.getButton());
        contentPanel.add(buttonInfo.getButton());
        
        panel.add(contentPanel);
        return panel;
    }

    private void styleButton(JButton button) {
        button.setPreferredSize(new Dimension(100, 40));
        button.setMaximumSize(new Dimension(150, 40));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(102, 141, 192));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);
    }

    public static class ButtonInfo {
        private JButton button;
        private String imagePath;

        public ButtonInfo(JButton button) {
            this(button, null);
        }

        public ButtonInfo(JButton button, String imagePath) {
            this.button = button;
            this.imagePath = imagePath;
        }

        public JButton getButton() {
            return button;
        }

        public String getImagePath() {
            return imagePath;
        }
    }
}
