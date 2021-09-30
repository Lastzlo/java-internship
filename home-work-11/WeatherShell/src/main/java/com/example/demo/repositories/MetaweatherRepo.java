package com.example.demo.repositories;

import com.example.demo.exception.MetaweatherRepoException;
import com.example.demo.models.Forecast;
import com.example.demo.models.Location;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

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
        try {
            return restTemplate.getForObject(Url, Location[].class);
        } catch (HttpClientErrorException e) {
            throw new MetaweatherRepoException();
        }
    }

    public Optional<Forecast> getForecast(int currLocationWoeid) {
        String Url = LOCATION_WEATHER_URL + currLocationWoeid + "/";
        try {
            Forecast forecast = restTemplate.getForObject(Url, Forecast.class);
            if (forecast.getConsolidatedWeather().length == 0) return Optional.empty();
            return Optional.of(forecast);
        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        } catch (HttpClientErrorException e) {
            throw new MetaweatherRepoException();
        }
    }
}
