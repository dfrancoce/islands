package com.islands.utils

import com.islands.model.Tile
import com.islands.model.Tilemap


object IslandsApplicationTestUtil {
    private val landTiles = listOf(Pair(1, 1), Pair(2, 1), Pair(5, 1), Pair(2, 2), Pair(5, 3), Pair(3, 4), Pair(4, 4), Pair(5, 4), Pair(4, 5))

    fun generateTiles(tilemap: Tilemap): List<Tile> {
        val tiles: MutableList<Tile> = mutableListOf()

        for (i in 1..6) {
            for (j in 1..5) {
                val tile = Tile(x = i, y = j, type = getTileType(i, j), tilemap = tilemap)
                tiles.add(tile)
            }
        }

        return tiles
    }

    private fun getTileType(x: Int, y: Int): String {
        val pair = Pair(x, y)
        return if (landTiles.contains(pair)) "land" else "water"
    }
}