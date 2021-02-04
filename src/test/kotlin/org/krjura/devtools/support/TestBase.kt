package org.krjura.devtools.support

import com.fasterxml.jackson.core.JsonProcessingException
import org.springframework.beans.factory.annotation.Autowired
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import java.io.IOException
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.context.ApplicationContext
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ProjectTest
abstract class TestBase {

    @Autowired
    lateinit var context: ApplicationContext;

    lateinit var webClient: WebTestClient;

    @Autowired
    lateinit var objectMapper: ObjectMapper;

    @BeforeEach
    fun beforeTestBase() {

        this.webClient = WebTestClient
            .bindToApplicationContext(context)
            //.apply(springSecurity())
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
}