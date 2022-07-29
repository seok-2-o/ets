package com.seok2.ets.global.config.security.application


import com.seok2.ets.global.config.security.SecurityProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*


@Component
class JwtTokenProvider(private val properties: SecurityProperties) {

    private val key: Key = Keys.hmacShaKeyFor(properties.secret.toByteArray(java.nio.charset.StandardCharsets.UTF_8))

    fun create(email: String): String = Jwts.builder()
        .setSubject(email)
        .setExpiration(Date(System.currentTimeMillis() + properties.expireTime))
        .signWith(key, SignatureAlgorithm.HS256)
        .compact()

    fun validate(token: String): Boolean {
        try {
            parse(token)
        } catch (e: ExpiredJwtException) {
            return false
        }
        return true
    }

    fun parse(token: String): Claims = Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .body
}
