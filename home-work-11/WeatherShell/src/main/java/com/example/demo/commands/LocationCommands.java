package com.example.demo.commands;

import com.example.demo.models.Location;
import com.example.demo.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Optional;

@ShellComponent
public class LocationCommands {

    private final LocationService locationService;

    @Autowired
    public LocationCommands(LocationService locationService) {
        this.locationService = locationService;
    }

    @ShellMethod(value = "Find a location", key = {"location", "loc"})
    public String searchLocation(
            String name
    ) {
        if(locationService.isInvalidName(name)) return "Invalid location name";
        Optional<Location[]> locationsByName = locationService.findLocationsByName(name);
        if(locationsByName.isPresent()) return formatSearchLocationRequest(name, locationsByName.get());
        return "Not found location with this name";
    }

    String formatSearchLocationRequest(String name, Location[] locations) {
        if(locations.length == 0) return "Not found location with name: " + name;
        if(locations.length == 1) {
            return locations[0].toString();
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Found many locations with same name:\n");
        for(Location location: locations) {
            builder.append(System.lineSeparator()).append(location.toString());
        }
        return builder.toString();
    }

}
