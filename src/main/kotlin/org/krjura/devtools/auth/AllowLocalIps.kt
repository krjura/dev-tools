package org.krjura.devtools.auth

import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.ReactiveAuthorizationManager
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.authorization.AuthorizationContext
import reactor.core.publisher.Mono

class AllowLocalIps: ReactiveAuthorizationManager<AuthorizationContext> {

  override fun check(authentication: Mono<Authentication>?, context: AuthorizationContext?): Mono<AuthorizationDecision> {
    val address = context?.exchange?.request?.remoteAddress?.address

    return if(address == null) {
      Mono.just(AuthorizationDecision(false))
    } else {
      Mono.just(AuthorizationDecision(address.isLoopbackAddress))
    }
  }
}