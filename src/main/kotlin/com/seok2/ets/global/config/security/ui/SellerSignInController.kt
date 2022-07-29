package com.seok2.ets.global.config.security.ui

import com.seok2.ets.global.config.security.application.SellerSignInService
import com.seok2.ets.global.config.security.ui.dto.SignInSellerCommand
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class SellerSignInController(private val sellerSignInService: SellerSignInService) {

    @PostMapping("apis/sellers/sign-in")
    fun signIn(@RequestBody command: SignInSellerCommand): ResponseEntity<Void> {
        val token = sellerSignInService.signIn(command.email, command.password)
        return ResponseEntity
            .ok()
            .header("Authorization", "Bearer $token")
            .build()
    }
}
