package org.krjura.devtools.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "application.security")
class SecurityConfiguration() {
    var monitoring: MonitoringConfig = MonitoringConfig()
}

class MonitoringConfig() {
    var tokens: MutableList<String> = ArrayList()
}