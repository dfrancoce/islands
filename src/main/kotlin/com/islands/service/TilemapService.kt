package com.islands.service

import com.islands.configuration.AppProperties
import com.islands.model.Tile
import com.islands.model.Tilemap
import com.islands.repository.TilemapRepository
import com.islands.response.MapResponse
import com.islands.response.MapResponseAttributes
import com.islands.response.MapResponseData
import com.github.kittinunf.fuel.httpGet
import mu.KLogging
import org.springframework.stereotype.Service

/**
 * This class contains the business logic related to the map
 */
@Service
class TilemapService(
        private val islandService: IslandService,
        private val tileService: TileService,
        private val tilemapRepository: TilemapRepository,
        private val appProperties: AppProperties) {

    companion object : KLogging()

    /**
     * Creates a map retrieving the information from the endpoint set in the app properties and
     * persisting it into the database
     */
    fun createMap() {
        logger.debug { "MapService - createMap - start" }

        appProperties.mapsUrl.httpGet().responseObject(MapResponse.Deserializer()) { _, _, result ->
            val (mapResponse, err) = result

            if (err == null) {
                if (!mapAlreadyExist(mapResponseData = mapResponse!!.data)) {
                    val tilemap = saveTilemap(mapResponseData = mapResponse!!.data)
                    val tiles = getTiles(tilemap, mapResponseAttributes = mapResponse.attributes)
                    saveIslands(tiles)
                    saveTiles(tiles)
                }
            } else {
                logger.error { "MapService - createMap - An error occurred = $err}" }
            }
        }
    }

    private fun mapAlreadyExist(mapResponseData: MapResponseData): Boolean {
        return tilemapRepository.findAll().any { tilemap -> tilemap.mapId == mapResponseData.id }
    }

    private fun saveTilemap(mapResponseData: MapResponseData): Tilemap {
        logger.debug { "MapService - saveTilemap - start" }
        val tilemap = Tilemap(mapId = mapResponseData.id)

        return tilemapRepository.save(tilemap)
    }

    private fun saveIslands(tiles: List<Tile>) {
        logger.debug { "MapService - saveIslands - start" }
        islandService.detectAndSaveIslands(tiles)
    }

    private fun saveTiles(tiles: List<Tile>) {
        logger.debug { "MapService - saveTiles - start" }
        tileService.save(tiles)
    }

    private fun getTiles(tilemap: Tilemap, mapResponseAttributes: MapResponseAttributes): List<Tile> {
        logger.debug { "MapService - getTiles - start" }

        return mapResponseAttributes.tiles.map { mapResponseAttrs ->
            Tile(x = mapResponseAttrs.x, y = mapResponseAttrs.y, type = mapResponseAttrs.type, tilemap = tilemap)
        }.toList()
    }


    /**
     * Returns the map with the id passed by parameter in an ASCII style
     *
     * @param id the id of the map
     */
    fun getAsciiMap(id: Long): String {
        logger.debug { "MapService - getAsciiMap with id $id - start" }

        val tiles = tileService.getAllTilesOfAMap(id)
        val maxX = tiles.map { tile -> tile.x }.max()
        val maxY = tiles.map { tile -> tile.y }.max()

        var asciiMap = ""
        for (y in maxY!! downTo 1) {
            asciiMap += "#"

            for (x in 1..maxX!!) {
                val tile = tiles.find { tile -> tile.x == x && tile.y == y }
                if (tile != null) asciiMap += getTileTypeCharacter(tile = tile)
            }

            asciiMap += "<br>"
        }

        return asciiMap
    }

    private fun getTileTypeCharacter(tile: Tile): String {
        return if (tile.type == "land") {
            " X"
        } else {
            " O"
        }
    }
}