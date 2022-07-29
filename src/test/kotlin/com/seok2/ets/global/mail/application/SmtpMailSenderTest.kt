package com.seok2.ets.global.mail.application

import com.icegreen.greenmail.configuration.GreenMailConfiguration
import com.icegreen.greenmail.junit5.GreenMailExtension
import com.icegreen.greenmail.util.GreenMailUtil
import com.icegreen.greenmail.util.ServerSetupTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource


@TestPropertySource(locations = ["/application.yml"])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class SmtpMailSenderTest(
    @Autowired
    private val sender: MailSender
) {

    companion object {
        @JvmField
        @RegisterExtension
        val smtp: GreenMailExtension = GreenMailExtension(ServerSetupTest.SMTP)
            .withConfiguration(GreenMailConfiguration.aConfig().withUser("seok2", "welcome"))
            .withPerMethodLifecycle(false)
    }

    @Test
    fun `이메일 발송 테스트`() {
        val content = "Have a good day"
        val to = "seok2@spring.io"

        sender.send(to, "Hello Green Mail", content)
        smtp.waitForIncomingEmail(1)
        val received = smtp.receivedMessages[0]

        assertThat(GreenMailUtil.getBody(received)).isEqualTo(content)
        assertThat(received.allRecipients[0].toString()).isEqualTo(to)
    }

}