package com.seok2.ets.seller

import com.icegreen.greenmail.util.GreenMailUtil
import com.seok2.ets.global.config.security.ui.dto.SignInSellerCommand
import com.seok2.ets.helper.SpringBootTestWithSandboxMail
import com.seok2.ets.seller.ui.CreateSellerCommand
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.response.Response
import org.apache.http.HttpStatus
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.jupiter.api.Test
import org.springframework.boot.test.web.server.LocalServerPort

private const val 셀러_비밀번호 = "1q2w3e!!"
private const val 셀러_이름 = "이재석"

class SellerApiTest : SpringBootTestWithSandboxMail() {

    @LocalServerPort
    var port: Int = 0


    @Test
    fun `회원 가입`() {
        val 이메일 = "seok1@kakao.com"
        `회원가입`(이메일, 셀러_비밀번호, 셀러_이름) Then {
            statusCode(HttpStatus.SC_CREATED)
            body("name", equalTo(셀러_이름))
        }
    }

    @Test
    fun `정상적으로 로그인 하는 경우`() {
        val 이메일 = "seok2@kakao.com"
        `회원가입 및 인증`(이메일, 셀러_비밀번호, 셀러_이름)
        `로그인`(이메일, 셀러_비밀번호) Then {
            statusCode(HttpStatus.SC_OK)
            header("Authorization", notNullValue())
        } Extract {
            header("Authorization")
        }
    }

    @Test
    fun `이메일 인증을 하지 않은 경우 로그인할 수 없다`() {
        val 이메일 = "seok2@kakao.com"
        `회원가입`(이메일, 셀러_비밀번호, 셀러_이름)
        `로그인`(이메일, 셀러_비밀번호) Then {
            statusCode(HttpStatus.SC_FORBIDDEN)
        }
    }

    @Test
    fun `비밀번호 일치하지 않을 틀린 경우`() {
        val 이메일 = "seok3@kakao.com"
        `회원가입`(이메일, 셀러_비밀번호, 셀러_이름)
        `로그인`(이메일, "1234") Then {
            statusCode(HttpStatus.SC_UNAUTHORIZED)
        }
    }


    fun `회원가입`(email: String, password: String, name: String): Response {
        val command = CreateSellerCommand(email, password, name)
        return Given {
            port(port)
            contentType(ContentType.JSON)
            body(command)
        } When {
            post("/apis/sellers")
        }
    }

    fun `회원가입 및 인증`(email: String, password: String, name: String) {
        `회원가입`(email, password, name)

        smtp.waitForIncomingEmail(1)
        val received = smtp.receivedMessages[0]
        val link = GreenMailUtil.getBody(received)

        Given {
            port(port)
        } When {
            get(link)
        } Then {
            statusCode(HttpStatus.SC_NO_CONTENT)
        }
    }

    fun `로그인`(email: String, password: String): Response {
        val command = SignInSellerCommand(email, password)
        return Given {
            port(port)
            contentType(ContentType.JSON)
            body(command)
        } When {
            post("/apis/sellers/sign-in")
        }
    }

}
