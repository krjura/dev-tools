package org.krjura.devtools.features.base64.services

import org.springframework.stereotype.Service
import java.util.Base64

@Service
class Base64Service {

    /**
     * decodes binary data using Base64 algorithm
     *
     * @param request - binary data to base64 decode
     * @return binary data
     */
    fun decodeBytes(request: ByteArray): ByteArray {
        return Base64.getDecoder().decode(request);
    }

    /**
     * encodes binary data using Base64 algorithm
     *
     * @param request - binary data to base64 encode
     * @return binary encoded data
     */
    fun encodeBytes(request: ByteArray): String {
        return Base64.getEncoder().encodeToString(request);
    }

}