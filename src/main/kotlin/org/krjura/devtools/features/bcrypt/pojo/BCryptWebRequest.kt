package org.krjura.devtools.features.bcrypt.pojo

import org.krjura.devtools.features.bcrypt.validations.BCryptDataConstraint
import org.krjura.devtools.features.bcrypt.validations.BCryptIterationConstraint

data class BCryptWebRequest(
    @BCryptIterationConstraint val iterations: Int,
    @BCryptDataConstraint val data: String
)