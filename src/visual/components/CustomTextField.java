package visual.components;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class CustomTextField extends JTextField {
    private static final int ARC = 15;
    private ImageIcon icon;
    private Color backgroundColor;

    public CustomTextField(ImageIcon icon) {
        super();
        this.icon = icon;
        this.backgroundColor = new Color(245, 245, 245); // Gris claro por defecto
        setupTextField();
    }

    private void setupTextField() {
        setOpaque(false);
        setBackground(backgroundColor);

        // Crear un panel para el icono
        JPanel iconPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (icon != null) {
                    int y = (getHeight() - icon.getIconHeight()) / 2;
                    icon.paintIcon(this, g, 10, y);
                }
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(icon != null ? icon.getIconWidth() + 10 : 10, getHeight());
            }
        };
        iconPanel.setOpaque(false);

        // Configurar el layout
        setLayout(new BorderLayout());
        add(iconPanel, BorderLayout.EAST);

        // Crear un borde compuesto para el padding y los bordes redondeados
        Border roundedBorder = new RoundedCornerBorder();
        Border paddingBorder = BorderFactory.createEmptyBorder(0, 5, 0, 5);
        setBorder(BorderFactory.createCompoundBorder(roundedBorder, paddingBorder));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Dibujar el fondo redondeado
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, ARC, ARC));

        // Calcular el area visible para el texto
        int iconWidth = (icon != null) ? icon.getIconWidth() : 0;
        Shape oldClip = g2.getClip();
        g2.setClip(new Rectangle(0, 0, getWidth() - iconWidth - 25, getHeight()));

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