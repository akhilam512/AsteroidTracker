package com.tracker.asteroid.service;

import com.tracker.asteroid.model.Asteroid;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NasaService {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private OkHttpClient okHttpClient;

    @Autowired
    public NasaService(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    @Value("${nasa.base.url}")
    private String baseUrl;

    public List<Asteroid> getAsteroids(String fromDate) throws IOException, JSONException, ParseException {

        HttpUrl.Builder httpBuilder = HttpUrl.parse(baseUrl).newBuilder();
        httpBuilder.addQueryParameter("start_date", fromDate);
        httpBuilder.addQueryParameter("api_key", "DEMO_KEY");

        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .get()
                .build();
        Response response = okHttpClient.newCall(request).execute();

        String jsonData = response.body().string();
        JSONObject json = new JSONObject(jsonData);
        JSONObject data = (JSONObject) json.get("near_earth_objects");

        return buildAsteroids(data);

    }

    private List<Asteroid> buildAsteroids(JSONObject data) throws ParseException {

        List<Asteroid> asteroids = new ArrayList<>();

        for (String key : data.keySet()) {
            JSONArray day = (JSONArray) data.get(key);

            for (int index = 0; index < day.length(); index++) {
                JSONObject asteroidJson = day.getJSONObject(index);
                String nameOfAsteroid = asteroidJson.getString("name");
                boolean isHazardous = asteroidJson.getBoolean("is_potentially_hazardous_asteroid");

                JSONObject diameterObject = asteroidJson.getJSONObject("estimated_diameter").getJSONObject("kilometers");
                double diameter = diameterObject.getDouble("estimated_diameter_min") + diameterObject.getDouble("estimated_diameter_max");
                diameter /= 2;

                SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
                String date = (String) asteroidJson
                        .getJSONArray("close_approach_data")
                        .getJSONObject(0)
                        .get("close_approach_date");

                Date approach_date = formatter.parse(date);

                Double closest_approach = asteroidJson.getJSONArray("close_approach_data")
                        .getJSONObject(0)
                        .getJSONObject("miss_distance")
                        .getDouble("kilometers");

                Asteroid asteroid = new Asteroid(nameOfAsteroid, approach_date, diameter, closest_approach, isHazardous);
                asteroids.add(asteroid);

            }
        }
        return asteroids;
    }
}
