package com.seok2.ets.global.config.security

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding


@ConstructorBinding
@ConfigurationProperties(prefix = "security")
data class SecurityProperties(
    val secret: String,
    val expireTime: Long
)
