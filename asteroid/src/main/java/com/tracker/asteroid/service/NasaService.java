package com.tracker.asteroid.service;

import com.tracker.asteroid.model.Asteroid;
import com.tracker.asteroid.model.AsteroidDetail;
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
import java.util.*;

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
        httpBuilder.addPathSegment("/feed");
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

    public AsteroidDetail getAsteroidById(String id) throws IOException, JSONException, ParseException {

        HttpUrl.Builder httpBuilder = HttpUrl.parse(baseUrl).newBuilder();
        httpBuilder.addPathSegment("/neo/").addPathSegment(id);
        httpBuilder.addQueryParameter("api_key", "DEMO_KEY");

        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .get()
                .build();
        Response response = okHttpClient.newCall(request).execute();

        String jsonData = response.body().string();
        JSONObject json = new JSONObject(jsonData);


        return buildAsteroidDetail(json);

    }

    private AsteroidDetail buildAsteroidDetail(JSONObject json) throws ParseException {

        String designation = json.getString("designation");

        JSONObject estimatedDiameterJson = json.getJSONObject("estimated_diameter")
                .getJSONObject("kilometers");

        Float estimatedDiameterMax = estimatedDiameterJson.getFloat("estimated_diameter_max");
        Float estimatedDiameterMin = estimatedDiameterJson.getFloat("estimated_diameter_min");
        Float estimatedDiameter = (estimatedDiameterMax + estimatedDiameterMin) / 2;
        Boolean potentiallyHazardousAsteroid = json.getBoolean("is_potentially_hazardous_asteroid");

        JSONArray closeApproachData = json.getJSONArray("close_approach_data");
        Map<Date, Double> closestApproachMap = buildClosestApproachDate(closeApproachData);

        Date now = new Date();

        Date closestDate = Collections.min(closestApproachMap.keySet(), (d1, d2) -> {
            long diff1 = Math.abs(d1.getTime() - now.getTime());
            long diff2 = Math.abs(d2.getTime() - now.getTime());
            return Long.compare(diff1, diff2);
        });

        return new AsteroidDetail(designation, estimatedDiameter, potentiallyHazardousAsteroid, closestApproachMap.get(closestDate));
    }

    private Map<Date, Double> buildClosestApproachDate(JSONArray closeApproachData) throws ParseException {

        Map<Date, Double> closestApproachByDate = new HashMap<>();

        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        for (int index = 0; index < closeApproachData.length(); index++) {
            String date = closeApproachData
                    .getJSONObject(index)
                    .getString("close_approach_date");

            Date approachDate = formatter.parse(date);

            Double closestApproach = closeApproachData
                    .getJSONObject(index)
                    .getJSONObject("miss_distance")
                    .getDouble("kilometers");

            closestApproachByDate.put(approachDate, closestApproach);

        }

        return closestApproachByDate;
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
