package com.seok2.ets.examples.ui

import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("apis/examples")
class ExampleController {

    @GetMapping
    fun welcome() : ResponseEntity<String> {
        logger.info { "hello store" }
       return ResponseEntity.ok("OK")
    }
}
