package com.example.demo.repositories;

import com.example.demo.models.Forecast;
import com.example.demo.models.Location;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class MetaweatherRepo {

    private static final String LOCATION_SEARCH_URL = "https://www.metaweather.com/api/location/search/?query=";
    private static final String LOCATION_WEATHER_URL = "https://www.metaweather.com/api/location/";

    private final RestTemplate restTemplate;

    public MetaweatherRepo() {
        this.restTemplate = new RestTemplate();
    }

    public Location[] findByName(String locationName) {
        String Url = LOCATION_SEARCH_URL + locationName;
        return restTemplate.getForObject(Url, Location[].class);
    }

    public Forecast getForecast(int currLocationWoeid) {
        String Url = LOCATION_WEATHER_URL + currLocationWoeid + "/";
        return restTemplate.getForObject(Url, Forecast.class);
    }
}
