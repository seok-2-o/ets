package com.seok2.ets.seller.domain.repository

import com.seok2.ets.seller.domain.model.Seller

interface SellerRepository {
    fun save(seller: Seller): Seller
    fun findByEmail(email: String): Seller?
}
