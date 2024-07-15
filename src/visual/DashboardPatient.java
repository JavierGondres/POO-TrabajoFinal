package visual;
import javax.swing.*;
import visual.components.RoundedPanel;
import java.awt.*;

public class DashboardPatient {
    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                DashboardPatient window = new DashboardPatient();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * @wbp.parser.entryPoint
     */
    public DashboardPatient() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1120, 743);
        frame.setLocationRelativeTo(null);

        // Usar GridBagLayout para el contenido principal
        frame.getContentPane().setLayout(new GridBagLayout());

        // Panel deslizante
        JPanel sliderPanel = new JPanel(new GridBagLayout());
        sliderPanel.setBackground(Color.PINK);
        GridBagConstraints gbcSlider = new GridBagConstraints();
        gbcSlider.fill = GridBagConstraints.BOTH;
        gbcSlider.insets = new Insets(5, 5, 5, 5);
        gbcSlider.gridx = 0;
        gbcSlider.gridy = 0;
        gbcSlider.weightx = 0.1;
        gbcSlider.weighty = 1.0;
        frame.getContentPane().add(sliderPanel, gbcSlider);

        // Title Container
        JPanel titleContainer = new JPanel(new GridBagLayout());
        titleContainer.setOpaque(false);
        GridBagConstraints gbcTitle = new GridBagConstraints();
        gbcTitle.fill = GridBagConstraints.HORIZONTAL;
        gbcTitle.anchor = GridBagConstraints.NORTH;
        gbcTitle.weightx = 1.0;
        gbcTitle.weighty = 0.0;
        gbcTitle.gridx = 0;
        gbcTitle.gridy = 0;
        gbcTitle.insets = new Insets(10, 10, 10, 10);
        sliderPanel.add(titleContainer, gbcTitle);

        JLabel title = new JLabel("Hospital");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        titleContainer.add(title, new GridBagConstraints());

        // Buttons Container
        JPanel buttonsContainer = new JPanel(new GridBagLayout());
        buttonsContainer.setOpaque(false);
        GridBagConstraints gbcButtons = new GridBagConstraints();
        gbcButtons.fill = GridBagConstraints.BOTH;
        gbcButtons.weightx = 1.0;
        gbcButtons.weighty = 1.0;
        gbcButtons.gridx = 0;
        gbcButtons.gridy = 1;
        gbcButtons.insets = new Insets(10, 10, 10, 10);
        sliderPanel.add(buttonsContainer, gbcButtons);
        
        // Aquí puedes agregar tus botones al buttonsContainer
        JButton button1 = new JButton("Botón 1");
        GridBagConstraints gbc_button1 = new GridBagConstraints();
        gbc_button1.fill = GridBagConstraints.HORIZONTAL; // Hace que el botón se estire horizontalmente
        gbc_button1.gridwidth = GridBagConstraints.REMAINDER; // Hace que el botón ocupe toda la fila
        gbc_button1.weightx = 1.0; // Da al botón todo el espacio horizontal extra
        gbc_button1.insets = new Insets(5, 5, 5, 0); // Añade un pequeño margen alrededor del botón
        gbc_button1.gridx = 0;
        gbc_button1.gridy = 0;
        buttonsContainer.add(button1, gbc_button1);
        
        JButton button2 = new JButton("Botón 2");
        GridBagConstraints gbc_button2 = new GridBagConstraints();
        gbc_button2.fill = GridBagConstraints.HORIZONTAL;
        gbc_button2.gridwidth = GridBagConstraints.REMAINDER;
        gbc_button2.weightx = 1.0;
        gbc_button2.insets = new Insets(5, 5, 5, 5);
        gbc_button2.gridx = 0;
        gbc_button2.gridy = 1; // Cambiado de 7 a 1 para ponerlo justo debajo del primer botón
        buttonsContainer.add(button2, gbc_button2);
        
        // Panel redondeado
        RoundedPanel mainPanel = new RoundedPanel();
        mainPanel.setCornerRadius(30);
        mainPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc_mainPanel = new GridBagConstraints();
        gbc_mainPanel.fill = GridBagConstraints.BOTH;
        gbc_mainPanel.insets = new Insets(5, 5, 5, 5);
        gbc_mainPanel.gridx = 1;
        gbc_mainPanel.gridy = 0;
        gbc_mainPanel.weightx = 0.8;
        gbc_mainPanel.weighty = 1.0;
        frame.getContentPane().add(mainPanel, gbc_mainPanel);
    }
}