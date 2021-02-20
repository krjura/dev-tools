package org.krjura.devtools.auth

import org.krjura.devtools.auth.enums.AuthorizationConstants
import org.krjura.devtools.config.SecurityConfiguration
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MonitoringAuthorizationFilter(private val config: SecurityConfiguration) : OncePerRequestFilter() {

    private val monitoringAuthority = listOf(SimpleGrantedAuthority("ROLE_MONITORING"));
    private val monitoringPrincipal = "monitoring"

    private var securityHolder: AuthenticationSecurityHolder = SpringAuthenticationSecurityHolder();

    fun setAuthenticationSecurityHolder(holder: AuthenticationSecurityHolder) {
        this.securityHolder = holder;
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain) {

        val header: String? = findAuthorizationHeader(request)

        if (header != null) {
            handleHeaderBasedAuthorization(header, request, response, filterChain);
        }

        filterChain.doFilter(request, response);
    }

    private fun findAuthorizationHeader(request: HttpServletRequest): String? {
        val header = request.getHeader(AuthorizationConstants.HEADER_AUTHORIZATION)

        return if (header == null || !header.startsWith(AuthorizationConstants.HEADER_PARAM_BEARER)) {
            null
        } else {
            header
        }
    }

    @Throws(IOException::class, ServletException::class)
    private fun handleHeaderBasedAuthorization(
        header: String, request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {

        val authToken = header.substring(AuthorizationConstants.HEADER_PARAM_BEARER.length)

        if(config.monitoring.tokens.contains(authToken)) {
            val token = UsernamePasswordAuthenticationToken(monitoringPrincipal, "", monitoringAuthority)

            securityHolder.authentication(token)
            this.logger.info("logged in monitoring user")
        }
    }
}