package org.krjura.devtools.features.password.controllers.pojo

import org.krjura.devtools.features.password.validations.PasswordCharacterCount
import org.krjura.devtools.validations.Required

data class PasswordRequest(
    @Required val useCapitalLetters: Boolean,
    @Required val useSmallLetters: Boolean,
    @Required val useNumbers: Boolean,
    @PasswordCharacterCount val characterCount: Int)
