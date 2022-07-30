package com.seok2.ets.seller.event

import com.seok2.ets.global.mail.application.MailSender
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.event.EventListener

import org.springframework.stereotype.Component

@Component
class SellerEventListener(
    private val sender: MailSender
) {

    @Value("\${host}")
    lateinit var host: String

    @EventListener
    fun handler(event: SellerCreatedEvent) {
        val link = "$host/apis/sellers/approve?email=${event.email}&token=${event.token}"
        sender.send(event.email, "[에츠] 회원 인증 메일입니다.", link)
    }
}