package visual.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import javax.swing.JPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RoundedPanel extends JPanel {
    private int[] cornerRadii = {30, 30, 30, 30}; // Top-left, top-right, bottom-right, bottom-left
    private Color backgroundColor = Color.LIGHT_GRAY;
    private Color hoverColor = new Color(200, 200, 200);
    private boolean shadowEnabled = true;
    private int shadowGap = 5;
    private int shadowOffset = 5;
    private Color shadowColor = new Color(50, 50, 50, 10);
    private int shadowBlur = 20;

    private boolean hovering = false;
    private boolean hoverEnabled = true;
    
    private Timer growTimer;
    private Timer shrinkTimer;
    private final int GROW_STEPS = 5;
    private final int GROW_INTERVAL = 10;
    private final float GROW_FACTOR = 1.1f;
    private Rectangle originalBounds;

    public RoundedPanel() {
        super();
        setOpaque(false);
        setupHoverEffect();
    }

    private void setupHoverEffect() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (hoverEnabled) {
                    hovering = true;
                    if (shrinkTimer != null && shrinkTimer.isRunning()) {
                        shrinkTimer.stop();
                    }
                    startGrowAnimation();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (hoverEnabled) {
                    hovering = false;
                    if (growTimer != null && growTimer.isRunning()) {
                        growTimer.stop();
                    }
                    startShrinkAnimation();
                }
            }
        });
    }

    private void startGrowAnimation() {
        if (originalBounds == null) {
            originalBounds = getBounds();
        }

        final Rectangle targetBounds = new Rectangle(
            (int) (originalBounds.x - (originalBounds.width * (GROW_FACTOR - 1) / 2)),
            (int) (originalBounds.y - (originalBounds.height * (GROW_FACTOR - 1) / 2)),
            (int) (originalBounds.width * GROW_FACTOR),
            (int) (originalBounds.height * GROW_FACTOR)
        );

        growTimer = new Timer(GROW_INTERVAL, null);
        growTimer.addActionListener(new ActionListener() {
            int step = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (step < GROW_STEPS) {
                    float progress = (float) step / GROW_STEPS;
                    int newX = (int) (originalBounds.x + (targetBounds.x - originalBounds.x) * progress);
                    int newY = (int) (originalBounds.y + (targetBounds.y - originalBounds.y) * progress);
                    int newWidth = (int) (originalBounds.width + (targetBounds.width - originalBounds.width) * progress);
                    int newHeight = (int) (originalBounds.height + (targetBounds.height - originalBounds.height) * progress);
                    
                    setBounds(newX, newY, newWidth, newHeight);
                    step++;
                } else {
                    ((Timer) e.getSource()).stop();
                }
                repaint();
            }
        });
        growTimer.start();
    }

    private void startShrinkAnimation() {
        if (originalBounds == null) return;

        shrinkTimer = new Timer(GROW_INTERVAL, null);
        shrinkTimer.addActionListener(new ActionListener() {
            int step = GROW_STEPS;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (step > 0) {
                    float progress = (float) step / GROW_STEPS;
                    int newX = (int) (getBounds().x + (originalBounds.x - getBounds().x) * (1 - progress));
                    int newY = (int) (getBounds().y + (originalBounds.y - getBounds().y) * (1 - progress));
                    int newWidth = (int) (getBounds().width + (originalBounds.width - getBounds().width) * (1 - progress));
                    int newHeight = (int) (getBounds().height + (originalBounds.height - getBounds().height) * (1 - progress));
                    
                    setBounds(newX, newY, newWidth, newHeight);
                    step--;
                } else {
                    setBounds(originalBounds);
                    ((Timer) e.getSource()).stop();
                }
                repaint();
            }
        });
        shrinkTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (shadowEnabled) {
            graphics.setColor(shadowColor);
            for (int i = 0; i < shadowBlur; i++) {
                int currentOffset = shadowOffset + i;
                int alpha = (int) (shadowColor.getAlpha() * (1 - (double) i / shadowBlur));
                Color currentShadowColor = new Color(shadowColor.getRed(), shadowColor.getGreen(), shadowColor.getBlue(), alpha);
                graphics.setColor(currentShadowColor);
                graphics.fill(createRoundedShape(currentOffset, currentOffset, width - shadowGap - 2 * i, height - shadowGap - 2 * i));
            }
        }

        if (hovering && hoverEnabled) {
            graphics.setColor(hoverColor);
        } else {
            graphics.setColor(backgroundColor);
        }
        graphics.fill(createRoundedShape(0, 0, width - (shadowEnabled ? shadowGap : 0), height - (shadowEnabled ? shadowGap : 0)));
    }

    private Shape createRoundedShape(int x, int y, int width, int height) {
        Path2D path = new Path2D.Double();
        path.moveTo(x + cornerRadii[0], y);
        path.lineTo(x + width - cornerRadii[1], y);
        path.quadTo(x + width, y, x + width, y + cornerRadii[1]);
        path.lineTo(x + width, y + height - cornerRadii[2]);
        path.quadTo(x + width, y + height, x + width - cornerRadii[2], y + height);
        path.lineTo(x + cornerRadii[3], y + height);
        path.quadTo(x, y + height, x, y + height - cornerRadii[3]);
        path.lineTo(x, y + cornerRadii[0]);
        path.quadTo(x, y, x + cornerRadii[0], y);
        path.closePath();
        return path;
    }
    
    public void setCornerRadii(int topLeft, int topRight, int bottomRight, int bottomLeft) {
        this.cornerRadii = new int[]{topLeft, topRight, bottomRight, bottomLeft};
        repaint();
    }

    public void setCornerRadius(int index, int radius) {
        if (index >= 0 && index < 4) {
            this.cornerRadii[index] = radius;
            repaint();
        }
    }

    public int getCornerRadius(int index) {
        if (index >= 0 && index < 4) {
            return this.cornerRadii[index];
        }
        return 0;
    }

    public int[] getCornerRadii() {
        return cornerRadii.clone();
    }

    @Override
    public void setBackground(Color color) {
        this.backgroundColor = color;
        repaint();
    }

    public void setHoverColor(Color color) {
        this.hoverColor = color;
        repaint();
    }

    public Color getHoverColor() {
        return hoverColor;
    }

    public void setShadowEnabled(boolean enabled) {
        this.shadowEnabled = enabled;
        repaint();
    }

    public boolean isShadowEnabled() {
        return shadowEnabled;
    }

    public void setShadowGap(int gap) {
        this.shadowGap = gap;
        repaint();
    }

    public int getShadowGap() {
        return shadowGap;
    }

    public void setShadowOffset(int offset) {
        this.shadowOffset = offset;
        repaint();
    }

    public int getShadowOffset() {
        return shadowOffset;
    }

    public void setShadowColor(Color color) {
        this.shadowColor = color;
        repaint();
    }

    public Color getShadowColor() {
        return shadowColor;
    }

    public void setShadowBlur(int blur) {
        this.shadowBlur = blur;
        repaint();
    }

    public int getShadowBlur() {
        return shadowBlur;
    }

    public void setHoverEnabled(boolean enabled) {
        this.hoverEnabled = enabled;
    }

    public boolean isHoverEnabled() {
        return hoverEnabled;
    }
}