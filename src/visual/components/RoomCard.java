package visual.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class RoomCard extends RoundedPanel {
	public RoomCard(String patientName, String available, String id) {
        super();
        setFocusable(true);
        setRequestFocusEnabled(true);
        this.setCornerRadii(30, 30, 30, 30);
        this.setBounds(72, 211, 383, 179);
        this.setBackground(Color.decode("#c0d0ef"));
        this.setShadowEnabled(true);
        this.setShadowGap(5);
        this.setShadowOffset(5);
        this.setShadowColor(new Color(50, 50, 50, 10));
        this.setShadowBlur(20);
        this.setHoverColor(Color.decode("#668dc0"));
        this.setHoverEnabled(true);
        this.setLayout(null);

        JLabel lblUserNameQuery = new JLabel(patientName);
        lblUserNameQuery.setBounds(170, 23, 201, 31);
        lblUserNameQuery.setHorizontalAlignment(SwingConstants.CENTER);
        Font newDoctorQueryFont = lblUserNameQuery.getFont().deriveFont(Font.BOLD, 14f);
        lblUserNameQuery.setFont(newDoctorQueryFont);
        this.add(lblUserNameQuery);

        JLabel lblFecha = new JLabel(available);
        lblFecha.setFont(lblFecha.getFont().deriveFont(14f));
        lblFecha.setBounds(170, 67, 201, 31);
        lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lblFecha);

        JLabel lblpmpm = new JLabel(id);
        lblpmpm.setFont(lblpmpm.getFont().deriveFont(14f));
        lblpmpm.setBounds(170, 111, 201, 31);
        lblpmpm.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lblpmpm);

        JPanel imageContainer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    URL imageURL = getClass().getResource("/assets/images/bedroom.png");
                    ImageIcon imageIcon = new ImageIcon(imageURL);
                    Image image = imageIcon.getImage();
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    System.err.println("No se pudo cargar la imagen: " + e.getMessage());
                }
            }
        };
        imageContainer.setOpaque(false);
        imageContainer.setBounds(12, 13, 150, 150);
        this.add(imageContainer);
    }
    
    public void addClickListener(MouseListener listener) {
        this.addMouseListener(listener);
    }
}
