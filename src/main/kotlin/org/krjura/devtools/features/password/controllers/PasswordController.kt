package org.krjura.devtools.features.password.controllers

import org.krjura.devtools.features.password.controllers.pojo.PasswordRequest
import org.krjura.devtools.features.password.controllers.pojo.PasswordResponse
import org.krjura.devtools.features.password.services.PasswordGeneratorService
import org.krjura.devtools.features.password.services.pojo.PasswordGeneratorConfig
import org.krjura.devtools.utils.ServerTimingBuilder
import org.krjura.devtools.utils.StopWatchUtils
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import javax.validation.Valid

@Controller
class PasswordController(val service: PasswordGeneratorService) {

    @PostMapping(
        value = ["/api/v1/password/generate"],
        consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE],
        produces = [MediaType.APPLICATION_JSON_UTF8_VALUE]
    )
    fun generatePassword(
        @RequestBody @Valid request: PasswordRequest): ResponseEntity<PasswordResponse> {

        val serviceRequest = PasswordGeneratorConfig(
            request.useCapitalLetters, request.useSmallLetters, request.useNumbers, request.characterCount);

        val (duration, password) = StopWatchUtils.execute { service.generatePassword(serviceRequest) };

        return ResponseEntity(
            PasswordResponse(password),
            ServerTimingBuilder().addApp(duration).build(),
            HttpStatus.OK);
    }
}