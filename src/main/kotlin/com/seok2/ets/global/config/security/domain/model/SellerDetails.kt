package com.seok2.ets.global.config.security.domain.model

import com.seok2.ets.seller.domain.model.Seller
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

class SellerDetails(private val seller: Seller) : UserDetails {

    var enabled: Boolean = true

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = AuthorityUtils.createAuthorityList()

    override fun getPassword(): String = seller.password

    override fun getUsername(): String = seller.email

    override fun isAccountNonExpired(): Boolean = enabled

    override fun isAccountNonLocked(): Boolean = enabled

    override fun isCredentialsNonExpired(): Boolean = enabled

    override fun isEnabled(): Boolean = enabled
}
