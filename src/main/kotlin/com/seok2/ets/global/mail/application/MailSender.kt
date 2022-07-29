package com.seok2.ets.global.mail.application

interface MailSender {

    fun send(to : String, subject : String, content : String)
}