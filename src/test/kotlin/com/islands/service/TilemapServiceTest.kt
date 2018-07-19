package com.islands.service

import com.islands.configuration.AppProperties
import com.islands.model.Island
import com.islands.model.Tile
import com.islands.model.Tilemap
import com.islands.repository.TilemapRepository
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class TilemapServiceTest {
    private val tilemap = Tilemap(id = 1, mapId = "Imaginary")
    private val island = Island(1)
    private val tiles: List<Tile> = listOf(
            Tile(1, tilemap, island, 1, 1, "land"),
            Tile(2, tilemap, island, 2, 1, "land"),
            Tile(3, tilemap, island, 3, 1, "water"),
            Tile(4, tilemap, island, 4, 1, "water")
    )

    @Mock
    private lateinit var tileService: TileService
    @Mock
    private lateinit var islandService: IslandService
    @Mock
    private lateinit var tilemapRepository: TilemapRepository
    @Mock
    private lateinit var appProperties: AppProperties
    @InjectMocks
    private lateinit var sut: TilemapService

    @Before
    fun setUp() {
        `when`(tileService.getAllTilesOfAMap(island.id)).thenReturn(tiles)
    }

    @Test
    fun `'getAsciiMap' should return the map passed by parameter in ASCII style`() {
        val asciiMap = sut.getAsciiMap(tilemap.id)
        assertTrue(asciiMap.isNotEmpty())
    }
}