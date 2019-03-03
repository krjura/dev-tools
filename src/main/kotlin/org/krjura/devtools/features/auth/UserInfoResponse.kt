package org.krjura.devtools.features.auth

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class UserInfoResponse(val authenticated: Boolean, var username: String?)

object UserInfoResponseFactory {

    fun of(): UserInfoResponse {
        return UserInfoResponse(false, null);
    }

    fun of(username: String): UserInfoResponse {
        return UserInfoResponse(true, username);
    }
}