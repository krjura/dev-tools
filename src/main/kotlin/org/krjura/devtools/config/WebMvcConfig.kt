package org.krjura.devtools.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.CacheControl
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.util.concurrent.TimeUnit

@Configuration
class WebMvcConfig : WebMvcConfigurer {

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {

        val cacheControl = CacheControl
            .maxAge(7, TimeUnit.DAYS)
            .noTransform()
            .cachePrivate();

        registry
            .addResourceHandler("/portal/*.js", "/portal/*.css", "/portal/*.woff", "/portal/*.woff2")
            .addResourceLocations("file:web-resources/")
            .setCacheControl(cacheControl);

        registry
            .addResourceHandler("/portal/**")
            .addResourceLocations("file:web-resources/")
            .setCacheControl(CacheControl.noCache());
    }
}