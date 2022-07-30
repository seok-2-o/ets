package com.seok2.ets.seller.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SellerTest {

    @Test
    fun `최초 회원 생성시 인증 요청 상태를 가진다`() {
        val seller = Seller("seok2@kakao.com", "1234", "이재석")
        assertThat(seller.isActive()).isFalse
    }

    @Test
    fun `회원을 활성상태로 변경할 수 있다`() {
        val seller = Seller("seok2@kakao.com", "1234", "이재석")
        seller.approve(seller.token)
        assertThat(seller.isActive()).isTrue
    }

}