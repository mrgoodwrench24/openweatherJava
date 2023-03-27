package Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

public class OpenWeatherMapApi {

    private static String apiKey;
    private static String city;

    private static String getApiKey(){
        File keyReader = new File("src/Model/apiKey.txt");
        try {
            Scanner myReader;
            myReader = new Scanner(keyReader);
            apiKey = myReader.nextLine();
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot file apiKey.txt");
            e.printStackTrace();
        }
        return apiKey;
    }

    public static HashMap getCityInfo(String zipCode) throws IOException {
        getApiKey();
        HashMap<String, String> cityInfo = new HashMap<>();
        try {
            URL url = new URL("http://api.openweathermap.org/geo/1.0/zip?zip=" + zipCode + "%2CUS&appid=" + apiKey);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");


            BufferedReader in = new BufferedReader(new BufferedReader(new InputStreamReader(connection.getInputStream())));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) !=null){
                response.append(inputLine);

            }
            in.close();

            //Parse the JSON response and put the values into the HashMap
            JSONObject jsonObject = new JSONObject(response.toString());
            cityInfo.put("zip", jsonObject.getString("zip"));
            cityInfo.put("name", jsonObject.getString("name"));
            cityInfo.put("lat", Double.toString(jsonObject.getDouble("lat")));
            cityInfo.put("lon", Double.toString(jsonObject.getDouble("lon")));

        } catch (MalformedURLException e){
            System.out.println("Bad URL");
            e.printStackTrace();
        }
        return cityInfo;
    }

    public static HashMap getCurrentConditions(String lat, String lon){
        HashMap<String, Object> weatherInfo = new HashMap<>();
        try{
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=" + apiKey);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new BufferedReader(new InputStreamReader(connection.getInputStream())));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) !=null){
                response.append(inputLine);

            }
            in.close();

            JSONObject jsonObject = new JSONObject(response.toString());

            JSONObject mainObj = jsonObject.getJSONObject("main");
            double tempKelvin = mainObj.getDouble("temp");
            double feelK = mainObj.getDouble("feels_like");
            double minK = mainObj.getDouble("temp_min");
            double maxK = mainObj.getDouble("temp_max");
            weatherInfo.put("temp", kelvinToFarenheit(tempKelvin));
            weatherInfo.put("feels_like", kelvinToFarenheit(feelK));
            weatherInfo.put("temp_min", kelvinToFarenheit(minK));
            weatherInfo.put("temp_max", kelvinToFarenheit(maxK));

            JSONArray weatherArray = jsonObject.getJSONArray("weather");
            JSONObject weather = weatherArray.getJSONObject(0);
            weatherInfo.put("description", weather.getString("description"));
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return weatherInfo;
    }

    private static double kelvinToFarenheit(double temp){
        return ((temp - 273.15) * 9/5 +32);
    }
}
