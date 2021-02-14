package org.krjura.devtools.features.http.controllers

import org.junit.jupiter.api.Test
import org.krjura.devtools.support.TestBase
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class HttpMirrorControllerTest : TestBase() {

  @Test
  fun testGetMethod() {
    getMockMvc()
      .perform(get("/api/v1/headers/mirror")
        .header("Test1", "Test1")
        .header("Test2", "Test2")
      )
      .andExpect(status().isOk)
      .andExpect(header().string("XX-Test1", "Test1"))
      .andExpect(header().string("XX-Test2", "Test2"))
      .andExpect(header().string("XX-method", "GET"))
  }

  @Test
  fun testPostMethod() {
    getMockMvc()
      .perform(post("/api/v1/headers/mirror")
        .header("Test1", "Test1")
        .contentType(MediaType.TEXT_PLAIN)
        .content("demo")
      )
      .andExpect(status().isOk)
      .andExpect(header().string("XX-Test1", "Test1"))
      .andExpect(header().string("XX-content-type", "text/plain"))
      .andExpect(header().string("XX-method", "POST"))
      .andExpect(content().string("demo"))
  }

  @Test
  fun testPutMethod() {
    getMockMvc()
      .perform(put("/api/v1/headers/mirror")
        .header("Test1", "Test1")
        .contentType(MediaType.TEXT_PLAIN)
        .content("demo")
      )
      .andExpect(status().isOk)
      .andExpect(header().string("XX-Test1", "Test1"))
      .andExpect(header().string("XX-content-type", "text/plain"))
      .andExpect(header().string("XX-method", "PUT"))
      .andExpect(content().string("demo"))
  }

  @Test
  fun testDeleteMethod() {
    getMockMvc()
      .perform(delete("/api/v1/headers/mirror")
        .header("Test1", "Test1")
        .header("Test2", "Test2")
      )
      .andExpect(status().isOk)
      .andExpect(header().string("XX-Test1", "Test1"))
      .andExpect(header().string("XX-Test2", "Test2"))
      .andExpect(header().string("XX-method", "DELETE"))
  }
}