package com.islands.model

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table(name = "tile")
class Tile(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,
        @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "tilemap_id")
        val tilemap: Tilemap? = null,
        @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "island_id")
        var island: Island? = null,
        @get: NotNull val x: Int,
        @get: NotNull val y: Int,
        @get: NotBlank val type: String = ""
)