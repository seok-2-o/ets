package com.seok2.ets.global.event.infra

import com.seok2.ets.global.event.application.EventPublisher
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class SpringApplicationEventPublisher(
    private val publisher: ApplicationEventPublisher
) : EventPublisher {
    override fun publish(any: Any) {
        publisher.publishEvent(any)
    }
}