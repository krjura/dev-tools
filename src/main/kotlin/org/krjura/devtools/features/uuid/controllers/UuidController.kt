package org.krjura.devtools.features.uuid.controllers

import org.krjura.devtools.features.uuid.controllers.pojo.UuidWebResponse
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import java.util.UUID

@Controller
class UuidController {

    @GetMapping(
        value = ["/api/v1/password/generate"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun generateType4Uuid(): ResponseEntity<UuidWebResponse> {
        return ResponseEntity.ok(UuidWebResponse(UUID.randomUUID().toString()));
    }
}