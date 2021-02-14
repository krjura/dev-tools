package org.krjura.devtools.features.auth

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class UserInfoController {

    @GetMapping(
        value = ["/api/v1/auth/user-info"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun userInfo(): ResponseEntity<UserInfoResponse> {
        return response(SecurityContextHolder.getContext());
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