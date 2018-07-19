package com.islands.service

import com.islands.model.Island
import com.islands.model.Tile
import com.islands.repository.TileRepository
import com.islands.response.TileResponse
import mu.KLogging
import org.springframework.stereotype.Service

/**
 * This class contains the business logic related to the tile
 */
@Service
class TileService(private val tileRepository: TileRepository) {
    companion object : KLogging()

    /**
     * Returns the tiles of the island passed by parameter
     *
     * @param island the island
     */
    fun getTilesOfIsland(island: Island): List<TileResponse> {
        logger.debug { "TileService - getTilesOfIsland - start" }
        return tileRepository.findAll()
                .filter { tile -> tile.island == island }
                .map { tile -> TileResponse(tile.x, tile.y, tile.type) }
                .toList()
    }

    /**
     * Returns the tiles of the map
     *
     * @param idMap the id of the map
     */
    fun getAllTilesOfAMap(idMap: Long): List<Tile> {
        logger.debug { "TileService - getAllTilesOfAMap with id $idMap - start" }
        return tileRepository.findAll().filter { tile -> tile.tilemap!!.id == idMap }.toList()
    }

    /**
     * Persists into the database the tiles passed by parameter
     *
     * @param tiles
     */
    fun save(tiles: List<Tile>) {
        logger.debug { "TileService - save - start" }
        tileRepository.saveAll(tiles)
    }
}