package visual.utils;

import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

public class CircularBorder extends AbstractBorder {
    /**
	 * 
	 */
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

        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(
            x + thickness / 2f, y + thickness / 2f,
            width - thickness, height - thickness,
            radius, radius);

        Area clip = new Area(new Rectangle(0, 0, width, height));
        clip.subtract(new Area(roundedRectangle));

        g2d.setClip(clip);
        g2d.setColor(c.getParent().getBackground());
        g2d.fillRect(x, y, width, height);

        g2d.setClip(null);

        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(thickness));
        g2d.draw(roundedRectangle);

        g2d.dispose();
    }
}