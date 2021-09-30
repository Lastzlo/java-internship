package com.example.demo.repositories;

import com.example.demo.models.Location;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class LocationsRepo {

    private final Map<String, Location> buffer = new HashMap<>();

    public void addLocations(Location[] locations) {
        for(Location location: locations) {
            buffer.put(location.getTitle(), location);
        }
    }

    public Optional<Location> findByName(String locationName) {
        Location location = buffer.get(locationName);
        if(location == null) return Optional.empty();
        return Optional.of(location);
    }

}
