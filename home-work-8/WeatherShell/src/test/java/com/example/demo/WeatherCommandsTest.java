package com.example.demo;

import com.example.demo.dto.FiveDayForecast;
import com.example.demo.dto.Location;
import com.example.demo.dto.WeatherOnDay;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeatherCommandsTest {

    static WeatherOnDay[] weatherOnDays;
    static Location location;

    @BeforeAll
    static void init() throws JsonProcessingException {
        ObjectMapper mapper =
                new ObjectMapper()
                        //ignore properties that haven't class
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                        //specify jackson to use fields (without getters-setters)
                        .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
                        .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        String body = "{\n" +
                "  \"consolidated_weather\": [\n" +
                "    {\n" +
                "      \"id\": 5511525927223296,\n" +
                "      \"weather_state_name\": \"Heavy Cloud\",\n" +
                "      \"weather_state_abbr\": \"hc\",\n" +
                "      \"wind_direction_compass\": \"W\",\n" +
                "      \"created\": \"2021-09-23T18:59:02.064626Z\",\n" +
                "      \"applicable_date\": \"2021-09-23\",\n" +
                "      \"min_temp\": 11.905,\n" +
                "      \"max_temp\": 21.325000000000003,\n" +
                "      \"the_temp\": 20.895,\n" +
                "      \"wind_speed\": 6.729550472952624,\n" +
                "      \"wind_direction\": 266.4464642400697,\n" +
                "      \"air_pressure\": 1019.5,\n" +
                "      \"humidity\": 72,\n" +
                "      \"visibility\": 9.494241131790345,\n" +
                "      \"predictability\": 71\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 5396960996491264,\n" +
                "      \"weather_state_name\": \"Heavy Cloud\",\n" +
                "      \"weather_state_abbr\": \"hc\",\n" +
                "      \"wind_direction_compass\": \"SSW\",\n" +
                "      \"created\": \"2021-09-23T18:59:04.355172Z\",\n" +
                "      \"applicable_date\": \"2021-09-28\",\n" +
                "      \"min_temp\": 11.565,\n" +
                "      \"max_temp\": 17.875,\n" +
                "      \"the_temp\": 17.94,\n" +
                "      \"wind_speed\": 5.493817108088761,\n" +
                "      \"wind_direction\": 192.99999999999997,\n" +
                "      \"air_pressure\": 1022,\n" +
                "      \"humidity\": 57,\n" +
                "      \"visibility\": 9.999726596675416,\n" +
                "      \"predictability\": 71\n" +
                "    }\n" +
                "  ],\n" +
                "  \"time\": \"2021-09-23T20:45:49.981801+01:00\",\n" +
                "  \"sun_rise\": \"2021-09-23T06:48:23.154445+01:00\",\n" +
                "  \"sun_set\": \"2021-09-23T18:56:12.143815+01:00\",\n" +
                "  \"timezone_name\": \"LMT\",\n" +
                "  \"parent\": {\n" +
                "    \"title\": \"England\",\n" +
                "    \"location_type\": \"Region / State / Province\",\n" +
                "    \"woeid\": 24554868,\n" +
                "    \"latt_long\": \"52.883560,-1.974060\"\n" +
                "  },\n" +
                "  \"sources\": [\n" +
                "    {\n" +
                "      \"title\": \"BBC\",\n" +
                "      \"slug\": \"bbc\",\n" +
                "      \"url\": \"http://www.bbc.co.uk/weather/\",\n" +
                "      \"crawl_rate\": 360\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"Forecast.io\",\n" +
                "      \"slug\": \"forecast-io\",\n" +
                "      \"url\": \"http://forecast.io/\",\n" +
                "      \"crawl_rate\": 480\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"HAMweather\",\n" +
                "      \"slug\": \"hamweather\",\n" +
                "      \"url\": \"http://www.hamweather.com/\",\n" +
                "      \"crawl_rate\": 360\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"Met Office\",\n" +
                "      \"slug\": \"met-office\",\n" +
                "      \"url\": \"http://www.metoffice.gov.uk/\",\n" +
                "      \"crawl_rate\": 180\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"OpenWeatherMap\",\n" +
                "      \"slug\": \"openweathermap\",\n" +
                "      \"url\": \"http://openweathermap.org/\",\n" +
                "      \"crawl_rate\": 360\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"Weather Underground\",\n" +
                "      \"slug\": \"wunderground\",\n" +
                "      \"url\": \"https://www.wunderground.com/?apiref=fc30dc3cd224e19b\",\n" +
                "      \"crawl_rate\": 720\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"World Weather Online\",\n" +
                "      \"slug\": \"world-weather-online\",\n" +
                "      \"url\": \"http://www.worldweatheronline.com/\",\n" +
                "      \"crawl_rate\": 360\n" +
                "    }\n" +
                "  ],\n" +
                "  \"title\": \"London\",\n" +
                "  \"location_type\": \"City\",\n" +
                "  \"woeid\": 44418,\n" +
                "  \"latt_long\": \"51.506321,-0.12714\",\n" +
                "  \"timezone\": \"Europe/London\"\n" +
                "}";

        FiveDayForecast forecast = mapper
                .readValue(body, FiveDayForecast.class);
        weatherOnDays = forecast.getConsolidated_weather();

        body = "[{\"title\":\"London\",\"location_type\":\"City\",\"woeid\":44418,\"latt_long\":\"51.506321,-0.12714\"}]";

        location = mapper
                .readValue(body, Location[].class)[0];
    }



    @Test
    void formatGetWeatherRequest() {
        WeatherCommands commands = new WeatherCommands(null);
        String actual = commands.formatGetWeatherRequest(location, weatherOnDays);
        System.out.println(actual);

        Assertions.assertEquals(182, actual.length());
    }
}