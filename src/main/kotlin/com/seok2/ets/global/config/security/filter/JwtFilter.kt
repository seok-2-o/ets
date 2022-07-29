package com.seok2.ets.global.config.security.filter

import com.seok2.ets.global.config.security.application.JwtTokenProvider
import com.seok2.ets.global.config.security.application.SellerAuthenticationService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtFilter(
    private val service: SellerAuthenticationService,
    private val provider: JwtTokenProvider
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        request.getHeader("Authorization")?.substring("Bearer ".length)
            ?.let {
                provider.parse(it).id
            }?.let {
                service.loadUserByUsername(it)
            }?.let {
                SecurityContextHolder.getContext().authentication =
                    UsernamePasswordAuthenticationToken(it, null, it.authorities)
            }
        filterChain.doFilter(request, response)
    }
}

