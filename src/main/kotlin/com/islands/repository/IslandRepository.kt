package com.islands.repository

import com.islands.model.Island
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface IslandRepository : CrudRepository<Island, Long>