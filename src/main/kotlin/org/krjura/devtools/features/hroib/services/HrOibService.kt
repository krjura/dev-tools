package org.krjura.devtools.features.hroib.services

import org.krjura.devtools.features.hroib.HrOibServiceUtils.joinDigits
import org.krjura.devtools.features.hroib.HrOibServiceUtils.calculateControlDigit
import org.krjura.devtools.features.hroib.HrOibServiceUtils.generateFirstTenDigits
import org.krjura.devtools.features.hroib.HrOibServiceUtils.isNotNumber
import org.krjura.devtools.features.hroib.HrOibServiceUtils.toDigits
import org.springframework.stereotype.Service
import java.lang.IllegalStateException

@Service
class HrOibService {

    fun generateOib(): String {
        val oib = generateFirstTenDigits()
        val controlDigit = calculateControlDigit(oib);

        return ensureOibIsValid(joinDigits(oib, controlDigit))
    }

    /**
        1. prva znamenka zbroji se s brojem 10
        2. dobiveni  zbroj  cjelobrojno  (s  ostatkom)  podijeli  se  brojem  10;
            ako  je  dobiveni ostatak 0 zamijeni se brojem 10 (ovaj broj je tzv. međuostatak)
        3. dobiveni međuostatak pomnoži se brojem 2
        4. dobiveni  umnožak  cjelobrojno  (s  ostatkom)  podijeli se  brojem  11;
            ovaj  ostatak matematički nikako ne može biti 0 jer je rezultat prethodnog koraka uvijek paran broj
        5. slijedeća znamenka zbroji se s ostatkom u prethodnom koraku
        6. ponavljaju se koraci 2, 3, 4 i 5  dok se ne potroše sve znamenke
        7. razlika između broja 11 i ostatka u zadnjem koraku je kontrolna znamenka;
            ako je ostatak 1 kontrolna znamenka je 0 (11-1=10, a 10 ima dvije znamenke)

        link: https://regos.hr/app/uploads/2018/07/KONTROLA-OIB-a.pdf
     */
    fun validate(oib: String): Boolean {
        if(isNotNumber(oib)) {
            return false
        }

        val digits = toDigits(oib);
        return digits[10] == calculateControlDigit(digits);
    }

    private fun ensureOibIsValid(oib: String): String {
        if(!validate(oib)) {
            throw IllegalStateException("generated oib is not valid");
        }

        return oib;
    }
}