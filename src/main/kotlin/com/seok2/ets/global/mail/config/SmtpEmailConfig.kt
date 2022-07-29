package com.seok2.ets.global.mail.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import java.util.*


@Configuration
class SmtpEmailConfig {

    @Value("\${spring.mail.transport.protocol}")
    lateinit var protocol: String
    @Value("\${spring.mail.properties.mail.smtp.auth}")
    private var auth : Boolean = false
    @Value("\${spring.mail.properties.mail.smtp.starttls.enable}")
    private var starttls : Boolean = false
    @Value("\${spring.mail.debug}")
    private var debug : Boolean = false
    @Value("\${spring.mail.host}")
    private lateinit var host: String
    @Value("\${spring.mail.port}")
    private var port : Int = 0
    @Value("\${spring.mail.username}")
    private lateinit var username: String
    @Value("\${spring.mail.password}")
    private lateinit var password: String
    @Value("\${spring.mail.default-encoding}")
    private lateinit var encoding: String

    @Bean
    fun javaMailSender() : JavaMailSender {
        val sender = JavaMailSenderImpl()
        val properties = Properties()
        properties["mail.transport.protocol"] = protocol
        properties["mail.smtp.auth"] = auth
        properties["mail.smtp.starttls.enable"] = starttls
        properties["mail.smtp.debug"] = debug

        sender.host = host
        sender.username = username
        sender.password = password
        sender.port = port
        sender.javaMailProperties = properties
        sender.defaultEncoding = encoding
        return sender
    }
}