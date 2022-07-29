package com.seok2.ets.global.config.security.application

import com.seok2.ets.global.config.security.domain.model.SellerDetails
import com.seok2.ets.seller.domain.repository.SellerRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class SellerAuthenticationService(private val sellerRepository: SellerRepository) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        return sellerRepository.findByEmail(email)?.let {
            SellerDetails(it)
        } ?: throw UsernameNotFoundException("존재하지 않는 이메일입니다. : $email")
    }

}
