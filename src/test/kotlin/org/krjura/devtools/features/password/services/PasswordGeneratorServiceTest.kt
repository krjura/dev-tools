package org.krjura.devtools.features.password.services

import org.krjura.devtools.features.password.services.pojo.PasswordGeneratorConfig

import org.assertj.core.api.Assertions.assertThat;
import org.junit.Test
import java.util.regex.Pattern

class PasswordGeneratorServiceTest {

    @Test
    fun generateOnlyCapitalLetters() {
        val service = PasswordGeneratorService();

        val password = service.generatePassword(PasswordGeneratorConfig(true, false, false, 10));

        assertThat(password).hasSize(10);
        assertThat(password).containsPattern(Pattern.compile("[A-Z]{10}"))
    }

    @Test
    fun generateOnlySmallLetters() {
        val service = PasswordGeneratorService();

        val password = service.generatePassword(PasswordGeneratorConfig(false, true, false, 10));

        assertThat(password).hasSize(10);
        assertThat(password).containsPattern(Pattern.compile("[a-z]{10}"))
    }

    @Test
    fun generateOnlyNumbers() {
        val service = PasswordGeneratorService();

        val password = service.generatePassword(PasswordGeneratorConfig(false, false, true, 10));

        assertThat(password).hasSize(10);
        assertThat(password).containsPattern(Pattern.compile("[0-9]{10}"))
    }

    @Test
    fun generateOnlyLetters() {
        val service = PasswordGeneratorService();

        val password = service.generatePassword(PasswordGeneratorConfig(true, true, false, 10));

        assertThat(password).hasSize(10);
        assertThat(password).containsPattern(Pattern.compile("[a-zA-Z]{10}"))
    }

    @Test
    fun generateAll() {
        val service = PasswordGeneratorService();

        val password = service.generatePassword(PasswordGeneratorConfig(true, true, true, 10));

        assertThat(password).hasSize(10);
        assertThat(password).containsPattern(Pattern.compile("[a-zA-z0-9]{10}"))
    }

    @Test
    fun generateNoneAndAll() {
        val service = PasswordGeneratorService();

        val password = service.generatePassword(PasswordGeneratorConfig(false, false, false, 10));

        assertThat(password).hasSize(10);
        assertThat(password).containsPattern(Pattern.compile("[a-zA-z0-9]{10}"))
    }
}