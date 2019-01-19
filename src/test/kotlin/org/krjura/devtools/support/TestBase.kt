package org.krjura.devtools.support

import com.fasterxml.jackson.core.JsonProcessingException
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.beans.factory.annotation.Autowired
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Before
import java.io.IOException
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.context.ApplicationContext


@RunWith(SpringJUnit4ClassRunner::class)
@ProjectTest
abstract class TestBase {

    @Autowired
    lateinit var context: ApplicationContext;

    lateinit var webClient: WebTestClient;

    @Autowired
    lateinit var objectMapper: ObjectMapper;

    @Before
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