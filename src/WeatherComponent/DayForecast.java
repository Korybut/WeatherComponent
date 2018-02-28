package WeatherComponent;

public class DayForecast {

    public String cityKey;
    public String cityName;
    public String administrativeArea;

    public String warning;
    public String category;
    public String date;
    public String sunRise;
    public String sunSet;
    public double nightTemp;
    public double dayTemp;
    public double feelDayTemp;
    public double feelNightTemp;
    public int iconNum;
    public String phrase;
    public double windSpeed;
    public String windDirect;
    public double rain;
    public double snow;

    public DayForecast() {
        cityKey = null;
        cityName = "Koszalin";
        administrativeArea = "Zachodniopomorskie";
        warning = null;
        category = null;
        date = null;
        sunRise = null;
        nightTemp = -15;
        dayTemp = -11;
        feelDayTemp = 0;
        feelNightTemp = 0;
        iconNum = 6;
        phrase = null;
        windSpeed = 0;
        windDirect = null;
        rain = 0;
        snow = 0;
    }

    @Override
    public String toString() {
        return "DayForecast{" + '\n' +
                "cityKey= " + cityKey + '\n' +
                ", cityName= " + cityName + '\n' +
                ", administrativeArea= " + administrativeArea + '\n' +
                ", warning= " + warning + '\n' +
                ", category= " + category + '\n' +
                ", date= " + date + '\n' +
                ", sunRise= " + sunRise + '\n' +
                ", sunSet= " + sunSet + '\n' +
                ", nightTemp= " + nightTemp + '\n' +
                ", dayTemp= " + dayTemp + '\n' +
                ", feelDayTemp= " + feelDayTemp + '\n' +
                ", feelNightTemp= " + feelNightTemp + '\n' +
                ", iconNum= " + iconNum + '\n' +
                ", phrase= " + phrase + '\n' +
                ", windSpeed= " + windSpeed + '\n' +
                ", windDirect= " + windDirect + '\n' +
                ", rain= " + rain + '\n' +
                ", snow= " + snow + '\n' +
                '}';
    }
}
