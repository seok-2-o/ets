package com.seok2.ets.examples.accepetance

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
@AutoConfigureMockMvc
class ExampleApiTest(@Autowired private var mvc : MockMvc) {

    @Test
    fun examples() {
        mvc.perform(get("/apis/examples"))
            .andExpect(status().isOk)
            .andExpect(content().string("OK"))
    }
}
