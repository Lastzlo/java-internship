package com.example.demo.controllers;

import com.example.demo.models.Forecast;
import com.example.demo.services.WeatherService;
import com.example.demo.models.Location;
import com.example.demo.models.WeatherOnDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

@ShellComponent
public class WeatherCommands {

    private final WeatherService weatherService;

    @Autowired
    public WeatherCommands(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @ShellMethod(value = "Find a location", key = {"location", "loc"})
    public String searchLocation(
            String name
    ) {
        Location[] locations = weatherService.findLocationsWithName(name);
        return formatSearchLocationRequest(name, locations);
    }

    String formatSearchLocationRequest(String name, Location[] locations) {
        if(locations.length == 0) return "Not found location with name: " + name;
        if(locations.length == 1) {
            weatherService.setCurrLocation(locations[0]);
            return "Location: " + name + " has chosen";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Found many locations with same name,\n");
        builder.append("please choose and rewrite command: ");
        for(Location location: locations) {
            builder.append(System.lineSeparator()).append(location.getTitle());
        }
        return builder.toString();
    }

    @ShellMethod(value = "Return forecast for current location", key = {"weather", "get"})
    @ShellMethodAvailability("getWeatherAvailabilityCheck")
    public String getWeather() {
        Location location = weatherService.getCurrLocation();
        Forecast forecast = weatherService.getForecastOnCurrLocation();
        return formatGetWeatherRequest(location, forecast);
    }

    String formatGetWeatherRequest(Location location, Forecast forecast) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("'On' EEEE yyyy-MM-dd");
        DecimalFormat df = new DecimalFormat("##");
        StringBuilder builder =
                new StringBuilder("Weather for ")
                        .append(location.getTitle())
                        .append(":");
        for (WeatherOnDay weather : forecast.getConsolidatedWeather()) {
            builder
                    .append(System.lineSeparator())
                        .append(dateFormat.format(weather.getDate()))
                    .append(System.lineSeparator())
                        .append("weather state: ")
                        .append(weather.getWeatherState())
                    .append(System.lineSeparator())
                        .append("max temp: ")
                        .append(df.format(weather.getMaxTemperature()))
                    .append(System.lineSeparator())
                        .append("min temp: ")
                        .append(df.format(weather.getMinTemperature()))
                    .append(System.lineSeparator());
        }
        return builder.toString();
    }

    public Availability getWeatherAvailabilityCheck() {
        return weatherService.isPresentCurrLocation()
                ? Availability.available()
                : Availability.unavailable("Please choose location");
    }


}
