package com.example.demo.controllers;

import com.example.demo.exception.LocationNotFoundException;
import com.example.demo.models.Forecast;
import com.example.demo.services.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Operation(summary = "Returns forecast by location name")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Forecast.class)) }),
            @ApiResponse(
                    responseCode = "404",
                    description = "LocationNotFoundException", content = @Content) }
    )
    @GetMapping("/forecast/{locationName}")
    public Forecast getForecast(@PathVariable String locationName) {
        return weatherService
                .getForecastOnLocation(locationName)
                .orElseThrow(LocationNotFoundException::new);
    }

}
