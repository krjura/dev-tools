package org.krjura.devtools.features.http.controllers

import org.springframework.core.io.ByteArrayResource
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import java.util.function.Consumer
import javax.servlet.http.HttpServletRequest

@Controller
class HttpMirrorController {

  @RequestMapping(
    value = ["/api/v1/http/mirror"],
    method = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE]
  )
  fun process(
    request: HttpServletRequest,
    @RequestBody(required = false) body: ByteArray?): ResponseEntity<ByteArrayResource> {

    val headers = HttpHeaders();

    request
      .headerNames
      .toList()
      .forEach(Consumer {
          headerName -> headers["XX-$headerName"] = request.getHeaders(headerName).toList()
      })

    headers["XX-Method"] = request.method
    headers["Content-Type"] = request.contentType;

    return ResponseEntity
      .ok()
      .headers(headers)
      .body(ByteArrayResource(body ?: ByteArray(0)))
  }
}