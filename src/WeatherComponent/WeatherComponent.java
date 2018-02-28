package WeatherComponent;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class WeatherComponent extends JPanel implements Serializable {

    private DayForecast dayForecast;
    private JLabel cityNameLabel;
    private JLabel administrativeAreaLabel;
    private JLabel dayIcon;
    private JLabel dayTempLabel;
    private JLabel dayFeelTempLabel;
    private JLabel nightIcon;
    private JLabel nightTempLabel;

    public WeatherComponent() {
        dayForecast = new DayForecast();
        //dayForecast = AccuWeatherData.getCurrentForecast("Ustka");
        //System.out.println(dayForecast.toString());

        cityNameLabel = new JLabel(dayForecast.cityName, SwingConstants.LEFT);
        cityNameLabel.setFont(new Font("Open-Sans", Font.BOLD, 30));
        cityNameLabel.setForeground(Color.white);
        cityNameLabel.setBounds(20,15,340,26);
        add(cityNameLabel);

        administrativeAreaLabel = new JLabel(("Województwo: " + dayForecast.administrativeArea), SwingConstants.LEFT);
        administrativeAreaLabel.setFont(new Font("Open-Sans", Font.BOLD, 12));
        administrativeAreaLabel.setForeground(Color.white);
        administrativeAreaLabel.setBounds(20,50,260,14);
        add(administrativeAreaLabel);

        dayIcon = loadImage("img/" + String.format("%02d", dayForecast.iconNum) + "-s.png");
        dayIcon.setBounds(20,75,75,45);
        add(dayIcon);

        dayTempLabel = new JLabel((String.valueOf(dayForecast.dayTemp) + "°" + "C"), SwingConstants.RIGHT);
        dayTempLabel.setFont(new Font("Open-Sans", Font.BOLD, 36));
        dayTempLabel.setForeground(Color.white);
        dayTempLabel.setBounds(100,70,125,45);
        add(dayTempLabel);

        dayFeelTempLabel = new JLabel(("(Odczuwalna: " + String.valueOf(dayForecast.feelDayTemp) + "°" + "C)"), SwingConstants.LEFT);
        dayFeelTempLabel.setFont(new Font("Open-Sans", Font.BOLD, 12));
        dayFeelTempLabel.setForeground(Color.white);
        dayFeelTempLabel.setBounds(230,92,150,18);
        add(dayFeelTempLabel);

        nightIcon = loadImage("img/small_moon.png");
        nightIcon.setBounds(20,121,20,18);
        add(nightIcon);

        nightTempLabel = new JLabel("Temp. nocna: " + (String.valueOf(dayForecast.nightTemp) + "°" + "C"), SwingConstants.LEFT);
        nightTempLabel.setFont(new Font("Open-Sans", Font.BOLD, 12));
        nightTempLabel.setForeground(Color.white);
        nightTempLabel.setBounds(45,120,130,18);
        add(nightTempLabel);



        setLayout(null);
        setSize(new Dimension(380,220));
        setBorder(new EtchedBorder());
        setBackground(new Color(104,73,140));
        setVisible(true);
    }

    public JLabel loadImage(String url){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(url));
            return new JLabel(new ImageIcon(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JLabel();
    }

}
