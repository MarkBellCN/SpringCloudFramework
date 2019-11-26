package com.hollysys.platform.common.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(SwaggerProperties.PREFIX)
public class SwaggerProperties {
    public static final String PREFIX = "swagger";
    private boolean enable = false;

    private String basePackage = "";

    private String title = "";

    private String description = "";

    private String version = "";
}
