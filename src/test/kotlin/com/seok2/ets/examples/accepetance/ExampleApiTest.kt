package com.seok2.ets.examples.accepetance

import com.seok2.ets.helper.AcceptanceTest
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.apache.http.HttpStatus
import org.junit.jupiter.api.Test


class ExampleApiTest : AcceptanceTest() {

    @Test
    fun examples() {
        When {
            get("/apis/examples")
        } Then {
            statusCode(HttpStatus.SC_OK)
        }
    }
}
