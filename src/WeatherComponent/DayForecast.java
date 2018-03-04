package WeatherComponent;

import java.io.*;
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
    public String iconNum;
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
        if(Files.exists(Paths.get("forecast.properties"))) {
            try (InputStream stream = Files.newInputStream(Paths.get("forecast.properties"))) {
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
                iconNum = properties.getProperty("iconNum");
                phrase = properties.getProperty("phrase");
                windSpeed = Double.parseDouble(properties.getProperty("windSpeed"));
                windDirect = properties.getProperty("windDirect");
                rain = Double.parseDouble(properties.getProperty("rain"));
                snow = Double.parseDouble(properties.getProperty("snow"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            File file = new File("forecast.properties");
            try {
                file.createNewFile();
                FileOutputStream out = null;
                out = new FileOutputStream("forecast.properties");
                properties = new Properties();
                properties.setProperty("cityKey", "275110");
                properties.setProperty("cityName", "Bia\\u0142ystok");
                properties.setProperty("administrativeArea", "Podlaskie");
                properties.setProperty("warning", "Cienka warstwa do 1 cm \\u015Bnieg wtorek wiecz\\u00F3r");
                properties.setProperty("category", "snow");
                properties.setProperty("date", "2018-03-03T07\\:00\\:00+01\\:00");
                properties.setProperty("sunRise", "2018-03-03T06\\:11\\:00+01\\:00");
                properties.setProperty("sunSet", "2018-03-03T17\\:09\\:00+01\\:00");
                properties.setProperty("nightTemp", "-12.1");
                properties.setProperty("dayTemp", "-7.6");
                properties.setProperty("feelDayTemp", "-8.9");
                properties.setProperty("feelNightTemp", "-18.7");
                properties.setProperty("iconNum", "31");
                properties.setProperty("phrase","Zimno");
                properties.setProperty("windSpeed", "13.0");
                properties.setProperty("windDirect","N");
                properties.setProperty("rain","0.0");
                properties.setProperty("snow", "0.0");
                properties.store(out, null);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
            properties.setProperty("iconNum", iconNum);
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
        if(rain==0 & snow==0) return "/img/rain.png";
        else if(rain!=0 & snow!=0) return "/img/snow_rain.png";
        else if(rain!=0) return "/img/rain.png";
        else return "/img/snow.png";
    }

    public String getPrecipitationText(){
        if(rain==0 & snow==0) return "Opady: brak";
        else if(rain!=0 & snow!=0) return "Opady śniegu z deszczem: " + (rain>snow ? rain : snow) + "mm";
        else if(rain!=0) return "Opady deszczu: " + rain + "mm";
        else return "Opady śniegu: " + snow + "mm";
    }

    public String getArrowWind(){
        return "/img/" + windDirect + ".png";
    }

    public String getDayIcon(){
        return "/img/" + iconNum + "-s.png";
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