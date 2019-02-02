package org.krjura.devtools.features.pki.controller

import org.krjura.devtools.features.pki.controller.pojo.GeneratePairRequest
import org.krjura.devtools.features.pki.controller.pojo.GeneratePairResponse
import org.krjura.devtools.features.pki.services.PkiService
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
class PkiController(val service: PkiService) {

    @PostMapping(
        value = ["/api/v1/pki/generate-pair"],
        consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE],
        produces = [MediaType.APPLICATION_JSON_UTF8_VALUE]
    )
    fun generateKeyPair(
        @RequestBody @Valid request: GeneratePairRequest): ResponseEntity<GeneratePairResponse> {

        val (duration, pair) = StopWatchUtils.execute {
            this.service.generatePair(request.keySize, request.algorithm)
        };

        return ResponseEntity(
            GeneratePairResponse(request.keySize, request.algorithm, pair.public, pair.private),
            ServerTimingBuilder().addApp(duration).build(),
            HttpStatus.OK);
    }
}