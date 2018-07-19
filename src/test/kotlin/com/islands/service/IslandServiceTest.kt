package com.islands.service

import com.islands.model.Island
import com.islands.model.Tilemap
import com.islands.repository.IslandRepository
import com.islands.utils.IslandsApplicationTestUtil
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class IslandServiceTest {
    private val tilemap = Tilemap(mapId = "Imaginary")
    private val island = Island(1)
    private val islands: List<Island> = listOf(Island(1), Island(2), Island(3))

    @Mock
    private lateinit var islandRepository: IslandRepository
    @Mock
    private lateinit var tileService: TileService
    @InjectMocks
    private lateinit var sut: IslandService

    @Before
    fun setUp() {
        `when`(islandRepository.save(ArgumentMatchers.any(Island::class.java))).thenReturn(island)
        `when`(islandRepository.findAll()).thenReturn(islands)
        `when`(islandRepository.findById(1)).thenReturn(Optional.of(island))
    }

    @Test
    fun `'detectAndSaveIslands' should detect the islands on the map`() {
        val tiles = IslandsApplicationTestUtil.generateTiles(tilemap)
        sut.detectAndSaveIslands(tiles = tiles)

        assertTrue(tiles.filter { tile -> tile.island != null }.toList().isNotEmpty())
    }

    @Test
    fun `'getIslands' should retrieve all the islands of the map`() {
        val islands = sut.getIslands()
        assertTrue(islands.size == 3)
    }

    @Test
    fun `'getIsland' should retrieve the island matching the id passed by parameter`() {
        val islandResponse = sut.getIsland(1)
        assertTrue(islandResponse != null)
    }
}