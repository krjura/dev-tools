package org.krjura.devtools.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableWebFluxSecurity
class WebSecurityConfig() {

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http
            .authorizeExchange()
            .pathMatchers("/actuator/health").permitAll()
            .pathMatchers("/actuator").hasAuthority("ADMIN")
            .pathMatchers("/actuator/**").hasAuthority("ADMIN")
            .pathMatchers("/**").permitAll()

        http.csrf().disable();

        return http.build();
    }
}