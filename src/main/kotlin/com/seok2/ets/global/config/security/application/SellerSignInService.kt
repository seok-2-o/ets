package com.seok2.ets.global.config.security.application

import com.seok2.ets.global.config.security.exception.AuthenticationRequiredException
import com.seok2.ets.global.config.security.exception.SignInFailedException
import com.seok2.ets.seller.domain.repository.SellerRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Transactional
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

    @Transactional(readOnly = false)
    fun approve(email: String, token: String) {
        var seller = sellerRepository.findByEmail(email)
            ?: throw UsernameNotFoundException("존재하지 않는 이메일입니다. : $email")
        seller.approve(token)
        sellerRepository.save(seller)
    }

    private fun authenticate(email: String, password: String) {
        val seller = sellerRepository.findByEmail(email)
            ?.takeIf { passwordEncoder.matches(password, it.password) }
            ?: throw SignInFailedException()
        if (!seller.isActive()) {
            throw AuthenticationRequiredException()
        }
    }
}

