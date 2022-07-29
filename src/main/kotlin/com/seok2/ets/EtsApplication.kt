package com.seok2.ets

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class EtsApplication

fun main(args: Array<String>) {
    runApplication<EtsApplication>(*args)
}
