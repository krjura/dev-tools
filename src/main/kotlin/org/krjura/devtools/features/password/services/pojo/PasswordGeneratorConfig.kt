package org.krjura.devtools.features.password.services.pojo

data class PasswordGeneratorConfig(
    val useCapitalLetters: Boolean,
    val useSmallLetters: Boolean,
    val useNumbers: Boolean,
    val useSpecial: Boolean,
    val characterCount: Int) {

}
