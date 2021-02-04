package org.krjura.devtools.features.hroib.controller

import org.krjura.devtools.features.hroib.services.HrOibService
import org.krjura.devtools.features.hroib.controller.pojo.HrOibGenerateResponse
import org.krjura.devtools.features.hroib.controller.pojo.HrOibValidateResponse
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class HrOibController(var service: HrOibService) {

    @GetMapping(
        value = ["/api/v1/hroib/generate"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun generateOib(): ResponseEntity<HrOibGenerateResponse> {
        return ResponseEntity.ok(HrOibGenerateResponse(service.generateOib()))
    }

    @GetMapping(
        value = ["/api/v1/hroib/validate/{oib}"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun validateOib(@PathVariable("oib") oib: String): ResponseEntity<HrOibValidateResponse> {
        return ResponseEntity.ok(HrOibValidateResponse(service.validate(oib)))
    }

}