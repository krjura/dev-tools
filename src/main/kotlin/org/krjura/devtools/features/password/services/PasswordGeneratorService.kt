package org.krjura.devtools.features.password.services

import org.krjura.devtools.features.password.services.pojo.PasswordGeneratorConfig
import org.springframework.stereotype.Service
import java.util.Random

@Service
class PasswordGeneratorService {

    companion object {
        val capitalLetters: List<Char> = listOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'R', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z')

        val smallLetters: List<Char> = listOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 'r', 't', 'u', 'v', 'w', 'x', 'y', 'z')

        val numbers: List<Char> = listOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')

        val special: List<Char> = listOf('#', '$')

        val random = Random()
    }

    fun generatePassword(config: PasswordGeneratorConfig): String {

        val selectedConfig = validateConfig(config)

        val characters = calculateCharactersToUse(selectedConfig)
        val passwordCharacters = mutableListOf<Char>()

        // ensure there is at least one capital letter
        if(selectedConfig.useCapitalLetters) {
            passwordCharacters.add(capitalLetters[random.nextInt(capitalLetters.size)])
        }

        // ensure there is at least one small letter
        if(selectedConfig.useSmallLetters) {
            passwordCharacters.add(smallLetters[random.nextInt(smallLetters.size)])
        }

        // ensure there is at least one small letter
        if(selectedConfig.useNumbers) {
            passwordCharacters.add(numbers[random.nextInt(numbers.size)])
        }

        // ensure there is at least one special
        if(selectedConfig.useSpecial) {
            passwordCharacters.add(special[random.nextInt(special.size)])
        }

        val charactersToGenerate = selectedConfig.characterCount - passwordCharacters.size
        for (i in 1..charactersToGenerate) {
            passwordCharacters.add(characters[random.nextInt(characters.size)])
        }

        return passwordCharacters.joinToString("", "", "")
    }

    private fun validateConfig(config: PasswordGeneratorConfig): PasswordGeneratorConfig {
        var selectedConfig: PasswordGeneratorConfig = config

        if (!config.useNumbers && !config.useSmallLetters && !config.useCapitalLetters && !config.useSpecial) {
            selectedConfig = PasswordGeneratorConfig(
                useCapitalLetters = true,
                useSmallLetters = true,
                useNumbers = true,
                useSpecial = true,
                config.characterCount
            )
        }

        return selectedConfig
    }

    private fun calculateCharactersToUse(config: PasswordGeneratorConfig): List<Char> {
        val characters: MutableList<Char> = mutableListOf()

        if (config.useCapitalLetters) {
            characters.addAll(capitalLetters)
        }

        if (config.useSmallLetters) {
            characters.addAll(smallLetters)
        }

        if (config.useNumbers) {
            characters.addAll(numbers)
        }

        if (config.useSpecial) {
            characters.addAll(special)
        }

        characters.shuffle()

        return characters
    }
}