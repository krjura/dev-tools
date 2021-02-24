package org.krjura.devtools.config

import org.krjura.devtools.auth.MonitoringAuthorizationFilter
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.logout.ForwardLogoutSuccessHandler
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
@EnableWebSecurity
class WebSecurityConfig(private val config: SecurityConfiguration): WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
            .antMatchers("/actuator/health").permitAll()
            .antMatchers("/actuator/health/**").permitAll()
            .antMatchers("/actuator/metrics").hasAuthority("ROLE_MONITORING")
            .antMatchers("/actuator/metrics/**").hasAuthority("ROLE_MONITORING")
            .antMatchers("/actuator/prometheus").hasAuthority("ROLE_MONITORING")
            .antMatchers("/actuator/**").hasIpAddress("127.0.0.1")
            .antMatchers("/**").permitAll()
            .and()
            .addFilterBefore(MonitoringAuthorizationFilter(this.config), BasicAuthenticationFilter::class.java)

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