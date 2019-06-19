package com.mulecode.jwtserver.config;

import com.mulecode.jwtserver.client.ClientDetailsCheckerService;
import com.mulecode.jwtserver.client.ClientDetailsService;
import com.mulecode.jwtserver.client.DefaultClientDetailsCheckerService;
import com.mulecode.jwtserver.client.DefaultClientDetailsService;
import com.mulecode.jwtserver.client.settings.ClientProperties;
import com.mulecode.jwtserver.enhancer.DefaultTokenEnhancer;
import com.mulecode.jwtserver.enhancer.TokenEnhancer;
import com.mulecode.jwtserver.event.JwtServerEventPublisher;
import com.mulecode.jwtserver.flow.FlowService;
import com.mulecode.jwtserver.flow.PasswordFlowService;
import com.mulecode.jwtserver.flow.RefreshFlowService;
import com.mulecode.jwtserver.parser.DefaultTokenParser;
import com.mulecode.jwtserver.parser.TokenParser;
import com.mulecode.jwtserver.password.BCryptPasswordEncoder;
import com.mulecode.jwtserver.password.PasswordEncoder;
import com.mulecode.jwtserver.resource.model.DefaultOauthAuthorizationRequest;
import com.mulecode.jwtserver.store.InMemoryStoreService;
import com.mulecode.jwtserver.store.StoreService;
import com.mulecode.jwtserver.token.DefaultTokenService;
import com.mulecode.jwtserver.token.TokenService;
import com.mulecode.jwtserver.user.DefaultUserDetailsCheckerService;
import com.mulecode.jwtserver.user.DefaultUserDetailsService;
import com.mulecode.jwtserver.user.UserDetailsCheckerService;
import com.mulecode.jwtserver.user.UserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.List;
import java.util.Map;

@Configuration
public class JwtServerConfiguration {

    static final Logger LOGGER = LoggerFactory.getLogger(JwtServerConfiguration.class);

    @Autowired
    private List<FlowService> flowServices;

    @Bean
    JwtServerEventPublisher jwtServerEventPublisher() {
        return new JwtServerEventPublisher();
    }

    @Bean
    @ConditionalOnMissingBean
    StoreService tokenStore() {
        return new InMemoryStoreService();
    }

    @Bean
    @ConditionalOnMissingBean
    UserDetailsService userDetailsService() {
        return new DefaultUserDetailsService();
    }

    @Bean
    @ConditionalOnMissingBean
    ClientDetailsService clientDetailsService() {
        return new DefaultClientDetailsService();
    }

    @Bean
    @ConditionalOnMissingBean(name = "tokenEnhancer")
    TokenEnhancer tokenEnhancer() {
        return new DefaultTokenEnhancer();
    }

    @Bean
    @ConditionalOnMissingBean(name = "jwtService")
    @DependsOn({"jwtServerEventPublisher"})
    TokenService jwtService() {
        return new DefaultTokenService();
    }

    @Bean
    @ConditionalOnMissingBean(name = "tokenParser")
    TokenParser tokenParser() {
        return new DefaultTokenParser();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.jwtserver")
    ClientProperties clientProperties() {
        return new ClientProperties();
    }

    @Bean("clientPasswordEncoder")
    @ConditionalOnMissingBean(name = "clientPasswordEncoder")
    PasswordEncoder clientPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean("userPasswordEncoder")
    @ConditionalOnMissingBean(name = "userPasswordEncoder")
    PasswordEncoder userPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean(name = "clientDetailsCheckerService")
    @DependsOn({"clientPasswordEncoder", "jwtServerEventPublisher"})
    ClientDetailsCheckerService clientDetailsCheckerService() {
        return new DefaultClientDetailsCheckerService();
    }

    @Bean
    @ConditionalOnMissingBean(name = "userDetailsCheckerService")
    @DependsOn({"userPasswordEncoder", "jwtServerEventPublisher"})
    UserDetailsCheckerService userDetailsCheckerService() {
        return new DefaultUserDetailsCheckerService();
    }

    @Bean("passwordFlowService")
    @ConditionalOnMissingBean(name = "passwordFlowService")
    @DependsOn({"clientDetailsCheckerService", "userDetailsCheckerService", "jwtServerEventPublisher"})
    FlowService passwordFlowService() {
        return new PasswordFlowService();
    }

    @Bean("refreshFlowService")
    @ConditionalOnMissingBean(name = "refreshFlowService")
    @DependsOn({"clientDetailsCheckerService", "jwtServerEventPublisher"})
    FlowService refreshFlowService() {
        return new RefreshFlowService();
    }

    public Map<String, Object> process(DefaultOauthAuthorizationRequest tokenRequest) throws Exception {

        var flowService = flowServices.stream()
                .filter(fs -> fs.name().equalsIgnoreCase(tokenRequest.getGrantType()))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("No flow implementation found for the given grant type: " + tokenRequest.getGrantType())
                );

        return flowService.process(tokenRequest);
    }
}
