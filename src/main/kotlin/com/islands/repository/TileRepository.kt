package com.islands.repository

import com.islands.model.Tile
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TileRepository : CrudRepository<Tile, Long>