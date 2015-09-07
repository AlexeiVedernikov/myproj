package com.my3o.rest.processor;

import com.my3o.rest.constant.ContentProcessorType;

public class ProcessorFactory {

    public static AbstractProcessor getInstance(ContentProcessorType contentProcessorType) throws Exception {
        switch (contentProcessorType) {
        case Json:
            return new JsonProcessor();
        case Xml:
            return new XmlProcessor();
        case Qiwi:
            return new QiwiProcessor();
        default:
            throw new UnsupportedOperationException("Unknown content type. Cannot find processor");
        }
    }
}
