package com.islands.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import javax.validation.constraints.NotNull

@Component
@ConfigurationProperties(prefix = "app")
class AppProperties {
    @NotNull lateinit var mapsUrl: String
}