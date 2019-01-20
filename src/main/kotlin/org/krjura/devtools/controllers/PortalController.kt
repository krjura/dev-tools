package org.krjura.devtools.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import java.net.URI

@Controller
class PortalController {

    companion object {
        val ROOT_PAGE = URI("/portal/index.html");
    }

    @GetMapping(value = ["/", "/portal"])
    fun rootRedirect(response: ServerHttpResponse) {
        response.statusCode = HttpStatus.FOUND;
        response.headers.location = ROOT_PAGE;
    }

    @GetMapping(value = ["*"])
    fun subPageRedirect(response: ServerHttpResponse) {
        response.statusCode = HttpStatus.FOUND;
        response.headers.location = ROOT_PAGE;
    }
}