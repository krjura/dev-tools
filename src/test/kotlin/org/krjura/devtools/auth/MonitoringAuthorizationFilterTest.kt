package org.krjura.devtools.auth

import org.krjura.devtools.auth.enums.AuthorizationConstants
import org.krjura.devtools.auth.enums.AuthorizationConstants.HEADER_AUTHORIZATION
import org.krjura.devtools.config.SecurityConfiguration
import org.mockito.Mockito
import org.mockito.Mockito.mock
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.krjura.devtools.support.TestBase
import org.mockito.Mockito.`when`
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import java.util.*
import javax.servlet.DispatcherType
import kotlin.collections.ArrayList

class MonitoringAuthorizationFilterTest: TestBase() {

    private val token = "be078463-f7d6-41cd-bd55-32e11075f277"

    @Test
    fun mustSetAuthentication() {
        // given
        val holder = SimpleAuthenticationSecurityHolder()
        val config = SecurityConfiguration();
        config.monitoring.tokens.add(token)

        val request = mock(HttpServletRequest::class.java)
        val response = mock(HttpServletResponse::class.java)
        val chain = mock(FilterChain::class.java)

        `when`(request.getHeader(HEADER_AUTHORIZATION)).thenReturn("Bearer $token")
        `when`(request.dispatcherType).thenReturn(DispatcherType.REQUEST)

        // when
        val filter = MonitoringAuthorizationFilter(config)
        filter.setAuthenticationSecurityHolder(holder)

        filter.doFilter(request, response, chain)

        // then
        assertThat(holder.getTokens()).hasSize(1)
        assertThat(holder.getTokens()[0]).isExactlyInstanceOf(UsernamePasswordAuthenticationToken::class.java)

        val token = holder.getTokens()[0];
        assertThat(token.principal).isEqualTo("monitoring")
        assertThat(token.authorities).hasSize(1)
        assertThat(ArrayList(token.authorities)[0].authority).isEqualTo("ROLE_MONITORING")
    }

    @Test
    fun mustNotSetAuthenticationWhenHeaderIsNotPresent() {
        // given
        val holder = SimpleAuthenticationSecurityHolder()
        val config = SecurityConfiguration();
        config.monitoring.tokens.add(token)

        val request = mock(HttpServletRequest::class.java)
        val response = mock(HttpServletResponse::class.java)
        val chain = mock(FilterChain::class.java)

        `when`(request.getHeader(HEADER_AUTHORIZATION)).thenReturn(null)
        `when`(request.dispatcherType).thenReturn(DispatcherType.REQUEST)

        // when
        val filter = MonitoringAuthorizationFilter(config)
        filter.setAuthenticationSecurityHolder(holder)

        filter.doFilter(request, response, chain)

        // then
        assertThat(holder.getTokens()).hasSize(0)
    }

    @Test
    fun mustNotSetAuthenticationWhenHeaderIsBasicAuth() {
        // given
        val holder = SimpleAuthenticationSecurityHolder()
        val config = SecurityConfiguration();
        config.monitoring.tokens.add(token)

        val request = mock(HttpServletRequest::class.java)
        val response = mock(HttpServletResponse::class.java)
        val chain = mock(FilterChain::class.java)

        `when`(request.getHeader(HEADER_AUTHORIZATION)).thenReturn("Basic aaaaaaa")
        `when`(request.dispatcherType).thenReturn(DispatcherType.REQUEST)

        // when
        val filter = MonitoringAuthorizationFilter(config)
        filter.setAuthenticationSecurityHolder(holder)

        filter.doFilter(request, response, chain)

        // then
        assertThat(holder.getTokens()).hasSize(0)
    }

    @Test
    fun mustNotSetAuthenticationWhenDifferentToken() {
        // given
        val holder = SimpleAuthenticationSecurityHolder()
        val config = SecurityConfiguration();
        config.monitoring.tokens.add(UUID.randomUUID().toString())

        val request = mock(HttpServletRequest::class.java)
        val response = mock(HttpServletResponse::class.java)
        val chain = mock(FilterChain::class.java)

        `when`(request.getHeader(HEADER_AUTHORIZATION)).thenReturn("Monitoring $token")
        `when`(request.dispatcherType).thenReturn(DispatcherType.REQUEST)

        // when
        val filter = MonitoringAuthorizationFilter(config)
        filter.setAuthenticationSecurityHolder(holder)

        filter.doFilter(request, response, chain)

        // then
        assertThat(holder.getTokens()).hasSize(0)
    }
}