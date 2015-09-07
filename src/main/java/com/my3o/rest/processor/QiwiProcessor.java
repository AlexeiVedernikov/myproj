package com.my3o.rest.processor;


public class QiwiProcessor extends JsonProcessor {
    @Override
    public String processRequestBody(Object postBody) throws Exception {
        if (postBody instanceof String) {
            return (String) postBody;
        }
        return super.processRequestBody(postBody);
    }
}
