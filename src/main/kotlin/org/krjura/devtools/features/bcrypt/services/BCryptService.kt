package org.krjura.devtools.features.bcrypt.services

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class BCryptService() {

    fun calculate(iterations: Int, data: String): String {
        return BCryptPasswordEncoder(iterations).encode(data)
    }
}