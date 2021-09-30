package com.example.demo.commands;

import com.example.demo.models.Forecast;
import com.example.demo.services.ForecastService;
import com.example.demo.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
public class ForecastCommands {

    private final ForecastService forecastService;
    private final LocationService locationService;

    @Autowired
    public ForecastCommands(ForecastService forecastService, LocationService locationService) {
        this.forecastService = forecastService;
        this.locationService = locationService;
    }

    @ShellMethod(value = "Return forecast for current location", key = {"weather", "get"})
    @ShellMethodAvailability("getWeatherAvailabilityCheck")
    public String getWeather(int locationId) {
        if(locationService.isInvalidId(locationId)) return "Invalid location id";
        Forecast forecast = forecastService.getForecastByLocationId(locationId).get();
        return forecast.toString();
    }

}
