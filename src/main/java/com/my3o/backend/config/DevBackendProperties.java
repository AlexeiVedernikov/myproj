package com.my3o.backend.config;

import com.my3o.common.annotation.Dev;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * @author Alexander Dukkardt
 *
 */
@Configuration
@Dev
@PropertySources(value={@PropertySource("classpath:backend-dev.properties")})
public class DevBackendProperties extends AbstractBackendProperties {

}
