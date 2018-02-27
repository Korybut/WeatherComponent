package WeatherComponent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class AccuWeatherData {

    private static DayForecast dayForecast = new DayForecast();

    public static String[] getCurrentForecast(String cityName){

        String cityKey = null;

        /* Próba wyszukania i zwrócenia kodu miasta do zmiennej cityKey */
        try {
            URL url = new URL("http://dataservice.accuweather.com/locations/v1/cities/search?apikey=RuxvrrM8qDkPm0uddr5gAaopFROpthJf&q=" + cityName + "&language=pl&details=false");
            URLConnection conn = url.openConnection();
            BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            JSONArray arr = new JSONArray(input.readLine());
            cityKey = arr.getJSONObject(0).getString("Key");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(cityKey!=null){
            try {
                URL url = new URL("http://dataservice.accuweather.com/forecasts/v1/daily/1day/" + cityKey + "?apikey=RuxvrrM8qDkPm0uddr5gAaopFROpthJf&language=pl&details=false&metric=true");
                URLConnection conn = url.openConnection();
                BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                JSONObject obj = new JSONObject(input.readLine());

                String headline = obj.getJSONObject("Headline").getString("EffectiveDate");
                String category = obj.getJSONObject("Headline").getString("Category");

                System.out.println(headline + " \n " + category);

                JSONArray array = obj.getJSONArray("DailyForecasts");
                System.out.println(array.getJSONObject(0).get("Date"));
                System.out.println(array.getJSONObject(0).get("Day"));

                return new String[17];

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        else {
            System.out.println("Wystąpił błąd przy wyszukiwaniu miasta!");
        }
        return null;
    }
}
