package visual.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserCard extends RoundedPanel {
	
    protected JLabel lblUserName;
    protected JPanel imageContainer;
    protected boolean isSelected = false;
    protected Color defaultTextColor = Color.decode("#0f1c30");
    protected Color hoverTextColor = Color.WHITE;
    
    public UserCard(String profilePicture, String userName) {
        super();
        this.setBounds(12, 77, 220, 322);
        this.setBackground(Color.decode("#c0d0ef"));
        this.setHoverColor(Color.decode("#304a6e"));
        this.setLayout(null);
        
        imageContainer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    URL imageURL = getClass().getResource(profilePicture);
                    ImageIcon imageIcon = new ImageIcon(imageURL);
                    Image image = imageIcon.getImage();
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    System.err.println("No se pudo cargar la imagen: " + e.getMessage());
                }
            }
        };
        imageContainer.setBounds(12, 13, 235, 180);
        imageContainer.setOpaque(false);
        this.add(imageContainer);
        
        lblUserName = new JLabel(userName);
        lblUserName.setBounds(12, 203, 190, 22);
        lblUserName.setForeground(defaultTextColor);
        lblUserName.setFont(lblUserName.getFont().deriveFont(Font.BOLD, 16));
        this.add(lblUserName);
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                toggleSelection();
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                updateTextColor(true);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (!isSelected) {
                    updateTextColor(false);
                }
            }
        });
    }
    
    protected void updateTextColor(boolean isHovering) {
        if (isHovering || isSelected) {
            lblUserName.setForeground(hoverTextColor);
        } else {
            lblUserName.setForeground(defaultTextColor);
        }
    }
    
    public void toggleSelection() {
        isSelected = !isSelected;
        updateSelection();
    }
    
    public boolean isSelected() {
        return isSelected;
    }
    
    public void setSelected(boolean selected) {
        isSelected = selected;
        updateSelection();
    }
    
    private void updateSelection() {
        if (isSelected) {
            setBackground(getHoverColor());
            updateTextColor(true);
        } else {
            setBackground(Color.decode("#c0d0ef"));
            updateTextColor(false);
        }
        repaint();
    }
}