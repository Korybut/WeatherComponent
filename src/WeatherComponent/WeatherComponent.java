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
    private JLabel precipitationIcon;
    private JLabel precipitationLabel;
    private JLabel windIcon;
    private JLabel windLabel;
    private JLabel arrowIcon;
    private JLabel arrowLabel;

    public WeatherComponent() {
        dayForecast = new DayForecast();
        dayForecast = AccuWeatherData.getCurrentForecast("Słupsk");
        System.out.println(dayForecast.toString());

        cityNameLabel = new JLabel(dayForecast.cityName, SwingConstants.LEFT);
        cityNameLabel.setFont(new Font("Open-Sans", Font.BOLD, 30));
        cityNameLabel.setForeground(Color.white);
        cityNameLabel.setBounds(20,15,340,35);
        add(cityNameLabel);

        administrativeAreaLabel = new JLabel(("Województwo: " + dayForecast.administrativeArea), SwingConstants.LEFT);
        administrativeAreaLabel.setFont(new Font("Open-Sans", Font.BOLD, 12));
        administrativeAreaLabel.setForeground(Color.white);
        administrativeAreaLabel.setBounds(20,55,260,14);
        add(administrativeAreaLabel);

        dayIcon = loadImage("img/" + String.format("%02d", dayForecast.iconNum) + "-s.png");
        dayIcon.setBounds(20,80,75,45);
        add(dayIcon);

        dayTempLabel = new JLabel((String.valueOf(dayForecast.dayTemp) + "°" + "C"), SwingConstants.RIGHT);
        dayTempLabel.setFont(new Font("Open-Sans", Font.BOLD, 36));
        dayTempLabel.setForeground(Color.white);
        dayTempLabel.setBounds(100,75,125,45);
        add(dayTempLabel);

        dayFeelTempLabel = new JLabel(("(Odczuwalna: " + String.valueOf(dayForecast.feelDayTemp) + "°" + "C)"), SwingConstants.LEFT);
        dayFeelTempLabel.setFont(new Font("Open-Sans", Font.BOLD, 12));
        dayFeelTempLabel.setForeground(Color.white);
        dayFeelTempLabel.setBounds(230,97,150,18);
        add(dayFeelTempLabel);

        nightIcon = loadImage("img/small_moon.png");
        nightIcon.setBounds(20,126,30,18);
        nightIcon.setHorizontalAlignment(SwingConstants.CENTER);
        add(nightIcon);

        nightTempLabel = new JLabel("Temp. nocna: " + (String.valueOf(dayForecast.nightTemp) + "°" + "C"), SwingConstants.LEFT);
        nightTempLabel.setFont(new Font("Open-Sans", Font.BOLD, 12));
        nightTempLabel.setForeground(Color.white);
        nightTempLabel.setBounds(55,125,130,18);
        add(nightTempLabel);

        precipitationIcon = loadImage("img/" + dayForecast.getPrecipitationImg());
        precipitationIcon.setBounds(20,149,30,18);
        add(precipitationIcon);

        precipitationLabel = new JLabel(dayForecast.getPrecipitationText());
        precipitationLabel.setFont(new Font("Open-Sans", Font.BOLD, 12));
        precipitationLabel.setForeground(Color.white);
        precipitationLabel.setBounds(55,149,220,18);
        add(precipitationLabel);

        windIcon = loadImage("img/wind.png");
        windIcon.setBounds(20,172,32,18);
        add(windIcon);

        windLabel = new JLabel("Prędkość wiatru: "
                + dayForecast.windSpeed
                + "km/h, Kierunek: " + dayForecast.windDirect);
        windLabel.setFont(new Font("Open-Sans", Font.BOLD, 12));
        windLabel.setForeground(Color.white);
        windLabel.setBounds(55,172,(windLabel.getText().length()*6),18); //obliczam długość etykiety na na podstawie długości tekstu
        add(windLabel);

        arrowIcon = loadWindArrowImage(dayForecast.windDirect);
        arrowIcon.setBounds(((windLabel.getText().length()*6)+60),172,18,18); //pozycja zależna od windLabel
        add(arrowIcon);

        setLayout(null);
        setSize(new Dimension(380,220));
        setBorder(new EtchedBorder());
        setBackground(new Color(104,73,140));
        setVisible(true);
    }

    public JLabel loadWindArrowImage(String direct){
        if(direct.equals("N")) return loadImage("img/N.png");
        else if(direct.equals("NNE") || direct.equals("NE") || direct.equals("ENE")) return loadImage("img/NE.png");
        else if(direct.equals("E")) return loadImage("img/E.png");
        else if(direct.equals("ESE") || direct.equals("SE") || direct.equals("SSE")) return loadImage("img/ES.png");
        else if(direct.equals("E")) return loadImage("img/S.png");
        else if(direct.equals("SSW") || direct.equals("SW") || direct.equals("WSW")) return loadImage("img/WS.png");
        else if(direct.equals("W")) return loadImage("img/W.png");
        else return loadImage("img/NW.png");
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
