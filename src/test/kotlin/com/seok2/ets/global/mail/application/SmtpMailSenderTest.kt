package com.seok2.ets.global.mail.application

import com.icegreen.greenmail.util.GreenMailUtil
import com.seok2.ets.helper.SpringBootTestWithSandboxMail
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired


internal class SmtpMailSenderTest(
    @Autowired
    private val sender: MailSender
) : SpringBootTestWithSandboxMail() {


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