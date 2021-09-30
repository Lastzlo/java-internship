package com.example.demo.controllers;

import com.example.demo.exception.LocationInvalidException;
import com.example.demo.models.Forecast;
import com.example.demo.models.Location;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("locations")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @Operation(summary = "Returns locations by location name")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the locations",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Forecast.class)) }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid location name",
                    content = @Content),
            @ApiResponse(
                    responseCode = "404",
                    description = "No locations for location name",
                    content = @Content)})
    @GetMapping("{locationName}")
    public Location[] getLocations(
            @Parameter(description = "location name")
            @PathVariable String locationName) {
        if (locationService.isInvalidName(locationName)) throw new LocationInvalidException();
        return locationService.findLocationsByName(locationName).orElseThrow(LocationInvalidException::new);
    }

}
