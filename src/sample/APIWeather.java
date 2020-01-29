package sample;

import com.mysql.cj.xdevapi.JsonString;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIWeather {
    static String json = null;

    public Weather getWeather(String city, String code2) {
        Weather weather = null;
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city + "," + code2 + "&units=metric&appid=26f312e1a14ba8902ba63873b2ff0cb2");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() == 200) { // 200 - means successfully return right info
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();

                json = response.toString();
                //JSONArray array_obj = (JSONArray) parser_obj.parse(json);
                //String output = br.readLine();
                System.out.println(response.toString());
                //return weather;

                JSONParser parser_obj = new JSONParser();
                JSONObject myResp = (JSONObject) parser_obj.parse(response.toString());
                JSONObject main = (JSONObject) myResp.get("main");

                double temp = (double) main.get("temp");
                long humidity = (long) main.get("humidity");
                System.out.println("The main temp is: " + temp);
                System.out.println("The main humidity is: " + humidity);
                weather = new Weather(null, null, temp, humidity, 0, 0);
                return weather;
            }
            //connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weather;
    }
}
