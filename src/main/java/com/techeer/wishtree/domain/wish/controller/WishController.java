package com.techeer.wishtree.domain.wish.controller;

import com.techeer.wishtree.domain.wish.dto.request.CreateWishRequest;
import com.techeer.wishtree.domain.wish.dto.response.CreateWishResponse;
import com.techeer.wishtree.domain.wish.service.WishService;
import com.techeer.wishtree.global.result.ResultCode;
import com.techeer.wishtree.global.result.ResultResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wish")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class WishController {
    private final WishService wishService;

    @PostMapping
    public ResponseEntity<ResultResponse> createWish(@Validated @RequestBody CreateWishRequest request) {
        CreateWishResponse response = wishService.createWish(request);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ResultResponse.of(ResultCode.WISH_CREATE_SUCCESS, response));
    }
}
