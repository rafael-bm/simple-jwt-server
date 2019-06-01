package com.mulecode.jwtserver.resource;

import com.mulecode.jwtserver.resource.model.DefaultOauthAuthorizationRequest;
import com.mulecode.jwtserver.config.JwtServerConfiguration;
import com.mulecode.jwtserver.utils.BasicAuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@EnableWebMvc
@RestController
@Configuration
public class TokenResource {

    static final Logger LOGGER = LoggerFactory.getLogger(TokenResource.class);

    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String HEADER_REFRESH_TOKEN = "refresh_token";
    public static final String FIELD_USERNAME = "username";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIELD_GRANT_TYPE = "grant_type";

    @Autowired
    private JwtServerConfiguration oauthJwtServerConfiguration;

    @PostMapping("/oauth/token")
    public ResponseEntity oauthToken(@RequestHeader HttpHeaders headers,
                                     @RequestBody MultiValueMap<String, Object> requestBody) throws Exception {

        LOGGER.debug("Headers: {}", headers);
        LOGGER.debug("Body: {}", requestBody);

        var basicAuthorization = headers.getOrDefault(HEADER_AUTHORIZATION, List.of()).stream()
                .findFirst()
                .map(s -> {
                    var auth = new BasicAuthenticationService();
                    auth.decode(s);
                    return auth;
                }).orElse(new BasicAuthenticationService());

        var refreshToken = requestBody.getOrDefault(HEADER_REFRESH_TOKEN, List.of())
                .stream()
                .findFirst()
                .map(Object::toString)
                .orElse(null);

        var username = requestBody.getOrDefault(FIELD_USERNAME, List.of())
                .stream()
                .findFirst()
                .map(Object::toString)
                .orElse(null);

        var password = requestBody.getOrDefault(FIELD_PASSWORD, List.of())
                .stream()
                .findFirst()
                .map(Object::toString)
                .orElse(null);

        var grantType = requestBody.getOrDefault(FIELD_GRANT_TYPE, List.of())
                .stream()
                .findFirst()
                .map(Object::toString)
                .orElse(null);

        var tokenRequest = new DefaultOauthAuthorizationRequest();
        tokenRequest.setGrantType(grantType);
        tokenRequest.setUserPassword(password);
        tokenRequest.setUserName(username);
        tokenRequest.setOauthTokenRefresh(refreshToken);
        tokenRequest.setClientId(basicAuthorization.getUserName());
        tokenRequest.setClientSecret(basicAuthorization.getPassword());

        var response = oauthJwtServerConfiguration.process(tokenRequest);

        return ResponseEntity.ok(response);
    }

}