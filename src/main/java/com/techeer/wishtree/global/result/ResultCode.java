package com.techeer.wishtree.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {
    // Wish
    WISH_CREATE_SUCCESS("W001", "소원을 생성하였습니다."),
    WISH_DELETE_SUCCESS("W002", "소원을 삭제하였습니다."),
    WISH_UPDATE_SUCCESS("W003", "소원을 수정하였습니다."),
    WISH_GET_SUCCESS("W004", "소원을 조회하였습니다."),

    COMMENT_CREATE_SUCCESS("C001", "댓글을 생성하였습니다."),
    COMMENT_GET_SUCCESS("C002", "댓글을 조회하였습니다."),
    COMMENT_DELETE_SUCCESS("C003", "댓글을 삭제하였습니다.");

    private final String code;
    private final String message;
}
