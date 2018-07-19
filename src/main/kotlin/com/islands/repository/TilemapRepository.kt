package com.islands.repository

import com.islands.model.Tilemap
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TilemapRepository : CrudRepository<Tilemap, Long>