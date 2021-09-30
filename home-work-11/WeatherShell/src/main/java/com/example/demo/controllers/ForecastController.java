package com.example.demo.controllers;

import com.example.demo.exception.ForecastNotFoundException;
import com.example.demo.exception.LocationInvalidException;
import com.example.demo.models.Forecast;
import com.example.demo.services.ForecastService;
import com.example.demo.services.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForecastController {

    private final ForecastService forecastService;
    private final LocationService locationService;

    @Autowired
    public ForecastController(ForecastService forecastService, LocationService locationService) {
        this.forecastService = forecastService;
        this.locationService = locationService;
    }

    @Operation(summary = "Returns forecast by location id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the forecast",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Forecast.class)) }),
            @ApiResponse(
                    responseCode = "404",
                    description = "No forecast for location",
                    content = @Content),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid location id",
                    content = @Content)})
    @GetMapping("/forecast/{locationId}")
    public Forecast getForecast(
            @Parameter(description = "location id")
            @PathVariable int locationId) {
        if (locationService.isInvalidId(locationId)) throw new LocationInvalidException();
        return forecastService
                .getForecastByLocationId(locationId)
                .orElseThrow(ForecastNotFoundException::new);
    }

}
