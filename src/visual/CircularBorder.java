package visual;

import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

public class CircularBorder extends AbstractBorder {
    private static final long serialVersionUID = 1L;

    private Color borderColor;
    private int thickness;
    private int radius;

    public CircularBorder(Color borderColor, int thickness, int radius) {
        this.borderColor = borderColor;
        this.thickness = thickness;
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int adjustedWidth = width - 1;
        int adjustedHeight = height - 1;

        // Define the rounded rectangle for the border
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(
            x + thickness / 2f, y + thickness / 2f,
            adjustedWidth - thickness, adjustedHeight - thickness,
            radius, radius);

        // Clip the area to be painted to avoid drawing outside the rounded rectangle
        Area clip = new Area(new Rectangle(x, y, width, height));
        clip.subtract(new Area(roundedRectangle));
        g2d.setClip(clip);

        // Fill the clipped area with the parent background color
        g2d.setColor(c.getParent().getBackground());
        g2d.fillRect(x, y, width, height);

        // Reset the clip and draw the rounded rectangle border
        g2d.setClip(null);
        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(thickness));
        g2d.draw(roundedRectangle);

        g2d.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        int padding = thickness + radius / 2;
        return new Insets(padding, padding, padding, padding);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        int padding = thickness + radius / 2;
        insets.left = insets.right = insets.top = insets.bottom = padding;
        return insets;
    }
}
