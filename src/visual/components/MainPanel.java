package visual.components;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends RoundedPanel {
    public MainPanel() {
        setBounds(262, 5, 1096, 904);
        setShadowEnabled(false);
        setHoverEnabled(false);
        setShadowBlur(100);
        setShadowOffset(0);
        setShadowColor(Color.LIGHT_GRAY);
        setCornerRadii(30, 30, 30, 30);
        setBackground(Color.WHITE);
        setLayout(null);
    }
}