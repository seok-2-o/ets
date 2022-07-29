package com.seok2.ets.global.config.security.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "존재하지 않는 아이디 혹은 비밀번호가 일치하지 않습니다.")
class SignInFailedException : RuntimeException()
