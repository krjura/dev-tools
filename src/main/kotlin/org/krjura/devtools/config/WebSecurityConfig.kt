package org.krjura.devtools.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.logout.ForwardLogoutSuccessHandler

@Configuration
@EnableWebSecurity
class WebSecurityConfig: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
            .antMatchers("/actuator/health").permitAll()
            .antMatchers("/actuator/health/**").permitAll()
            .antMatchers("/actuator/metrics/**").access("hasIpAddress('10.0.0.0/8') or hasIpAddress('127.0.0.1')")
            .antMatchers("/actuator/prometheus").access("hasIpAddress('10.0.0.0/8') or hasIpAddress('127.0.0.1')")
            .antMatchers("/actuator/**").hasIpAddress("127.0.0.1")
            .antMatchers("/**").permitAll();

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
    }

    fun logoutHandler(): ForwardLogoutSuccessHandler {
        return ForwardLogoutSuccessHandler("/portal");
    }
}