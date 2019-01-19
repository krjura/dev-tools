package org.krjura.devtools.services

import org.springframework.stereotype.Service
import java.util.Base64

@Service
class Base64Service {

    fun decodeBytes(request: ByteArray): ByteArray {
        return Base64.getDecoder().decode(request);
    }

    fun encodeBytes(request: ByteArray): String {
        return Base64.getEncoder().encodeToString(request);
    }

}