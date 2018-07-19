package com.islands.service

import com.islands.model.Island
import com.islands.model.Tile
import com.islands.model.Tilemap
import com.islands.repository.TileRepository
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class TileServiceTest {
    private val tilemap = Tilemap(id = 1, mapId = "Imaginary")
    private val island = Island(1)
    private val tilesOfIsland: List<Tile> = listOf(
            Tile(1, tilemap, island, 1, 1, "land"),
            Tile(2, tilemap, island, 2, 1, "land")
    )

    @Mock
    private lateinit var tileRepository: TileRepository
    @InjectMocks
    private lateinit var sut: TileService

    @Before
    fun setUp() {
        Mockito.`when`(tileRepository.findAll()).thenReturn(tilesOfIsland)
    }

    @Test
    fun `'getTilesOfIsland' should retrieve all the tiles of the island passed by parameter`() {
        val islands = sut.getTilesOfIsland(island)
        assertTrue(islands.size == 2)
    }

    @Test
    fun `'getAllTilesOfAMap' should retrieve all the tiles of the map passed by parameter`() {
        val tiles = sut.getAllTilesOfAMap(1)
        assertTrue(tiles.size == 2)
    }
}