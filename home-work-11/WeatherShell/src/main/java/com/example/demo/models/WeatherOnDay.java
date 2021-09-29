package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class WeatherOnDay {
    @JsonProperty("applicable_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    @JsonProperty("weather_state_name")
    private String weatherState;

    @JsonProperty("min_temp")
    private int minTemperature;

    @JsonProperty("max_temp")
    private int maxTemperature;

    public Date getDate() {
        return date;
    }

    public String getWeatherState() {
        return weatherState;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    @Override
    public String toString() {
        return "WeatherOnDay{" +
                "applicable_date=" + date +
                ", weather_state_name='" + weatherState + '\'' +
                ", min_temp=" + minTemperature +
                ", max_temp=" + maxTemperature +
                '}';
    }
}
