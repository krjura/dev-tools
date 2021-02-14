package org.krjura.devtools.features.pki.controller

import org.krjura.devtools.features.pki.controller.pojo.GeneratePairRequest
import org.krjura.devtools.features.pki.enums.PkiAlgorithm
import org.krjura.devtools.features.pki.enums.PkiKeySizes
import org.krjura.devtools.support.TestBase
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test
import org.krjura.devtools.features.pki.controller.pojo.GeneratePairResponse

class PkiControllerTest: TestBase() {

    @Test
    fun generateRsa512KeyPair() {
        val request = GeneratePairRequest(PkiKeySizes.L_512, PkiAlgorithm.RSA);

        val responseBody = getMockMvc()
            .perform(post("/api/v1/pki/generate-pair")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request))
            )
            .andExpect(status().isOk)
            .andReturn()
            .response
            .contentAsByteArray;

        val response = fromJson(responseBody, GeneratePairResponse::class.java)
        assertThat(response).isNotNull

        response.let {
            assertThat(response.algorithm).isEqualTo(request.algorithm);
            assertThat(response.keySize).isEqualTo(request.keySize);
            assertThat(response.private).contains("-----BEGIN RSA PRIVATE KEY-----", "-----END RSA PRIVATE KEY-----")
            assertThat(response.public).contains("-----BEGIN PUBLIC KEY-----", "-----END PUBLIC KEY-----")
        }
    }
}