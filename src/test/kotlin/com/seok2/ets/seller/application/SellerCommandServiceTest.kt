package com.seok2.ets.seller.application

import com.icegreen.greenmail.configuration.GreenMailConfiguration
import com.icegreen.greenmail.junit5.GreenMailExtension
import com.icegreen.greenmail.util.ServerSetupTest
import com.seok2.ets.seller.event.SellerCreatedEvent
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.event.ApplicationEvents
import org.springframework.test.context.event.RecordApplicationEvents

@TestPropertySource(locations = ["/application.yml"])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RecordApplicationEvents
internal class SellerCommandServiceTest(
    @Autowired
    private val service: SellerCommandService,
) {

    companion object {
        @JvmField
        @RegisterExtension
        val smtp: GreenMailExtension = GreenMailExtension(ServerSetupTest.SMTP)
            .withConfiguration(GreenMailConfiguration.aConfig().withUser("seok2", "welcome"))
            .withPerMethodLifecycle(true)
    }

    @Test
    fun `판매자 가입`(@Autowired events: ApplicationEvents) {
        service.welcome("seok5@kakao.com", "1234", "이재석")

        var count = events.stream(SellerCreatedEvent::class.java)
            .filter { it.name == "이재석" }
            .count()

        assertThat(count).isEqualTo(1L)

    }
}
