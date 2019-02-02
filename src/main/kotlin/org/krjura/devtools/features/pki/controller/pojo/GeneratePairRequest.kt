package org.krjura.devtools.features.pki.controller.pojo

import org.krjura.devtools.features.pki.enums.PkiAlgorithm
import org.krjura.devtools.features.pki.enums.PkiKeySizes

data class GeneratePairRequest(val keySize: PkiKeySizes, val algorithm: PkiAlgorithm)