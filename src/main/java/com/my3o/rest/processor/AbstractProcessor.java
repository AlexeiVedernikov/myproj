package com.my3o.rest.processor;

import java.util.HashMap;

import com.my3o.rest.constant.ContentType;
import com.my3o.rest.response.RestResponse;
import com.my3o.rest.transport.HttpRequestWrapper;
import com.my3o.rest.transport.UrlWrapper;

public abstract class AbstractProcessor {
    protected ContentType contentType = ContentType.Json;

    protected void AbstractProcessor() {
        setContentType();
    }

    public HttpRequestWrapper getRequest(String method, Object postBody, UrlWrapper requestUrl,
            HashMap<String, String> headers) throws Exception {

        HttpRequestWrapper request = HttpRequestWrapper.getInstance(contentType, method, requestUrl, headers);
        if (postBody != null) {
            request.setBody(processRequestBody(postBody));

        }
        return request;
    }

    protected String preProcessBody(String requestBody, Class<?> objectClass) {
        throw new UnsupportedOperationException("preProcessBody is not implemented for " + this.getClass().getName());
    }

    public abstract <ServiceResponse extends RestResponse> ServiceResponse readResponse(String responseBody,
            Class<ServiceResponse> clazz) throws Exception;

    public abstract String processRequestBody(Object postBody) throws Exception;

    protected abstract void setContentType();

}
