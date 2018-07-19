package com.islands.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class MapResponseData(
        @JsonProperty("id") val id: String,
        @JsonProperty("type") val type: String
)