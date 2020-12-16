package app.model;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by PZON_SM on 11.12.2020.
 **/
public class WeatherManager {
    private String city;
    private String day;
    private String icon;
    private String description;
    private String windSpeed;
    private String pressure;
    private String cloudiness;
    private String humidity;
    private Integer temperature;
    private String apiKey;


    public String getCity() {
        return city;
    }

    public String getDay() {
        return day;
    }

    public String getIcon() {
        return icon;
    }

    public String getDescription() {
        return description;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getPressure() {
        return pressure;
    }

    public String getCloudiness() {
        return cloudiness;
    }

    public String getHumidity() {
        return humidity;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public WeatherManager(String city) {
        this.city = city;
        this.apiKey = "apiKey";
    }

    //Build a String from the read Json file
    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    //Reads and returns the JsonObject
    public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }


}
