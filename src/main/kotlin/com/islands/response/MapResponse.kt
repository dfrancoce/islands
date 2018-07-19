package com.islands.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class MapResponse(
        @JsonProperty("data") val data: MapResponseData,
        @JsonProperty("attributes") val attributes: MapResponseAttributes
) {
    class Deserializer : ResponseDeserializable<MapResponse> {
        override fun deserialize(content: String) = Gson().fromJson(content, MapResponse::class.java)
    }
}