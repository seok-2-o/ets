package com.seok2.ets.seller.domain.model

import com.seok2.ets.global.config.security.exception.InvalidAuthenticationTokenException
import java.util.*
import javax.persistence.*

@Entity
class Seller(
    @Column(nullable = false, unique = true)
    var email: String,
    @Column(nullable = false)
    var password: String,
    @Column(nullable = false)
    var name: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    val token: String = UUID.randomUUID().toString()
}
