package com.seok2.ets.global.config.security.application

import com.seok2.ets.global.config.security.exception.SignInFailedException
import com.seok2.ets.seller.domain.repository.SellerRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class SellerSignInService(
    private val sellerRepository: SellerRepository,
    private val jwtProvider: JwtTokenProvider,
    private val passwordEncoder: PasswordEncoder
) {

    fun signIn(email: String, password: String): String {
        authenticate(email, password)
        return jwtProvider.create(email)
    }

    private fun authenticate(email: String, password: String) {
        sellerRepository.findByEmail(email)
            ?.takeIf { passwordEncoder.matches(password, it.password) }
            ?: throw SignInFailedException()
    }
}

