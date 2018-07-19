package com.islands.model

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "tilemap")
class Tilemap(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
        @get: NotBlank val mapId: String = ""
)