package com.my3o.rest.client.impl;

import com.my3o.rest.client.AbstractAPIClient;

public abstract class WeatherApiClient extends AbstractAPIClient {
    private String baseUrl = "http://api.openweathermap.org";

    protected String getBaseUrl() {
        return baseUrl;
    }

}
