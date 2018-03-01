package WeatherComponent;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

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
        loadValues();
    }

    public void loadValues(){
        Properties properties = new Properties();
        try(InputStream stream = Files.newInputStream(Paths.get("forecast.properties"))) {
            properties.load(stream);
            cityKey = properties.getProperty("cityKey");
            cityName = properties.getProperty("cityName");
            administrativeArea = properties.getProperty("administrativeArea");
            warning = properties.getProperty("warning");
            category = properties.getProperty("category");
            date = properties.getProperty("date");
            sunRise = properties.getProperty("sunRise");
            sunSet = properties.getProperty("sunSet");
            nightTemp = Double.parseDouble(properties.getProperty("nightTemp"));
            dayTemp = Double.parseDouble(properties.getProperty("dayTemp"));
            feelDayTemp = Double.parseDouble(properties.getProperty("feelDayTemp"));
            feelNightTemp = Double.parseDouble(properties.getProperty("feelNightTemp"));
            iconNum = Integer.parseInt(properties.getProperty("iconNum"));
            phrase = properties.getProperty("phrase");
            windSpeed = Double.parseDouble(properties.getProperty("windSpeed"));
            windDirect = properties.getProperty("windDirect");
            rain = Double.parseDouble(properties.getProperty("rain"));
            snow = Double.parseDouble(properties.getProperty("snow"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void saveValues(){
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("forecast.properties");
            Properties properties = new Properties();
            properties.setProperty("cityKey", cityKey);
            properties.setProperty("cityName", cityName);
            properties.setProperty("administrativeArea", administrativeArea);
            properties.setProperty("warning", warning);
            properties.setProperty("category", category);
            properties.setProperty("date", date);
            properties.setProperty("sunRise", sunRise);
            properties.setProperty("sunSet", sunSet);
            properties.setProperty("nightTemp", String.valueOf(nightTemp));
            properties.setProperty("dayTemp", String.valueOf(dayTemp));
            properties.setProperty("feelDayTemp", String.valueOf(feelDayTemp));
            properties.setProperty("feelNightTemp", String.valueOf(feelNightTemp));
            properties.setProperty("iconNum", String.valueOf(iconNum));
            properties.setProperty("phrase",phrase);
            properties.setProperty("windSpeed", String.valueOf(windSpeed));
            properties.setProperty("windDirect",windDirect);
            properties.setProperty("rain", String.valueOf(rain));
            properties.setProperty("snow", String.valueOf(snow));
            properties.store(out, null);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPrecipitationImg(){
        if(rain==0 & snow==0) return "rain.png";
        else if(rain!=0 & snow!=0) return "snow_rain.png";
        else if(rain!=0) return "rain.png";
        else return "snow.png";
    }

    public String getPrecipitationText(){
        if(rain==0 & snow==0) return "Opady: brak";
        else if(rain!=0 & snow!=0) return "Opady śniegu z deszczem: " + (rain>snow ? rain : snow) + "mm";
        else if(rain!=0) return "Opady deszczu: " + rain + "mm";
        else return "Opady śniegu: " + snow + "mm";
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
