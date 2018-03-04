package WeatherComponent;

import javax.swing.*;
import java.awt.*;

public class TransparentButton extends JButton {

    public TransparentButton() {
        super.setContentAreaFilled(false);
        super.setFocusPainted(false);
        setBackground(null);
        setBorder(null);
    }

    public TransparentButton(String text) {
        super(text);
        super.setContentAreaFilled(false);
        super.setFocusPainted(false);
        setBackground(null);
        setForeground(Color.white);
        setBorder(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isRollover()) {
            g.setColor(new Color(0,0,0,0));
            this.setFont(new Font(this.getFont().getFamily(), Font.BOLD, this.getFont().getSize()));

        } else {
            g.setColor(getBackground());
            this.setFont(new Font(this.getFont().getFamily(), Font.PLAIN, this.getFont().getSize()));
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}