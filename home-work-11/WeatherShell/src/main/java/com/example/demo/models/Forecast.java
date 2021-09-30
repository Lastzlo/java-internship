package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class Forecast {
    @JsonProperty("consolidated_weather")
    private WeatherOnDay consolidatedWeather[];

    public WeatherOnDay[] getConsolidatedWeather() {
        return consolidatedWeather;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "consolidatedWeather=" + Arrays.toString(consolidatedWeather) +
                '}';
    }
}
