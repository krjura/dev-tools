package org.krjura.devtools.features.hroib

import java.lang.IllegalArgumentException
import java.security.SecureRandom
import java.util.regex.Pattern

object HrOibServiceUtils {

    private val random: SecureRandom = SecureRandom.getInstance("NativePRNGNonBlocking");
    private val oibPattern: Pattern = Pattern.compile("\\d{11}")

    fun joinDigits(oib: IntArray, controlDigit: Int): String{
        return oib.joinToString(separator = "", prefix = "", postfix = "") + controlDigit.toString()
    }

    fun isNotNumber(oib: String): Boolean {
        return ! oibPattern.matcher(oib).matches();
    }

    fun toDigits(oib: String): IntArray {
        val digits = IntArray(oib.length);

        for(i in 0 until oib.length) {
            digits[i] = Integer.parseInt(oib.substring(i, i+1));
        }

        return digits;
    }

    fun generateFirstTenDigits(): IntArray {
        val oib = IntArray(10);

        for (i in 0 until 10) {
            oib[i] = random.nextInt(10);
        }

        return oib
    }

    fun calculateControlDigit(digits: IntArray): Int {
        ensureOibSizeForControlDigitCalculation(digits)

        var leftover = 10;

        // use only first 10 ints
        for(i in 0 until 10) {
            val digit = digits[i];

            val step1 = digit + leftover;

            val intermediate = step1 % 10;
            val step2 = if(intermediate == 0) 10 else intermediate;

            val step3 = step2 * 2;

            val step4 = step3 % 11;

            leftover = step4;
        }

        val step7 = 11 - leftover;

        return if(step7 == 10) 0 else step7;
    }

    private fun ensureOibSizeForControlDigitCalculation(digits: IntArray) {
        if (digits.size < 10) {
            throw IllegalArgumentException("got " + digits.size + " digits but expecting at least 10")
        }
    }
}