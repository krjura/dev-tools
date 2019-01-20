package org.krjura.devtools.controllers

import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import java.nio.file.Paths

@Controller
class PortalController {

    @GetMapping(value = ["/", "/portal", "/portal/op/**"])
    fun rootRedirect(response: ServerHttpResponse): ResponseEntity<Resource> {
        return ResponseEntity.ok(FileSystemResource(Paths.get("web-resources/index.html")))
    }
}