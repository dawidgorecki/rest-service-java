package pl.dawidgorecki.restservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pl.dawidgorecki.restservice.filter.PreAuthTokenHeaderFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${http.auth.token.name}")
    private String authHeaderName;

    @Value("${http.auth.token.value}")
    private String authHeaderValue;

    @Bean
    public PasswordEncoder getBcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        PreAuthTokenHeaderFilter filter = new PreAuthTokenHeaderFilter(authHeaderName);

        filter.setAuthenticationManager(authentication -> {
            String principal = (String) authentication.getPrincipal();

            if (!authHeaderValue.equals(principal)) {
                throw new BadCredentialsException("Invalid API key.");
            }

            authentication.setAuthenticated(true);
            return authentication;
        });

        http
                .antMatcher("/api/**")
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(filter)
                .authorizeRequests().anyRequest().authenticated();

        return http.build();
    }
}