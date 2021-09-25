package com.example.demo.repositories;

import com.example.demo.models.Location;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class LocationsRepo {

    private final Map<String, Location> buffer = new HashMap<>();
    private Optional<Location> currLocation = Optional.empty();

    public void addLocations(Location[] locations) {
        for(Location location: locations) {
            buffer.put(location.getTitle(), location);
        }
    }

    public void setCurrLocation(Location location) {
        this.currLocation = Optional.of(location);
    }

    public boolean hasCurrLocation() {
        return currLocation.isPresent();
    }

    public Optional<Location> findByName(String locationName) {
        Location location = buffer.get(locationName);
        if(location == null) return Optional.empty();
        return Optional.of(location);
    }

    public Location getCurrLocation() {
        return currLocation.orElseThrow();
    }
}
