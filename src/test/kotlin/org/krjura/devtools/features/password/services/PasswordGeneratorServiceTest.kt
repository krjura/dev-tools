package org.krjura.devtools.features.password.services

import org.krjura.devtools.features.password.services.pojo.PasswordGeneratorConfig

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.regex.Pattern

class PasswordGeneratorServiceTest {

    @Test
    fun generateOnlyCapitalLetters() {
        val service = PasswordGeneratorService()

        val password = service.generatePassword(PasswordGeneratorConfig(
            useCapitalLetters = true,
            useSmallLetters = false,
            useNumbers = false,
            useSpecial = false,
            characterCount = 10
        ))

        assertThat(password).hasSize(10)
        assertThat(password).containsPattern(Pattern.compile("[A-Z]{10}"))
    }

    @Test
    fun generateOnlySmallLetters() {
        val service = PasswordGeneratorService()

        val password = service.generatePassword(PasswordGeneratorConfig(
            useCapitalLetters = false,
            useSmallLetters = true,
            useNumbers = false,
            useSpecial = false,
            characterCount = 10
        ))

        assertThat(password).hasSize(10)
        assertThat(password).containsPattern(Pattern.compile("[a-z]{10}"))
    }

    @Test
    fun generateOnlyNumbers() {
        val service = PasswordGeneratorService()

        val password = service.generatePassword(PasswordGeneratorConfig(
            useCapitalLetters = false,
            useSmallLetters = false,
            useNumbers = true,
            useSpecial = false,
            characterCount = 10
        ))

        assertThat(password).hasSize(10)
        assertThat(password).containsPattern(Pattern.compile("[0-9]{10}"))
    }

    @Test
    fun generateOnlySpecial() {
        val service = PasswordGeneratorService()

        val password = service.generatePassword(PasswordGeneratorConfig(
            useCapitalLetters = false,
            useSmallLetters = false,
            useNumbers = false,
            useSpecial = true,
            characterCount = 10
        ))

        assertThat(password).hasSize(10)
        assertThat(password).containsPattern(Pattern.compile("[#$]{10}"))
    }

    @Test
    fun generateOnlyLetters() {
        val service = PasswordGeneratorService()

        val password = service.generatePassword(PasswordGeneratorConfig(
            useCapitalLetters = true,
            useSmallLetters = true,
            useNumbers = false,
            useSpecial = false,
            characterCount = 10
        ))

        assertThat(password).hasSize(10)
        assertThat(password).containsPattern(Pattern.compile("[a-zA-Z]{10}"))
    }

    @Test
    fun generateAll() {
        val service = PasswordGeneratorService()

        val password = service.generatePassword(PasswordGeneratorConfig(
            useCapitalLetters = true,
            useSmallLetters = true,
            useNumbers = true,
            useSpecial = true,
            characterCount = 10
        ))

        assertThat(password).hasSize(10)
        assertThat(password).containsPattern(Pattern.compile("[a-zA-z0-9#$]{10}"))
    }

    @Test
    fun generateNoneAndAll() {
        val service = PasswordGeneratorService()

        val password = service.generatePassword(PasswordGeneratorConfig(
            useCapitalLetters = false,
            useSmallLetters = false,
            useNumbers = false,
            useSpecial = false,
            characterCount = 10
        ))

        assertThat(password).hasSize(10)
        assertThat(password).containsPattern(Pattern.compile("[a-zA-z0-9#$]{10}"))
    }
}