package com.my3o.rest.client;

import com.my3o.rest.response.RestResponse;

public interface WeatherAPIClient extends RestAPIClient {
    public <WeatherResponse extends RestResponse> WeatherResponse translate() throws Exception;
}
