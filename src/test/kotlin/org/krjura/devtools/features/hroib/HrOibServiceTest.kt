package org.krjura.devtools.features.hroib

import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test
import org.krjura.devtools.features.hroib.services.HrOibService
import java.util.regex.Pattern

class HrOibServiceTest {

    @Test
    fun generateOib() {
        val service = HrOibService();
        val expectedPattern = Pattern.compile("\\d{11}");

        assertTrue(expectedPattern.matcher(service.generateOib()).matches())
        assertTrue(expectedPattern.matcher(service.generateOib()).matches())
        assertTrue(expectedPattern.matcher(service.generateOib()).matches())
        assertTrue(expectedPattern.matcher(service.generateOib()).matches())
        assertTrue(expectedPattern.matcher(service.generateOib()).matches())
        assertTrue(expectedPattern.matcher(service.generateOib()).matches())
        assertTrue(expectedPattern.matcher(service.generateOib()).matches())
        assertTrue(expectedPattern.matcher(service.generateOib()).matches())
        assertTrue(expectedPattern.matcher(service.generateOib()).matches())
        assertTrue(expectedPattern.matcher(service.generateOib()).matches())
    }

    @Test
    fun validateOib() {
        val service = HrOibService();

        assertThat(service.validate("69435151530")).isTrue();
        assertThat(service.validate("75471239711")).isTrue();
        assertThat(service.validate("29891904678")).isTrue();
        assertThat(service.validate("55822320367")).isTrue();
        assertThat(service.validate("63862714401")).isTrue();
        assertThat(service.validate("11070485218")).isTrue();
        assertThat(service.validate("91498725315")).isTrue();
        assertThat(service.validate("72544267426")).isTrue();
        assertThat(service.validate("00042856844")).isTrue();
    }
}