package org.krjura.devtools.features.timeout

import org.krjura.devtools.features.pki.services.PkiService
import org.krjura.devtools.features.timeout.pojo.TimeoutResponse
import org.reactivestreams.Publisher
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.time.Duration

@Controller
class TimeoutController(val service: PkiService) {

    @PostMapping(
        value = ["/api/v1/timeout"],
        produces = [MediaType.APPLICATION_JSON_UTF8_VALUE]
    )
    @ResponseBody
    fun postTimeout(
        @RequestBody body: Publisher<String>,
        @RequestParam(name = "duration", defaultValue = "10") duration:Long ): Mono<TimeoutResponse> {

        return Mono
            .just(TimeoutResponse(duration = duration))
            .delaySubscription(Duration.ofSeconds(duration))

    }

    @GetMapping(
        value = ["/api/v1/timeout"],
        produces = [MediaType.APPLICATION_JSON_UTF8_VALUE]
    )
    @ResponseBody
    fun getTimeout(@RequestParam(name = "duration", defaultValue = "10") duration:Long ): Mono<TimeoutResponse> {
        return Mono
            .just(TimeoutResponse(duration = duration))
            .delaySubscription(Duration.ofSeconds(duration))
    }
}