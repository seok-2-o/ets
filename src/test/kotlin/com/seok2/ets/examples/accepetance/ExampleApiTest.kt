package com.seok2.ets.examples.accepetance

import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.apache.http.HttpStatus
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.TestPropertySource

@TestPropertySource(locations = ["/application.yml"])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExampleApiTest {

    @LocalServerPort
    var port: Int = 0

    @Test
    fun examples() {
        Given {
            port(port)
            log().all()
        } When {
            get("/apis/examples")
        } Then {
            statusCode(HttpStatus.SC_OK)
        }
    }
}
