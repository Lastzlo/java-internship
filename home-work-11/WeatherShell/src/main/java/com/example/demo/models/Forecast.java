package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Forecast {
    @JsonProperty("consolidated_weather")
    private WeatherOnDay consolidatedWeather[];

    public WeatherOnDay[] getConsolidatedWeather() {
        return consolidatedWeather;
    }
}
