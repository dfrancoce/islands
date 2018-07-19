package com.islands.model

import javax.persistence.*

@Entity
@Table(name = "island")
data class Island (@Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0)