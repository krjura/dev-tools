package org.krjura.devtools.features.pki.controller

import org.krjura.devtools.features.pki.controller.pojo.GeneratePairRequest
import org.krjura.devtools.features.pki.controller.pojo.GeneratePairResponse
import org.krjura.devtools.features.pki.services.PkiService
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
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun generateKeyPair(@RequestBody @Valid request: GeneratePairRequest): ResponseEntity<GeneratePairResponse> {

        val pair = this.service.generatePair(request.keySize, request.algorithm)

        return ResponseEntity.ok(GeneratePairResponse(request.keySize, request.algorithm, pair.public, pair.private))
    }
}