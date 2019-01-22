package org.krjura.devtools.controllers.bcrypt.pojo

import org.krjura.devtools.controllers.validations.BCryptDataConstraint
import org.krjura.devtools.controllers.validations.BCryptIterationConstraint

data class BCryptWebRequest(
    @BCryptIterationConstraint val iterations: Int,
    @BCryptDataConstraint val data: String
)