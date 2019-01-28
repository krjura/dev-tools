package org.krjura.devtools.utils

import org.springframework.http.HttpHeaders

class ServerTimingBuilder {

    companion object {
        const val CONST_APP = "app";
        const val CONST_HTTP_HEADER = "Server-Timing";
    }

    private var values: MutableList<ServerTimingData> = mutableListOf();

    fun add(type: String, value: String): ServerTimingBuilder {
        values.add(ServerTimingData(type, value))

        return this;
    }

    fun add(type: String, value: Long): ServerTimingBuilder {
        values.add(ServerTimingData(type, value.toString()))

        return this;
    }

    fun addApp(value: Long): ServerTimingBuilder {
        values.add(ServerTimingData(CONST_APP, value.toString()))

        return this;
    }

    fun build(): HttpHeaders {
        val headerValue = values.joinToString(separator = ", ") { it -> it.type + ";dur=" + it.value };

        val headers = HttpHeaders();
        headers.add(CONST_HTTP_HEADER, headerValue);

        return headers;
    }

}

data class ServerTimingData(val type: String, val value: String);