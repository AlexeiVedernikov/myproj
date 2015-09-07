package com.my3o.frontend.config;

import com.my3o.common.annotation.Dev;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Alexander Dukkardt
 *
 */
@Configuration
@Dev
@PropertySource("classpath:frontend-dev.properties")
public class DevFrontendProperties extends AbstractFrontendProperties {

}
