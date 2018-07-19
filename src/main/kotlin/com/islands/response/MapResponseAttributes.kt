package com.islands.response

import com.fasterxml.jackson.annotation.JsonProperty

data class MapResponseAttributes(@JsonProperty("tiles") val tiles: Set<MapResponseAttributesTiles>)