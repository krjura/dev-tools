package org.krjura.devtools.auth

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

interface AuthenticationSecurityHolder {

    fun authentication(token: Authentication)
}

class SpringAuthenticationSecurityHolder : AuthenticationSecurityHolder {
    override fun authentication(token: Authentication) {
        SecurityContextHolder.getContext().authentication = token;
    }
}

class SimpleAuthenticationSecurityHolder : AuthenticationSecurityHolder {

    private val tokens: MutableList<Authentication> = ArrayList()

    override fun authentication(token: Authentication) {
        tokens.add(token)
    }

    fun getTokens(): MutableList<Authentication>  {
        return tokens;
    }
}