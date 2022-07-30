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
    var name: String,
    @Enumerated(EnumType.STRING)
    var status: Status = Status.AUTHENTICATION_REQUIRED
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    val token: String = UUID.randomUUID().toString()


    fun approve(token: String) {
        if (this.token != token) {
            throw InvalidAuthenticationTokenException()
        }
        this.status = Status.ACTIVE
    }

    fun isActive(): Boolean = this.status == Status.ACTIVE
}
