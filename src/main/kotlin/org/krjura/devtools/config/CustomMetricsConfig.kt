package org.krjura.devtools.config

import io.micrometer.core.instrument.Meter
import io.micrometer.core.instrument.config.MeterFilter
import io.micrometer.core.instrument.config.MeterFilterReply
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CustomMetricsConfig {

  @Bean
  fun customMeterFilter(): MeterFilter {
    return CustomMeterFilter()
  }
}

class CustomMeterFilter : MeterFilter {

  private val allowedMetrics = listOf(
    "jvm.buffer.",
    "jvm.classes.",
    "jvm.gc.",
    "jvm.memory.",
    "jvm.threads.live",
    "jvm.threads.peak",
    "process.files."
  )

  override fun accept(id: Meter.Id): MeterFilterReply {
     return allowedMetrics
       .stream()
       .filter { entry -> id.name.startsWith(entry) }
       .findAny()
       .map { MeterFilterReply.ACCEPT }
       .orElse(MeterFilterReply.DENY)
  }
}