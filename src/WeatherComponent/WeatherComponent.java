package WeatherComponent;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
    private TransparentButton changeCityButton;
    private JTextField textField;
    private MyButton confirmButton;
    private UpdateButton updateButton;

    public WeatherComponent() {
        initComponents();

        setLayout(null);

        setMinimumSize(new Dimension(380,220));
        setMaximumSize(new Dimension(380,220));
        setPreferredSize(new Dimension(380,220));
        setSize(new Dimension(380,220));
        setBorder(new EtchedBorder());
        setBackground(new Color(104,73,140));
        setVisible(true);
    }

    public void initComponents(){
        dayForecast = new DayForecast();
        System.out.println(dayForecast.toString());

        textField = new JTextField(dayForecast.cityName);
        textField.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                if(textField.getText().isEmpty()) {
                    textField.setText(dayForecast.cityName);
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if(textField.getText().equals(dayForecast.cityName)) {
                    textField.setText("");
                }
            }
        });

        textField.setVisible(false);

        confirmButton = new MyButton("Szukaj");
        confirmButton.setVisible(false);

        cityNameLabel = new JLabel(dayForecast.cityName, SwingConstants.LEFT);
        cityNameLabel.setFont(new Font("Open-Sans", Font.BOLD, 30));
        cityNameLabel.setForeground(Color.white);
        cityNameLabel.setBounds(20,15,cityNameLabel.getPreferredSize().width,35);
        add(cityNameLabel);

        changeCityButton = new TransparentButton("(Zmień miasto)");
        changeCityButton.setHorizontalAlignment(SwingConstants.LEFT);
        changeCityButton.setFont(new Font("Open-Sans", Font.BOLD, 12));
        changeCityButton.setBounds((cityNameLabel.getWidth()+30),32,changeCityButton.getPreferredSize().width,12);
        changeCityButton.setVisible(true);
        changeCityButton.addActionListener(e -> changeCity());
        add(changeCityButton);

        administrativeAreaLabel = new JLabel(("Województwo: " + dayForecast.administrativeArea), SwingConstants.LEFT);
        administrativeAreaLabel.setFont(new Font("Open-Sans", Font.BOLD, 12));
        administrativeAreaLabel.setForeground(Color.white);
        administrativeAreaLabel.setBounds(20,55,260,14);
        add(administrativeAreaLabel);

        dayIcon = new JLabel(loadImage("/img/1-s.png"));
        dayIcon.setBounds(20,80,75,45);
        add(dayIcon);

        dayTempLabel = new JLabel((String.valueOf(dayForecast.dayTemp) + "°" + "C"), SwingConstants.LEFT);
        dayTempLabel.setFont(new Font("Open-Sans", Font.BOLD, 36));
        dayTempLabel.setForeground(Color.white);
        dayTempLabel.setBounds(100,75,dayTempLabel.getPreferredSize().width,45);
        add(dayTempLabel);

        dayFeelTempLabel = new JLabel(("(Odczuwalna: " + String.valueOf(dayForecast.feelDayTemp) + "°" + "C)"), SwingConstants.LEFT);
        dayFeelTempLabel.setFont(new Font("Open-Sans", Font.BOLD, 12));
        dayFeelTempLabel.setForeground(Color.white);
        dayFeelTempLabel.setBounds((dayTempLabel.getLocation().x + dayTempLabel.getPreferredSize().width + 10),97,dayFeelTempLabel.getPreferredSize().width,18);
        add(dayFeelTempLabel);

        nightIcon = new JLabel(loadImage("/img/small_moon.png"));
        nightIcon.setBounds(20,126,30,18);
        nightIcon.setHorizontalAlignment(SwingConstants.CENTER);
        add(nightIcon);

        nightTempLabel = new JLabel("Temp. nocna: " + (String.valueOf(dayForecast.nightTemp) + "°" + "C"), SwingConstants.LEFT);
        nightTempLabel.setFont(new Font("Open-Sans", Font.BOLD, 12));
        nightTempLabel.setForeground(Color.white);
        nightTempLabel.setBounds(55,125,130,18);
        add(nightTempLabel);

        precipitationIcon = new JLabel(loadImage(dayForecast.getPrecipitationImg()));
        precipitationIcon.setBounds(20,149,30,18);
        add(precipitationIcon);

        precipitationLabel = new JLabel(dayForecast.getPrecipitationText());
        precipitationLabel.setFont(new Font("Open-Sans", Font.BOLD, 12));
        precipitationLabel.setForeground(Color.white);
        precipitationLabel.setBounds(55,149,220,18);
        add(precipitationLabel);

        windIcon = new JLabel(loadImage("/img/wind.png"));
        windIcon.setBounds(20,172,32,18);
        add(windIcon);

        windLabel = new JLabel("Prędkość wiatru: "
                + dayForecast.windSpeed
                + "km/h, Kierunek: " + dayForecast.windDirect);
        windLabel.setFont(new Font("Open-Sans", Font.BOLD, 12));
        windLabel.setForeground(Color.white);
        windLabel.setBounds(55,172,windLabel.getPreferredSize().width,18); //obliczam długość etykiety na na podstawie długości tekstu
        add(windLabel);

        arrowIcon = new JLabel(loadImage("/img/NE.png"));
        arrowIcon.setBounds(windLabel.getX()+windLabel.getWidth()+5,172,21,18); //pozycja zależna od windLabel
        add(arrowIcon);

        updateButton = new UpdateButton(loadImage("/img/update2.png"),loadImage("/img/update2_light.png"));
        updateButton.setBounds(353,195,17,14);
        updateButton.addActionListener(e -> updateComponents(dayForecast.cityName));
        add(updateButton);

        try {
            dayIcon.setIcon(loadImage(dayForecast.getDayIcon()));
            arrowIcon.setIcon(loadImage(dayForecast.getArrowWind()));
        }
        catch(Exception e){

        }
    }

    public void updateComponents(String city){
        dayForecast = AccuWeatherData.getCurrentForecast(city);

        cityNameLabel.setText(dayForecast.cityName);
        cityNameLabel.setBounds(20,15,cityNameLabel.getPreferredSize().width,35);

        administrativeAreaLabel.setText("Województwo: " + dayForecast.administrativeArea);

        dayTempLabel.setText(String.valueOf(dayForecast.dayTemp) + "°" + "C");
        dayTempLabel.setBounds(100,75,dayTempLabel.getPreferredSize().width,45);

        dayFeelTempLabel.setText("(Odczuwalna: " + String.valueOf(dayForecast.feelDayTemp) + "°" + "C)");
        dayFeelTempLabel.setBounds((dayTempLabel.getLocation().x + dayTempLabel.getPreferredSize().width + 10),97,dayFeelTempLabel.getPreferredSize().width,18);

        nightTempLabel.setText("Temp. nocna: " + (String.valueOf(dayForecast.nightTemp) + "°" + "C"));

        precipitationLabel.setText(dayForecast.getPrecipitationText());

        windLabel.setText("Prędkość wiatru: " + dayForecast.windSpeed + "km/h, Kierunek: " + dayForecast.windDirect);

        textField.setVisible(false);
        confirmButton.setVisible(false);

        changeCityButton.setBounds((cityNameLabel.getWidth()+30),32,100,12);
        changeCityButton.setVisible(true);

        try {
            dayIcon.setIcon(loadImage(dayForecast.getDayIcon()));
            precipitationIcon.setIcon(loadImage(dayForecast.getPrecipitationImg()));
            arrowIcon.setIcon(loadImage(dayForecast.getArrowWind()));
        }
        catch(Exception e){

        }

        this.revalidate();
        this.repaint();
    }

    public void changeCity(){
        changeCityButton.setVisible(false);
        textField.setVisible(true);
        textField.setBounds((cityNameLabel.getLocation().x + cityNameLabel.getPreferredSize().width+10),25,110,20);
        textField.setBorder(BorderFactory.createCompoundBorder(null, BorderFactory.createEmptyBorder(0, 0, 5, 5)));
        add(textField);

        confirmButton.setVisible(true);
        confirmButton.setBounds((textField.getLocation().x + textField.getWidth())+10, 25, 60, 20);
        confirmButton.addActionListener(e -> updateComponents(textField.getText()));
        add(confirmButton);

        this.revalidate();
        this.repaint();
    }

    public ImageIcon loadImage(String url){
        return new ImageIcon(Toolkit.getDefaultToolkit().getImage(WeatherComponent.class.getResource((url))));
    }
}