package com.mulecode.jwtserver.config;

import com.mulecode.jwtserver.resource.AdviceResource;
import com.mulecode.jwtserver.resource.TokenResource;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({JwtServerConfiguration.class, TokenResource.class, AdviceResource.class})
public @interface EnableSimpleJwtServer {


}
