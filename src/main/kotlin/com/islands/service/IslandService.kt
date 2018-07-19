package com.islands.service

import com.islands.model.Island
import com.islands.model.Tile
import com.islands.repository.IslandRepository
import com.islands.response.IslandResponse
import mu.KLogging
import org.springframework.stereotype.Service

/**
 * This class contains the business logic related to the islands
 */
@Service
class IslandService(private val islandRepository: IslandRepository, private val tileService: TileService) {
    companion object : KLogging()

    /**
     * Returns all the islands with their related tiles
     */
    fun getIslands(): List<IslandResponse> {
        logger.debug { "IslandService - getIslands - start" }

        val islands = islandRepository.findAll()
        return islands.map { island -> IslandResponse(island.id, tileService.getTilesOfIsland(island)) }.toList()
    }

    /**
     * Returns the island matching the id passed by parameter
     *
     * @param islandId the id of the island to retrieve
     */
    fun getIsland(islandId: Long): IslandResponse? {
        logger.debug { "IslandService - getIsland with id $islandId - start" }

        val island = islandRepository.findById(islandId)
        return IslandResponse(islandId, tileService.getTilesOfIsland(island.get()))
    }

    /**
     * Detects the islands in the tiles passed by parameter and save them into the database
     * using the IslandRepository
     *
     * @param tiles the list of tiles where we want to detect islands
     */
    fun detectAndSaveIslands(tiles: List<Tile>) {
        logger.debug { "IslandService - detectAndSaveIslands - start" }

        val maxX = tiles.map { tile -> tile.x }.max()
        val maxY = tiles.map { tile -> tile.y }.max()
        logger.debug { "IslandService - detectAndSaveIslands - maxX = $maxX, maxY = $maxY" }

        for (x in 0..maxX!!) {
            for (y in 0..maxY!!) {
                if (isALandTile(tiles, x, y)) {
                    logger.debug { "IslandService - detectAndSaveIslands - tile($x,$y) is a land tile" }
                    addTilesToIsland(tiles, x, y)
                }
            }
        }
    }

    private fun isALandTile(tiles: List<Tile>, x: Int, y: Int): Boolean {
        return findLandTileAtPosition(tiles, x, y) != null
    }

    private fun findLandTileAtPosition(tiles: List<Tile>, x: Int, y: Int): Tile? {
        return tiles.find { tile -> tile.x == x && tile.y == y && tile.type == "land" && tile.island == null }
    }

    private fun addTilesToIsland(tiles: List<Tile>, x: Int, y: Int, island: Island? = null) {
        logger.debug { "IslandService - addTilesToIsland - start" }

        val tile = findLandTileAtPosition(tiles, x, y) ?: return
        tile.island = island ?: createAndSaveIsland()

        val xs = intArrayOf(-1, 0, 1, 0)
        val ys = intArrayOf(0, 1, 0, -1)

        for (i in 0 until xs.size) {
            addTilesToIsland(tiles, x + xs[i], y + ys[i], tile.island)
        }
    }

    private fun createAndSaveIsland(): Island {
        logger.debug { "IslandService - createAndSaveIsland - start" }

        val island = Island()
        return islandRepository.save(island)
    }
}