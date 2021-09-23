package com.example.demo;

import com.example.demo.dto.FiveDayForecast;
import com.example.demo.dto.Location;
import com.example.demo.dto.WeatherOnDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class WeatherService {

    private static final String LOCATION_SEARCH_URL = "https://www.metaweather.com/api/location/search/?query=";
    private static final String LOCATION_WEATHER_URL = "https://www.metaweather.com/api/location/";


    private final LocationsRepo locationsRepo;
    private final RestTemplate restTemplate;

    @Autowired
    public WeatherService(LocationsRepo locationsRepo) {
        this.locationsRepo = locationsRepo;
        this.restTemplate = new RestTemplate();
    }

    public Location[] findLocationsWithName(String locationName) {
        //search at local memory
        Optional<Location> location = locationsRepo.findByName(locationName);
        if(location.isPresent()) return new Location[]{location.get()};

        //search at metaweather.com
        String Url = LOCATION_SEARCH_URL + locationName;
        Location[] locations = restTemplate.getForObject(Url, Location[].class);
        locationsRepo.addLocations(locations);
        return locations;
    }

    public boolean isPresentCurrLocation() {
        return locationsRepo.hasCurrLocation();
    }

    public void setCurrLocation(Location location) {
        locationsRepo.setCurrLocation(location);
    }

    public Location getCurrLocation() {
        return locationsRepo.getCurrLocation();
    }

    public WeatherOnDay[] getWeatherOnCurrLocation() {
        String Url = LOCATION_WEATHER_URL + locationsRepo.getCurrLocation().getWoeid() + "/";
        FiveDayForecast forecast = restTemplate.getForObject(Url, FiveDayForecast.class);
        return forecast.getConsolidated_weather();
    }
}
