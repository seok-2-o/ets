package com.seok2.ets.seller.ui

import com.seok2.ets.seller.application.SellerCommandService
import com.seok2.ets.seller.domain.model.Seller
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("apis/sellers")
class SellerCommandController(private val sellerCommandService: SellerCommandService) {

    @PostMapping
    fun save(@RequestBody command: CreateSellerCommand): ResponseEntity<Seller> {
        val seller = sellerCommandService.welcome(command.email, command.password, command.name)
        return ResponseEntity.created(URI.create("apis/sellers/${seller.id}"))
            .body(seller)
    }
}
