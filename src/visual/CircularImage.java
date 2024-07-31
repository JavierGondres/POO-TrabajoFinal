package visual;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class CircularImage extends JPanel {
    private BufferedImage originalImage;
    private BufferedImage circularImage;

    public CircularImage(String imagePath) {
        originalImage = loadImage(imagePath);
        if (originalImage != null) {
            // Initialize circularImage with default size
            circularImage = makeCircular(originalImage, getWidth(), getHeight());
        }

        // Add a ComponentListener to handle size changes
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (originalImage != null) {
                    circularImage = makeCircular(originalImage, getWidth(), getHeight());
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (circularImage != null) {
            g.drawImage(circularImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private static BufferedImage loadImage(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(resultingImage, 0, 0, null);
        g2d.dispose();
        return outputImage;
    }

    private static BufferedImage makeCircular(BufferedImage image, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = resizeImage(image, Math.max(targetWidth, 1), Math.max(targetHeight, 1));
        int diameter = Math.max(Math.min(targetWidth, targetHeight), 1);

        BufferedImage masked = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = masked.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Clear with transparency
        g2.setComposite(AlphaComposite.Clear);
        g2.fillRect(0, 0, diameter, diameter);
        g2.setComposite(AlphaComposite.Src);

        // Draw the circular image
        g2.setClip(new java.awt.geom.Ellipse2D.Double(0, 0, diameter, diameter));
        g2.drawImage(resizedImage, 0, 0, diameter, diameter, null);
        g2.dispose();

        return masked;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Circular Image Example");
        CircularImage panel = new CircularImage("C:\\Users\\Scarlet\\Downloads\\cute.png");
        frame.add(panel);
        frame.setSize(300, 300); // Set to desired size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
