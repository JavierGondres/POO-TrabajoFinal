package visual.components;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class CustomTextField extends JTextField {
	  private static final int ARC = 15;
	    private ImageIcon icon;
	    private Color backgroundColor;
	    private Color disabledColor = new Color(220, 220, 220); 

    public CustomTextField(ImageIcon icon) {
        this();
        this.icon = icon;
        setupIconPanel();
    }

    public CustomTextField() {
        super();
        this.backgroundColor = new Color(245, 245, 245);
        setupTextField();
    }

    private void setupTextField() {
        setOpaque(false);
        setBackground(backgroundColor);
        setLayout(new BorderLayout());

        Border roundedBorder = new RoundedCornerBorder();
        Border paddingBorder = BorderFactory.createEmptyBorder(0, 5, 0, 5);
        setBorder(BorderFactory.createCompoundBorder(roundedBorder, paddingBorder));
    }

    private void setupIconPanel() {
        if (icon != null) {
            JPanel iconPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    int y = (getHeight() - icon.getIconHeight()) / 2;
                    icon.paintIcon(this, g, 10, y);
                }

                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(icon.getIconWidth() + 10, getHeight());
                }
            };
            iconPanel.setOpaque(false);
            add(iconPanel, BorderLayout.EAST);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        Color bgColor = isEnabled() && isEditable() ? getBackground() : disabledColor;
        
        g2.setColor(bgColor);
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, ARC, ARC));
        
        int iconWidth = (icon != null) ? icon.getIconWidth() + 25 : 0;
        Shape oldClip = g2.getClip();
        g2.setClip(new Rectangle(0, 0, getWidth() - iconWidth, getHeight()));
        
        super.paintComponent(g2);
        
        g2.setClip(oldClip);
        g2.dispose();
    }
    private class RoundedCornerBorder extends AbstractBorder {
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getForeground());
            g2.drawRoundRect(x, y, width - 1, height - 1, ARC, ARC);
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(4, 8, 4, 8);
        }
    }
}