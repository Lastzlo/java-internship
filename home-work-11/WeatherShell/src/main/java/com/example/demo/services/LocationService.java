package com.example.demo.services;

import com.example.demo.models.Location;
import com.example.demo.repositories.LocationsRepo;
import com.example.demo.repositories.MetaweatherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationService {

    private final LocationsRepo locationsRepo;
    private final MetaweatherRepo metaweatherRepo;

    @Autowired
    public LocationService(LocationsRepo locationsRepo, MetaweatherRepo metaweatherRepo) {
        this.locationsRepo = locationsRepo;
        this.metaweatherRepo = metaweatherRepo;
    }

    public boolean isInvalidId(int locationId) {
        return locationId < 0;
    }

    public boolean isInvalidName(String locationName) {
        return false;
    }

    public Optional<Location[]> findLocationsByName(String locationName) {
        //search in locationsRepo
        Optional<Location> location = locationsRepo.findByName(locationName);
        if(location.isPresent()) return Optional.of(new Location[]{location.get()});

        //search in metaweatherRepo
        Location[] locations = metaweatherRepo.findByName(locationName);

        if(locations.length == 0) return Optional.empty();

        locationsRepo.addLocations(locations);
        return Optional.of(locations);
    }

}
