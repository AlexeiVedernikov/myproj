/**
 * 
 */
package com.my3o.frontend.config;

import com.my3o.frontend.web.util.CustomJacksonMapper;
import org.codehaus.jackson.JsonGenerator.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @author Alexander Dukkardt
 * 
 */
@Configuration
public class FrontendConfig {

    @Autowired
    protected IFrontendProperties baseFrontendProperties;

    @Bean
    public ObjectMapper mapper() {
        CustomJacksonMapper mapper = new CustomJacksonMapper();
        mapper.configure(Feature.WRITE_NUMBERS_AS_STRINGS, true);
        return mapper;
    }

//    @Bean
//    public CommonsMultipartResolver multipartResolver() {
//        CommonsMultipartResolver bean = new CommonsMultipartResolver();
//        bean.setMaxUploadSize(900000000000l);
//        return bean;
//    }

}
