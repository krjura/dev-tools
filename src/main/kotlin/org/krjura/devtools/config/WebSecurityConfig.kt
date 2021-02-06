package org.krjura.devtools.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.logout.RedirectServerLogoutSuccessHandler
import java.net.URI

@Configuration
@EnableWebFluxSecurity
class WebSecurityConfig {

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http
            .authorizeExchange()
            .pathMatchers("/actuator/health").permitAll()
            .pathMatchers("/actuator/health/**").permitAll()
            .pathMatchers("/actuator").hasAuthority("ADMIN")
            .pathMatchers("/actuator/**").hasAuthority("ADMIN")
            .pathMatchers("/**").permitAll();

        http
            .headers()
            .contentSecurityPolicy("default-src 'self'; style-src 'self' 'unsafe-inline'");

        /* at the moment not working with web flux
        http
            .csrf()
            .csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse());
            */
        http.csrf().disable();

        http
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessHandler(logoutHandler());

        return http.build();
    }

    fun logoutHandler(): RedirectServerLogoutSuccessHandler {
        val handler = RedirectServerLogoutSuccessHandler();

        handler.setLogoutSuccessUrl(URI.create("/portal"))

        return handler;
    }
}