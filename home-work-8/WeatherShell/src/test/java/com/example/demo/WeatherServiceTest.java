package com.example.demo;

import com.example.demo.dto.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;


class WeatherServiceTest {

    @Test
    void findLocationWithName_butRepoEmpty() {

        WeatherService weatherService = new WeatherService(new LocationsRepo());

        Location[] locations = weatherService.findLocationsWithName("kiev");

        Assertions.assertTrue(locations.length == 1);
        assertThat(locations[0], instanceOf(Location.class));

    }

    @Test
    void testFindLocationWithName() {



    }
}