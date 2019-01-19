package org.krjura.devtools.controllers

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import javax.servlet.http.HttpServletResponse

@Controller
class PortalController {

    companion object {
        const val ROOT_PAGE = "/portal/index.html";
    }

    @GetMapping("/")
    fun rootRedirect(response: HttpServletResponse) {
        response.setStatus(HttpStatus.FOUND.value());
        response.setHeader(HttpHeaders.LOCATION, ROOT_PAGE)
    }

    @GetMapping("/portal")
    fun portalRedirect(response: HttpServletResponse) {
        response.setStatus(HttpStatus.FOUND.value());
        response.setHeader(HttpHeaders.LOCATION, ROOT_PAGE)
    }
}