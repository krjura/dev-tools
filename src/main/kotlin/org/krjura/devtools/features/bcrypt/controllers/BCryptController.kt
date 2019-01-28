package org.krjura.devtools.features.bcrypt.controllers

import org.krjura.devtools.features.bcrypt.pojo.BCryptWebRequest
import org.krjura.devtools.features.bcrypt.pojo.BCryptWebResponse
import org.krjura.devtools.features.bcrypt.services.BCryptService
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
class BCryptController(val service: BCryptService) {

    @PostMapping(
        value = ["/api/v1/bcrypt/password"],
        consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE],
        produces = [MediaType.APPLICATION_JSON_UTF8_VALUE]
    )
    fun calculate(
        @RequestBody @Valid request: BCryptWebRequest): ResponseEntity<BCryptWebResponse> {

        val (duration, encoded) = StopWatchUtils
            .execute { this.service.calculate(request.iterations, request.data) }

        return ResponseEntity(
            BCryptWebResponse(request.iterations, request.data, encoded),
            ServerTimingBuilder().addApp(duration).build(),
            HttpStatus.OK
        )
    }
}