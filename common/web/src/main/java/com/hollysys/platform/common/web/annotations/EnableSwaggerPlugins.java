package com.hollysys.platform.common.web.annotations;

import com.hollysys.platform.common.web.config.SwaggerConfig;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(SwaggerConfig.class)
@EnableSwagger2
public @interface EnableSwaggerPlugins {
}
