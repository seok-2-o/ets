package com.seok2.ets.global.config.security.ui

import com.seok2.ets.global.config.security.application.SellerSignInService
import com.seok2.ets.global.config.security.ui.dto.SignInSellerCommand
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class SellerSignInController(private val sellerSignInService: SellerSignInService) {

    @PostMapping("apis/sellers/sign-in")
    fun signIn(@RequestBody command: SignInSellerCommand): ResponseEntity<Void> {
        val token = sellerSignInService.signIn(command.email, command.password)
        return ResponseEntity
            .ok()
            .header("Authorization", "Bearer $token")
            .build()
    }

    @GetMapping("apis/sellers/approve")
    fun approve(@RequestParam email: String, @RequestParam token: String): ResponseEntity<Void> {
        sellerSignInService.approve(email, token)
        return ResponseEntity
            .noContent().build()
    }
}
