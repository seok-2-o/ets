package com.seok2.ets.seller.application

import com.seok2.ets.global.event.application.EventPublisher
import com.seok2.ets.seller.domain.model.Seller
import com.seok2.ets.seller.domain.repository.SellerRepository
import com.seok2.ets.seller.event.SellerCreatedEvent
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class SellerCommandService(
    private val sellerRepository: SellerRepository,
    private val passwordEncoder: PasswordEncoder,
    private val eventPublisher: EventPublisher
) {

    @Transactional
    fun welcome(email: String, password: String, name: String): Seller {
        val seller = sellerRepository.save(Seller(email, passwordEncoder.encode(password), name))
        eventPublisher.publish(SellerCreatedEvent(seller.email, seller.name, seller.token))
        return seller
    }
}
