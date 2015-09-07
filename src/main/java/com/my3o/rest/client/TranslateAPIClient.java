package com.my3o.rest.client;

import com.my3o.rest.response.RestResponse;

public interface TranslateAPIClient extends RestAPIClient {
    public <TranslateResponse extends RestResponse> TranslateResponse translate(String text, String sourceLang,
            String targetLang) throws Exception;
}
