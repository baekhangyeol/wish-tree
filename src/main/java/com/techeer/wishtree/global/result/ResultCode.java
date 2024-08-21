package com.techeer.wishtree.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {
    // Wish
    WISH_CREATE_SUCCESS("W001", "소원을 생성하였습니다.");

    private final String code;
    private final String message;
}
