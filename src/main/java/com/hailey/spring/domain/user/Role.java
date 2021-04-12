package com.hailey.spring.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
스프링 시큐리티에서 권한 코드에 항상 ROLE_이 앞에 있어야 한다.
 */
@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;

}