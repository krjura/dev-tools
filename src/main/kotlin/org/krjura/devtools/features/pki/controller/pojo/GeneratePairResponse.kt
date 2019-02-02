package org.krjura.devtools.features.pki.controller.pojo

import org.krjura.devtools.features.pki.enums.PkiAlgorithm
import org.krjura.devtools.features.pki.enums.PkiKeySizes

data class GeneratePairResponse(
    val keySize: PkiKeySizes,
    val algorithm: PkiAlgorithm,
    val public: String,
    val private: String
)