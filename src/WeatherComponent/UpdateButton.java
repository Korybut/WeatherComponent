package WeatherComponent;

import javax.swing.*;
import java.awt.*;

public class UpdateButton extends JButton {

    Icon icon;
    Icon iconLight;

    public UpdateButton(Icon icon, Icon iconLight) {
        super.setContentAreaFilled(false);
        super.setFocusPainted(false);
        super.setIcon(icon);
        this.icon = icon;
        this.iconLight = iconLight;
        setBackground(null);
        setBorder(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isRollover()) {
            g.setColor(new Color(0,0,0,0));
            super.setIcon(iconLight);

        } else {
            g.setColor(getBackground());
            super.setIcon(icon);
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}