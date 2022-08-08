package com.seok2.ets.seller.event

import com.seok2.ets.global.mail.application.MailSender
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.event.EventListener

import org.springframework.stereotype.Component

@Component
class SellerEventListener(
    private val sender: MailSender
) {

    @EventListener
    fun handler(event: SellerCreatedEvent) {
        sender.send(event.email, "[에츠] 가족이 되신 것을 환영합니다.", "")
    }
}