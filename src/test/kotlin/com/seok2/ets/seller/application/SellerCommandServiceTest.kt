package com.seok2.ets.seller.application

import com.seok2.ets.helper.SpringBootTestWithSandboxMail
import com.seok2.ets.seller.event.SellerCreatedEvent
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.event.ApplicationEvents
import org.springframework.test.context.event.RecordApplicationEvents


@RecordApplicationEvents
internal class SellerCommandServiceTest(
    @Autowired
    private val service: SellerCommandService,
) : SpringBootTestWithSandboxMail() {


    @Test
    fun `판매자 가입`(@Autowired events: ApplicationEvents) {
        service.welcome("seok5@kakao.com", "1234", "이재석")

        var count = events.stream(SellerCreatedEvent::class.java)
            .filter { it.name == "이재석" }
            .count()

        assertThat(count).isEqualTo(1L)

    }
}
