package com.mulecode.jwtserver.config;

import com.mulecode.jwtserver.resource.model.TokenResource;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({JwtServerConfiguration.class, TokenResource.class})
public @interface EnableSimpleJwtServer {


}
