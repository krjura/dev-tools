package org.krjura.devtools.utils

import org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.krjura.devtools.auth.AllowPrivateAndLocalIps

class AllowPrivateAndLocalIpsTest {

  @ParameterizedTest
  @CsvSource(
    "127.0.0.1,true",
    "10.0.0.0,true",
    "10.255.255.255,true",
    "11.0.0.0,false",
    "9.0.0.0,false",
    "192.1.1.1,false",
  )
  fun testIpAddress(address:String, result:Boolean) {
    assertThat(AllowPrivateAndLocalIps.isAllowed(address)).isEqualTo(result)
  }
}