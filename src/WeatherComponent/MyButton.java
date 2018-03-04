package WeatherComponent;

import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {

    private Color hoverBackgroundColor = new Color(44,20,116);
    private Color pressedBackgroundColor = new Color(54,30,126);

    public MyButton() {
        this(null);
    }

    public MyButton(String text) {
        super(text);
        super.setContentAreaFilled(false);
        setBackground(new Color(34,10,106));
        setForeground(Color.white);
        setBorder(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(pressedBackgroundColor);
        } else if (getModel().isRollover()) {
            g.setColor(hoverBackgroundColor);
        } else {
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

}