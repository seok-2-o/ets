package com.seok2.ets.global.mail.application

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

@Component
class SmtpMailSender(private val sender : JavaMailSender) : MailSender {

    override fun send(to: String, subject: String, content: String) {
        val message = SimpleMailMessage()
        with(message) {
            setTo(to)
            setSubject(subject)
            setText(content)
        }
        sender.send(message)
    }
}