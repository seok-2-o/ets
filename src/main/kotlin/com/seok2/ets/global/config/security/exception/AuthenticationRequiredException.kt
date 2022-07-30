package com.seok2.ets.global.config.security.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "이메일 인증이 필요합니다.")
class AuthenticationRequiredException : RuntimeException()
