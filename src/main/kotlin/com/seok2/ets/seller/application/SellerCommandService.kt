package com.seok2.ets.seller.application

import com.seok2.ets.seller.domain.model.Seller
import com.seok2.ets.seller.domain.repository.SellerRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class SellerCommandService(
    private val sellerRepository: SellerRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun welcome(email: String, password: String, name: String): Seller {
        val seller = Seller(email, passwordEncoder.encode(password), name)
        return sellerRepository.save(seller)
    }


}
