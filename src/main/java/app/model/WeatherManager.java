package app.model;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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
        this.apiKey = "yourApiKey";
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

    public void getWeather(){
        int d = 0;
        JSONObject jsonObject;
        JSONObject jsonData;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();

        try{
            jsonObject = readJsonFromUrl("http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+apiKey+"&lang=eng&units=metric");
        }catch (IOException e){
            return;
        }

        jsonData = jsonObject.getJSONObject("main");
        this.temperature = jsonData.getInt("temp");
        this.pressure = jsonData.get("pressure").toString();
        this.humidity = jsonData.get("humidity").toString();
        jsonData = jsonObject.getJSONObject("wind");
        this.windSpeed = jsonData.get("wind").toString();
        jsonData = jsonObject.getJSONObject("clouds");
        this.cloudiness = jsonData.get("all").toString();

        calendar.add(Calendar.DATE,d);
        this.day = simpleDateFormat.format(calendar.getTime());

        jsonData = jsonObject.getJSONArray("weather").getJSONObject(0);
        this.description = jsonData.get("description").toString();
        this.icon = jsonData.get("icon").toString();
    }


}
