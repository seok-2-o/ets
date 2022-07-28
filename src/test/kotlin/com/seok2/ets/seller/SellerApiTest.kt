package com.seok2.ets.seller

import com.seok2.ets.seller.ui.CreateSellerCommand
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.apache.http.HttpStatus
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SellerApiTest {

    @LocalServerPort
    var port: Int = 0

    @Test
    fun welcome() {
        val command = CreateSellerCommand("seok2.dev@gmail.com", "1q2w3e!!", "이재석")
        Given {
            port(port)
            contentType(ContentType.JSON)
            log().all()
            body(command)
        } When {
            post("/apis/sellers")
        } Then {
            statusCode(HttpStatus.SC_CREATED)
            body("name", equalTo("이재석"))
        }
    }

}
