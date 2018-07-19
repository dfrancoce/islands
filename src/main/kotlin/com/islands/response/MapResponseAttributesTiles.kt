package com.islands.response

import com.fasterxml.jackson.annotation.JsonProperty

data class MapResponseAttributesTiles (
        @JsonProperty("x") val x: Int,
        @JsonProperty("y") val y: Int,
        @JsonProperty("type") val type: String
)