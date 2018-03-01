package WeatherComponent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class AccuWeatherData {

    private static DayForecast dayForecast = new DayForecast();

    public static DayForecast getCurrentForecast(String cityName){

        /* Próba wyszukania i zwrócenia kodu miasta do zmiennej dayForecast.cityKey */
        try {

            URL url = new URL("http://dataservice.accuweather.com/locations/v1/cities/search?apikey=RuxvrrM8qDkPm0uddr5gAaopFROpthJf&q=" + convertToHTML(cityName) + "&language=pl&details=false");
            URLConnection conn = url.openConnection();
            BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            JSONArray arr = new JSONArray(input.readLine());
            dayForecast.cityKey = arr.getJSONObject(0).get("Key").toString();
            dayForecast.cityName = arr.getJSONObject(0).get("LocalizedName").toString();
            dayForecast.administrativeArea = arr.getJSONObject(0).getJSONObject("AdministrativeArea").get("LocalizedName").toString();

            System.out.println("Klucz: " + dayForecast.cityKey + ", miasto: " + dayForecast.cityName + ", województwo: " + dayForecast.administrativeArea);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        if(dayForecast.cityKey!=null & dayForecast.cityName!=null & dayForecast.administrativeArea != null){
            try {
                URL url = new URL("http://dataservice.accuweather.com/forecasts/v1/daily/1day/" + dayForecast.cityKey + "?apikey=RuxvrrM8qDkPm0uddr5gAaopFROpthJf&language=pl&details=true&metric=true");
                URLConnection conn = url.openConnection();
                BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                JSONObject obj = new JSONObject(input.readLine());

                dayForecast.warning = obj.getJSONObject("Headline").get("Text").toString();
                dayForecast.category = obj.getJSONObject("Headline").get("Category").toString();

                JSONArray array = obj.getJSONArray("DailyForecasts");

                dayForecast.date = array.getJSONObject(0).get("Date").toString();
                dayForecast.iconNum = Integer.parseInt(array.getJSONObject(0).getJSONObject("Day").get("Icon").toString());
                dayForecast.phrase = array.getJSONObject(0).getJSONObject("Day").get("IconPhrase").toString();
                dayForecast.sunRise = array.getJSONObject(0).getJSONObject("Sun").get("Rise").toString();
                dayForecast.sunSet = array.getJSONObject(0).getJSONObject("Sun").get("Set").toString();
                dayForecast.dayTemp = Double.parseDouble(array.getJSONObject(0).getJSONObject("Temperature").getJSONObject("Maximum").get("Value").toString());
                dayForecast.nightTemp = Double.parseDouble(array.getJSONObject(0).getJSONObject("Temperature").getJSONObject("Minimum").get("Value").toString());
                dayForecast.feelDayTemp = Double.parseDouble(array.getJSONObject(0).getJSONObject("RealFeelTemperature").getJSONObject("Maximum").get("Value").toString());
                dayForecast.feelNightTemp = Double.parseDouble(array.getJSONObject(0).getJSONObject("RealFeelTemperature").getJSONObject("Minimum").get("Value").toString());
                dayForecast.windSpeed = Double.parseDouble(array.getJSONObject(0).getJSONObject("Day").getJSONObject("Wind").getJSONObject("Speed").get("Value").toString());
                dayForecast.windDirect = array.getJSONObject(0).getJSONObject("Day").getJSONObject("Wind").getJSONObject("Direction").get("Localized").toString();
                dayForecast.snow = Double.parseDouble(array.getJSONObject(0).getJSONObject("Day").getJSONObject("Snow").get("Value").toString());
                dayForecast.rain = Double.parseDouble(array.getJSONObject(0).getJSONObject("Day").getJSONObject("Rain").get("Value").toString());
                dayForecast.saveValues();

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return dayForecast;
        }
        else {
            System.out.println("Wystąpił błąd przy wyszukiwaniu miasta!");
        }
        return null;
    }

    private static String convertToHTML(String str){
        String result = "";
        for(char c : str.toCharArray()){
            if(c == 'ł') result += "%C5%82";
            else if(c == 'Ł') result += "%C5%81";
            else if(c == 'ą') result += "%C4%85";
            else if(c == 'Ą') result += "%C4%84";
            else if(c == 'ę') result += "%C4%99";
            else if(c == 'Ę') result += "%C4%98";
            else if(c == 'ć') result += "%C4%87";
            else if(c == 'Ć') result += "%C4%86";
            else if(c == 'ó') result += "%C3%94";
            else if(c == 'Ó') result += "%C3%93";
            else if(c == 'ń') result += "%C5%84";
            else if(c == 'Ń') result += "%C5%83";
            else if(c == 'ś') result += "%C5%9B";
            else if(c == 'Ś') result += "%C5%9A";
            else if(c == 'ż') result += "%C5%BC";
            else if(c == 'Ż') result += "%C5%BB";
            else if(c == 'ź') result += "%C5%BA";
            else if(c == 'Ź') result += "%C5%B9";
            else result += c;
        }
        return result;
    }
}
