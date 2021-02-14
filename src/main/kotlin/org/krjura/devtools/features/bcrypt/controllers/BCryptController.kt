package org.krjura.devtools.features.bcrypt.controllers

import org.krjura.devtools.features.bcrypt.pojo.BCryptWebRequest
import org.krjura.devtools.features.bcrypt.pojo.BCryptWebResponse
import org.krjura.devtools.features.bcrypt.services.BCryptService
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
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun calculate(@RequestBody @Valid request: BCryptWebRequest): ResponseEntity<BCryptWebResponse> {

        val encoded = this.service.calculate(request.iterations, request.data)

        return ResponseEntity.ok(BCryptWebResponse(request.iterations, request.data, encoded))
    }
}