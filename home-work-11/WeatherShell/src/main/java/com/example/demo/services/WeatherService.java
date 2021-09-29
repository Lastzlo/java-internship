package com.example.demo.services;

import com.example.demo.models.Forecast;
import com.example.demo.models.Location;
import com.example.demo.models.WeatherOnDay;
import com.example.demo.repositories.LocationsRepo;
import com.example.demo.repositories.MetaweatherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WeatherService {

    private final LocationsRepo locationsRepo;
    private final MetaweatherRepo metaweatherRepo;

    @Autowired
    public WeatherService(LocationsRepo locationsRepo, MetaweatherRepo metaweatherRepo) {
        this.locationsRepo = locationsRepo;
        this.metaweatherRepo = metaweatherRepo;
    }

    public Location[] findLocationsWithName(String locationName) {
        //search in locationsRepo
        Optional<Location> location = locationsRepo.findByName(locationName);
        if(location.isPresent()) return new Location[]{location.get()};

        //search in metaweatherRepo
        Location[] locations = metaweatherRepo.findByName(locationName);

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
        Integer currLocationWoeid = locationsRepo.getCurrLocation().getWoeid();


        Forecast forecast = metaweatherRepo.getForecast(currLocationWoeid);


        return forecast.getConsolidatedWeather();
    }

    public Forecast getForecastOnCurrLocation() {
        int currLocationWoeid = locationsRepo.getCurrLocation().getWoeid();
        return metaweatherRepo.getForecast(currLocationWoeid);
    }
}
