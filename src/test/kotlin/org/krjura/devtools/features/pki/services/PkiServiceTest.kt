package org.krjura.devtools.features.pki.services

import org.krjura.devtools.features.pki.enums.PkiAlgorithm
import org.krjura.devtools.features.pki.enums.PkiKeySizes

import org.assertj.core.api.Assertions.assertThat;
import org.junit.Test

class PkiServiceTest {

    @Test
    fun generateRsa512KeyPair() {
        val service = PkiService();

        val keyPair = service.generatePair(PkiKeySizes.L_512, PkiAlgorithm.RSA);

        assertThat(keyPair).isNotNull
        assertThat(keyPair.private).contains("-----BEGIN RSA PRIVATE KEY-----", "-----END RSA PRIVATE KEY-----")
        assertThat(keyPair.public).contains("-----BEGIN PUBLIC KEY-----", "-----END PUBLIC KEY-----")
    }

    @Test
    fun generateRsa1024KeyPair() {
        val service = PkiService();

        val keyPair = service.generatePair(PkiKeySizes.L_1024, PkiAlgorithm.RSA);

        assertThat(keyPair).isNotNull
        assertThat(keyPair.private).contains("-----BEGIN RSA PRIVATE KEY-----", "-----END RSA PRIVATE KEY-----")
        assertThat(keyPair.public).contains("-----BEGIN PUBLIC KEY-----", "-----END PUBLIC KEY-----")
    }

    @Test
    fun generateRsa2048KeyPair() {
        val service = PkiService();

        val keyPair = service.generatePair(PkiKeySizes.L_2048, PkiAlgorithm.RSA);

        println(keyPair.public)
        println(keyPair.private)

        assertThat(keyPair).isNotNull
        assertThat(keyPair.private).contains("-----BEGIN RSA PRIVATE KEY-----", "-----END RSA PRIVATE KEY-----")
        assertThat(keyPair.public).contains("-----BEGIN PUBLIC KEY-----", "-----END PUBLIC KEY-----")
    }

    @Test
    fun generateRsa4096KeyPair() {
        val service = PkiService();

        val keyPair = service.generatePair(PkiKeySizes.L_4096, PkiAlgorithm.RSA);

        assertThat(keyPair).isNotNull
        assertThat(keyPair.private).contains("-----BEGIN RSA PRIVATE KEY-----", "-----END RSA PRIVATE KEY-----")
        assertThat(keyPair.public).contains("-----BEGIN PUBLIC KEY-----", "-----END PUBLIC KEY-----")
    }
}