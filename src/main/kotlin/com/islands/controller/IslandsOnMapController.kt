package com.islands.controller

import com.islands.response.IslandResponse
import com.islands.service.IslandService
import com.islands.service.TilemapService
import io.swagger.annotations.ApiOperation
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * This class provides the endpoints to create a map, print a map in ASCII format
 * and get the islands information
 */
@RestController
@RequestMapping("/api")
class IslandsOnMapController(private val tilemapService: TilemapService, private val islandService: IslandService) {
    companion object : KLogging()

    @ApiOperation(value = "Creates entries in the database for the map requested to the islands API")
    @PostMapping("/maps")
    @ResponseStatus(HttpStatus.CREATED)
    fun createMap() {
        logger.debug { "IslandsOnMapController - createMap - start" }
        tilemapService.createMap()
    }

    @ApiOperation(value = "Returns all islands with the tiles that belong to them")
    @GetMapping("/islands")
    @ResponseStatus(HttpStatus.OK)
    fun getIslands(): List<IslandResponse> {
        logger.debug { "IslandsOnMapController - getIslands - start" }
        return islandService.getIslands()
    }

    @ApiOperation(value = "Returns the island with the matching id")
    @GetMapping("/islands/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getIsland(@PathVariable id: Long): IslandResponse? {
        logger.debug { "IslandsOnMapController - getIsland with id $id - start" }
        return islandService.getIsland(id)
    }

    @ApiOperation(value = "Returns the map corresponding to the id passed by parameter in ASCII style")
    @GetMapping("/asciiMap/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getAsciiMap(@PathVariable id: Long): String {
        logger.debug { "IslandsOnMapController - getAsciiMap with id $id - start" }
        return tilemapService.getAsciiMap(id)
    }
}