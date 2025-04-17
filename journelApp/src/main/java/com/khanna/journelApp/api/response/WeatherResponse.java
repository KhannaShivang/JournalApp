package com.khanna.journelApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse{
    public Current current;

    @Getter
    @Setter
    public class Current{
        @JsonProperty("observation_time")
        private String observationTime;
        private int temperature;
        @JsonProperty("weather_code")
        private int weatherCode;
        private int feelslike;
        @JsonProperty("uv_index")
        private int uv_index;
        private int visibility;
        @JsonProperty("is_day")
        private String isDay;
    }
}




