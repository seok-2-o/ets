package com.seok2.ets.seller.event

data class SellerCreatedEvent(val email: String, val name: String, val token: String)