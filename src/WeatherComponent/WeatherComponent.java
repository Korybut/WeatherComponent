package WeatherComponent;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.io.Serializable;

public class WeatherComponent extends JPanel implements Serializable {

    public WeatherComponent() {
        AccuWeatherData.getCurrentForecast("274663");
        setSize(new Dimension(380,220));
        setBorder(new EtchedBorder());
        setBackground(new Color(104,73,140));
        setVisible(true);
    }


}
