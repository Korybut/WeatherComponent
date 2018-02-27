import WeatherComponent.WeatherComponent;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String args[]) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.setSize(new Dimension(640,440));
                frame.setLayout(null);
                frame.setLocation(400,200);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                WeatherComponent weatherComponent = new WeatherComponent();
                weatherComponent.setLocation(30,30);
                frame.add(weatherComponent);
                frame.setVisible(true);
            }
        });
    }
}
