package org.krjura.devtools.auth

import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.ReactiveAuthorizationManager
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.authorization.AuthorizationContext
import reactor.core.publisher.Mono
import java.lang.Byte
import java.net.InetAddress
import java.net.UnknownHostException

class AllowPrivateAndLocalIps: ReactiveAuthorizationManager<AuthorizationContext> {

  override fun check(authentication: Mono<Authentication>?, context: AuthorizationContext?): Mono<AuthorizationDecision> {
    val address = context?.exchange?.request?.remoteAddress?.address

    return if(address == null) {
      Mono.just(AuthorizationDecision(false))
    } else {
      Mono.just(AuthorizationDecision(isAllowed(address)))
    }
  }

  companion object {

    private const val pRangeLow = 10000000000L
    private const val pRangeHigh = 10255255255L

    public fun isAllowed(address: String): Boolean {
      return isAllowed(InetAddress.getByName(address))
    }

    public fun isAllowed(address: InetAddress?): Boolean {

      if(address == null) {
        return false
      }

      return try {
        val ip = address.address

        val value =
          1_000_000_000L * Byte.toUnsignedInt(ip[0]) +
            1_000_000L * Byte.toUnsignedInt(ip[1]) +
            1_000L * Byte.toUnsignedInt(ip[2]) +
            Byte.toUnsignedInt(ip[3])

        is10Range(value) || address.isLoopbackAddress
      } catch (e: UnknownHostException) {
        false
      }
    }

    private fun is10Range(value: Long): Boolean {
      return value in pRangeLow..pRangeHigh
    }
  }
}