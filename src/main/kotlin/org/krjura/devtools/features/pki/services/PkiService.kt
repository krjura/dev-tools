package org.krjura.devtools.features.pki.services

import org.krjura.devtools.features.pki.enums.PkiAlgorithm
import org.krjura.devtools.features.pki.enums.PkiKeySizes
import org.krjura.devtools.features.pki.services.pojo.KeyPair
import org.springframework.stereotype.Service
import java.lang.StringBuilder
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import java.security.SecureRandom
import java.util.Base64

@Service
class PkiService {

    companion object {
        val random: SecureRandom = SecureRandom.getInstance("NativePRNGNonBlocking");
        const val KEY_LENGTH = 64;
    }

    fun generatePair(keySize: PkiKeySizes, algorithm: PkiAlgorithm): KeyPair {
        val generator: KeyPairGenerator = KeyPairGenerator.getInstance(algorithm.algorithm)
        generator.initialize(keySize.length, random)

        val pair = generator.genKeyPair();

        return KeyPair(convertRsaPublicKey(pair.public), convertRsaPrivateKey(pair.private))
    }

    private fun convertRsaPrivateKey(private: PrivateKey): String {
        val encoded = Base64.getEncoder().encodeToString(private.encoded);

        return prettify(encoded, KEY_LENGTH, "-----BEGIN RSA PRIVATE KEY-----", "-----END RSA PRIVATE KEY-----");
    }

    private fun convertRsaPublicKey(public: PublicKey): String {
        val encoded = Base64.getEncoder().encodeToString(public.encoded);

        return prettify(encoded, KEY_LENGTH, "-----BEGIN PUBLIC KEY-----", "-----END PUBLIC KEY-----")
    }

    private fun prettify(encoded: String, maxLength: Int, firstLine: String, lastLine: String): String {
        val formatted = StringBuilder();
        formatted.append(firstLine).append("\n");

        var i = 0;
        val n = encoded.length;
        while (i < n) {
            val left = n - i;
            val take = Math.min(left, maxLength);

            val selection = encoded.substring(i, i + take);
            formatted.append(selection).append("\n");
            i += take;
        }
        formatted.append(lastLine)

        return formatted.toString();
    }
}