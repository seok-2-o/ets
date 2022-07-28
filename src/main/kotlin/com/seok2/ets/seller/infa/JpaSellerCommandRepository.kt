package com.seok2.ets.seller.infa

import com.seok2.ets.seller.domain.model.Seller
import com.seok2.ets.seller.domain.repository.SellerRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface JpaSellerCommandRepository : SellerRepository, JpaRepository<Seller, Long>
