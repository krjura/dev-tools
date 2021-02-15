package org.krjura.devtools.support

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.io.IOException

@ExtendWith(SpringExtension::class)
@ProjectTest
abstract class TestBase {

    @Autowired
    private lateinit var context: WebApplicationContext

    @Autowired
    private lateinit var objectMapper: ObjectMapper;

    private lateinit var mockMvc: MockMvc

    fun getMockMvc():MockMvc {
        return mockMvc
    }

    @BeforeEach
    fun beforeTestBase() {
        this.mockMvc = MockMvcBuilders
            .webAppContextSetup(this.context)
            .build();
    }

    @Throws(JsonProcessingException::class)
    fun toJson(value: Any): String {
        return this.objectMapper.writeValueAsString(value);
    }

    @Throws(IOException::class)
    fun <T> fromJson(content: String, clazz: Class<T>): T {
        return this.objectMapper.readValue(content, clazz)
    }

    @Throws(IOException::class)
    fun <T> fromJson(content: ByteArray, clazz: Class<T>): T {
        return this.objectMapper.readValue(content, clazz)
    }
}