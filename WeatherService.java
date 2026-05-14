package org.fresnocity.weatherdashboard;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.Map;

@Service
public class WeatherService {
    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> getForecast(String lat, String lon) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "(my-weather-app, contact@example.com)");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Step 1: Get metadata
        String pointsUrl = "https://api.weather.gov/points/" + lat + "," + lon;
        Map<String, Object> points = restTemplate.exchange(pointsUrl, HttpMethod.GET, entity, Map.class).getBody();
        String forecastUrl = (String) ((Map) points.get("properties")).get("forecast");

        // Step 2: Get forecast and return as Map
        return restTemplate.exchange(forecastUrl, HttpMethod.GET, entity, Map.class).getBody();
    }
}