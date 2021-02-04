package org.krjura.devtools.features.auth

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.core.context.SecurityContext
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import reactor.core.publisher.Mono

@Controller
class UserInfoController {

    @GetMapping(
        value = ["/api/v1/auth/user-info"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun userInfo(): Mono<ResponseEntity<UserInfoResponse>> {
        return ReactiveSecurityContextHolder
            .getContext()
            .map { sc -> response(sc) }
            .defaultIfEmpty(ResponseEntity.ok(UserInfoResponseFactory.of()));
    }

    private fun response(sc: SecurityContext): ResponseEntity<UserInfoResponse> {

        val authentication = sc.authentication;

        return if (authentication == null) {
            ResponseEntity.ok(UserInfoResponseFactory.of())
        } else {
            ResponseEntity.ok(UserInfoResponseFactory.of(authentication.name))
        }
    }
}