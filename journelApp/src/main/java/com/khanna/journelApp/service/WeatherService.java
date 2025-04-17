package com.khanna.journelApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.khanna.journelApp.api.response.WeatherResponse;
import com.khanna.journelApp.cashe.AppCashe;

@Service
public class WeatherService {
    @Value("${api.key.weather}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedisService redisService;

    @Autowired
    private AppCashe appCashe;
    public WeatherResponse getWeather(String city){
        String key="Weather_in_"+city;
        WeatherResponse body = redisService.get(key,WeatherResponse.class);
        if(body!=null)return body;
        String finalApi=appCashe.cache.get("weather_api").replace("<api_key>",apiKey).replace("<city>",city);
        ResponseEntity<WeatherResponse>response = restTemplate.exchange(finalApi,HttpMethod.GET,null,WeatherResponse.class);
        body = response.getBody();
        if(body!=null)redisService.set(key,body,5);
        return body;
    }
}
