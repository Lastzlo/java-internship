package com.example.demo.services;

import com.example.demo.models.Forecast;
import com.example.demo.repositories.MetaweatherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ForecastService {

    private final MetaweatherRepo metaweatherRepo;

    @Autowired
    public ForecastService(MetaweatherRepo metaweatherRepo) {
        this.metaweatherRepo = metaweatherRepo;
    }

    public Optional<Forecast> getForecastByLocationId(int locationId) {
        return metaweatherRepo.getForecast(locationId);
    }
}
