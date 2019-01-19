package org.krjura.devtools.support

import com.fasterxml.jackson.core.JsonProcessingException
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.web.context.WebApplicationContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Before
import java.io.IOException
import org.springframework.test.web.servlet.MvcResult

@RunWith(SpringJUnit4ClassRunner::class)
@ProjectTest
abstract class TestBase {

    @Autowired
    lateinit var context: WebApplicationContext

    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper;

    @Before
    fun beforeTestBase() {

        mockMvc = MockMvcBuilders
            .webAppContextSetup(this.context)
            .apply<DefaultMockMvcBuilder>(springSecurity())
            .build();
    }

    @Throws(JsonProcessingException::class)
    fun toJson(value: Any): String {
        return this.objectMapper.writeValueAsString(value);
    }

    @Throws(IOException::class)
    fun <T> fromJson(result: MvcResult, clazz: Class<T>): T {
        return fromJson(result.response.contentAsString, clazz)
    }

    @Throws(IOException::class)
    fun <T> fromJson(content: String, clazz: Class<T>): T {
        return this.objectMapper.readValue(content, clazz)
    }
}