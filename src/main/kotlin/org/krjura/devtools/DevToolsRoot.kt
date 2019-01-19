package org.krjura.devtools

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DevToolsRoot

fun main(args: Array<String>) {
    runApplication<DevToolsRoot>(*args)
}