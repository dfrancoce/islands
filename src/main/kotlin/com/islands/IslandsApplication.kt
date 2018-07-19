package com.islands

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class IslandsApplication

fun main(args: Array<String>) {
    runApplication<IslandsApplication>(*args)
}
