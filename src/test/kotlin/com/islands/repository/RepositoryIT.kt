package com.islands.repository

import com.islands.model.Island
import com.islands.model.Tilemap
import com.islands.utils.IslandsApplicationTestUtil
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@DataJpaTest
class RepositoryIT {
    @Autowired
    lateinit var entityManager: TestEntityManager
    @Autowired
    lateinit var tilemapRepository: TilemapRepository
    @Autowired
    lateinit var tileRepository: TileRepository
    @Autowired
    lateinit var islandRepository: IslandRepository

    @Before
    fun setUp() {
        entityManager.clear()
    }

    @Test
    fun `TilemapRepository 'save' should persist a tilemap into the database`() {
        assertTrue(tilemapRepository.findAll().count() == 0)

        val tilemap = Tilemap(mapId = "Imaginary")
        tilemapRepository.save(tilemap)

        assertTrue(tilemapRepository.findAll().count() == 1)
    }

    @Test
    fun `TileRepository 'saveAll' should persist a list of tiles into the database`() {
        // given
        val tilemap = Tilemap(mapId = "Imaginary")
        entityManager.persist(tilemap)
        entityManager.flush()

        // when
        val tiles = IslandsApplicationTestUtil.generateTiles(tilemap)
        tileRepository.saveAll(tiles)

        // then
        assertTrue(tileRepository.findAll().count() == 30)

    }

    @Test
    fun `IslandRepository 'save' should persist an island into the database`() {
        assertTrue(islandRepository.findAll().count() == 0)

        val island = Island()
        islandRepository.save(island)

        assertTrue(islandRepository.findAll().count() == 1)
    }

    @Test
    fun `IslandRepository 'findById' should retrieve the island matching the id passed by parameter`() {
        islandRepository.save(Island())
        islandRepository.save(Island())
        islandRepository.save(Island())

        assertTrue(islandRepository.findById(1).isPresent)
    }
}