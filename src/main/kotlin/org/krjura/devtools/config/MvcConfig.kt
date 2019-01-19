package org.krjura.devtools.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry

@Configuration
@EnableWebMvc
class MvcConfig : WebMvcConfigurer {

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry
            .addResourceHandler("/web/**")
            .addResourceLocations("file:web-resources/web/")
            .setCachePeriod(0)

        registry
            .addResourceHandler("/js/**")
            .addResourceLocations("file:web-resources/js/")
            .setCachePeriod(0)

    }
}