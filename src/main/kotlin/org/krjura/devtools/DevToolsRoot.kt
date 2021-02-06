package org.krjura.devtools

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.ApplicationPidFileWriter

@SpringBootApplication
class DevToolsRoot

fun main(args: Array<String>) {
    val springApplication = SpringApplication(DevToolsRoot::class.java)
    springApplication.addListeners(ApplicationPidFileWriter())
    springApplication.run(*args)
}