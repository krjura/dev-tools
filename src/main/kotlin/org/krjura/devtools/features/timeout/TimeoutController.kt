package org.krjura.devtools.features.timeout

import org.krjura.devtools.features.pki.services.PkiService
import org.krjura.devtools.features.timeout.pojo.TimeoutResponse
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.time.Duration

@Controller
class TimeoutController(val service: PkiService) {

    @PostMapping(
        value = ["/api/v1/timeout"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun postTimeout(
        @RequestBody body: String,
        @RequestParam(name = "duration", defaultValue = "10") duration:Long ): TimeoutResponse {

        Thread.sleep(Duration.ofMillis(duration).toMillis());

        return TimeoutResponse(duration = duration);
    }

    @GetMapping(
        value = ["/api/v1/timeout"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun getTimeout(@RequestParam(name = "duration", defaultValue = "10") duration:Long ): TimeoutResponse {
        Thread.sleep(Duration.ofMillis(duration).toMillis());

        return TimeoutResponse(duration = duration);
    }
}