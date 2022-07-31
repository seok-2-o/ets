package com.seok2.ets.helper

import com.icegreen.greenmail.configuration.GreenMailConfiguration
import com.icegreen.greenmail.junit5.GreenMailExtension
import com.icegreen.greenmail.util.ServerSetupTest
import org.junit.jupiter.api.extension.RegisterExtension

class AcceptanceTestWithSandboxMail : AcceptanceTest() {

    companion object {
        @JvmField
        @RegisterExtension
        val smtp: GreenMailExtension = GreenMailExtension(ServerSetupTest.SMTP)
            .withConfiguration(GreenMailConfiguration.aConfig().withUser("seok2", "welcome"))
            .withPerMethodLifecycle(true)
    }
}