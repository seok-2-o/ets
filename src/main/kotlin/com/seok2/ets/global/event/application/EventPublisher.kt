package com.seok2.ets.global.event.application

interface EventPublisher {
    fun publish(any: Any)
}