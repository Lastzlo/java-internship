package com.example.demo;

import com.example.demo.dto.FiveDayForecast;
import com.example.demo.dto.Location;
import com.example.demo.dto.WeatherOnDay;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

public class TestLearnCases {

    @Test
    void testEmptyMap() {

        Map<String, Integer> map = new HashMap<>();

        Integer value = map.get("abc");

        Assertions.assertNull(value);

    }

    @Test
    void testUseRestTemplate_ResponseEntity() {

        RestTemplate restTemplate = new RestTemplate();

        String fooResourceUrl
                = "https://www.metaweather.com/api/location/search/?query=london";
        final ResponseEntity<String> response =
                restTemplate.getForEntity(fooResourceUrl, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    void testUseRestTemplate_thenLocationArray() {
        RestTemplate restTemplate = new RestTemplate();

        String fooResourceUrl
                = "https://www.metaweather.com/api/location/search/?query=london";

        Location[] asArray = restTemplate
                .getForObject(fooResourceUrl, Location[].class);

        assertThat(asArray[0], instanceOf(Location.class));
    }

    @Test
    void testUseHttpClient_thenHttpRequest_thenBody() throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://www.metaweather.com/api/location/search/?query=london"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        assertThat(response.statusCode(), equalTo(HttpStatus.OK));


        // print status code
        System.out.println(response.statusCode());
        // print response body
        System.out.println(response.body());

        String body = response.body();

        ObjectMapper mapper =
                new ObjectMapper()
                        //ignore properties that haven't class
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                        //specify jackson to use fields (without getters-setters)
                        .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
                        .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        Location[] asArray = mapper
                .readValue(body, Location[].class);

        System.out.println("location = " + asArray[0]);

    }

    @Test
    void testJacksonParse_thenLocation() throws IOException {
        String body = "[{\"title\":\"London\",\"location_type\":\"City\",\"woeid\":44418,\"latt_long\":\"51.506321,-0.12714\"}]";

        ObjectMapper mapper =
                new ObjectMapper()
                        //ignore properties that haven't class
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                        //specify jackson to use fields (without getters-setters)
                        .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
                        .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        Location[] asArray = mapper
                .readValue(body, Location[].class);
        System.out.println("location = " + asArray[0]);
    }

    @Test
    void testJacksonParse_thenFiveDayForecast_thenWeatherOnDay() throws IOException {
        String body = "{\"consolidated_weather\":[{\"id\":5511525927223296,\"weather_state_name\":\"Heavy Cloud\",\"weather_state_abbr\":\"hc\",\"wind_direction_compass\":\"W\",\"created\":\"2021-09-23T18:59:02.064626Z\",\"applicable_date\":\"2021-09-23\",\"min_temp\":11.905,\"max_temp\":21.325000000000003,\"the_temp\":20.895,\"wind_speed\":6.729550472952624,\"wind_direction\":266.4464642400697,\"air_pressure\":1019.5,\"humidity\":72,\"visibility\":9.494241131790345,\"predictability\":71},{\"id\":5218488864997376,\"weather_state_name\":\"Clear\",\"weather_state_abbr\":\"c\",\"wind_direction_compass\":\"WSW\",\"created\":\"2021-09-23T18:59:02.255727Z\",\"applicable_date\":\"2021-09-24\",\"min_temp\":11.905000000000001,\"max_temp\":22.855,\"the_temp\":21.23,\"wind_speed\":5.77404936645192,\"wind_direction\":250.1660606579943,\"air_pressure\":1016.0,\"humidity\":62,\"visibility\":10.61177722102919,\"predictability\":68},{\"id\":5256379603353600,\"weather_state_name\":\"Heavy Cloud\",\"weather_state_abbr\":\"hc\",\"wind_direction_compass\":\"SSW\",\"created\":\"2021-09-23T18:59:01.857078Z\",\"applicable_date\":\"2021-09-25\",\"min_temp\":15.565,\"max_temp\":21.455,\"the_temp\":20.265,\"wind_speed\":3.528983604313476,\"wind_direction\":209.4065238029261,\"air_pressure\":1015.0,\"humidity\":73,\"visibility\":10.800984749065456,\"predictability\":71},{\"id\":5729275501084672,\"weather_state_name\":\"Heavy Cloud\",\"weather_state_abbr\":\"hc\",\"wind_direction_compass\":\"SSW\",\"created\":\"2021-09-23T18:59:01.462948Z\",\"applicable_date\":\"2021-09-26\",\"min_temp\":14.25,\"max_temp\":21.58,\"the_temp\":21.465,\"wind_speed\":7.292783284474667,\"wind_direction\":206.15531440626793,\"air_pressure\":1011.0,\"humidity\":66,\"visibility\":11.87036457090591,\"predictability\":71},{\"id\":5698505709453312,\"weather_state_name\":\"Light Rain\",\"weather_state_abbr\":\"lr\",\"wind_direction_compass\":\"WSW\",\"created\":\"2021-09-23T18:59:02.054441Z\",\"applicable_date\":\"2021-09-27\",\"min_temp\":12.364999999999998,\"max_temp\":18.215000000000003,\"the_temp\":18.285,\"wind_speed\":8.399363623322463,\"wind_direction\":243.18357638489243,\"air_pressure\":1016.5,\"humidity\":56,\"visibility\":14.503735683607731,\"predictability\":75},{\"id\":5396960996491264,\"weather_state_name\":\"Heavy Cloud\",\"weather_state_abbr\":\"hc\",\"wind_direction_compass\":\"SSW\",\"created\":\"2021-09-23T18:59:04.355172Z\",\"applicable_date\":\"2021-09-28\",\"min_temp\":11.565,\"max_temp\":17.875,\"the_temp\":17.94,\"wind_speed\":5.493817108088761,\"wind_direction\":192.99999999999997,\"air_pressure\":1022.0,\"humidity\":57,\"visibility\":9.999726596675416,\"predictability\":71}],\"time\":\"2021-09-23T22:10:02.488162+01:00\",\"sun_rise\":\"2021-09-23T06:48:23.154445+01:00\",\"sun_set\":\"2021-09-23T18:56:12.143815+01:00\",\"timezone_name\":\"LMT\",\"parent\":{\"title\":\"England\",\"location_type\":\"Region / State / Province\",\"woeid\":24554868,\"latt_long\":\"52.883560,-1.974060\"},\"sources\":[{\"title\":\"BBC\",\"slug\":\"bbc\",\"url\":\"http://www.bbc.co.uk/weather/\",\"crawl_rate\":360},{\"title\":\"Forecast.io\",\"slug\":\"forecast-io\",\"url\":\"http://forecast.io/\",\"crawl_rate\":480},{\"title\":\"HAMweather\",\"slug\":\"hamweather\",\"url\":\"http://www.hamweather.com/\",\"crawl_rate\":360},{\"title\":\"Met Office\",\"slug\":\"met-office\",\"url\":\"http://www.metoffice.gov.uk/\",\"crawl_rate\":180},{\"title\":\"OpenWeatherMap\",\"slug\":\"openweathermap\",\"url\":\"http://openweathermap.org/\",\"crawl_rate\":360},{\"title\":\"Weather Underground\",\"slug\":\"wunderground\",\"url\":\"https://www.wunderground.com/?apiref=fc30dc3cd224e19b\",\"crawl_rate\":720},{\"title\":\"World Weather Online\",\"slug\":\"world-weather-online\",\"url\":\"http://www.worldweatheronline.com/\",\"crawl_rate\":360}],\"title\":\"London\",\"location_type\":\"City\",\"woeid\":44418,\"latt_long\":\"51.506321,-0.12714\",\"timezone\":\"Europe/London\"}";

        ObjectMapper mapper =
                new ObjectMapper()
                        //ignore properties that haven't class
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                        //specify jackson to use fields (without getters-setters)
                        .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
                        .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        FiveDayForecast forecast = mapper
                .readValue(body, FiveDayForecast.class);
        WeatherOnDay[] weatherOnDays = forecast.getConsolidated_weather();
        for(WeatherOnDay weather: weatherOnDays)
            System.out.println("weather = " + weather);

    }
}
