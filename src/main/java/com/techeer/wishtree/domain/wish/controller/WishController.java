package com.techeer.wishtree.domain.wish.controller;

import com.techeer.wishtree.domain.wish.domain.CategoryEnum;
import com.techeer.wishtree.domain.wish.domain.ConfirmEnum;
import com.techeer.wishtree.domain.wish.domain.Wish;
import com.techeer.wishtree.domain.wish.dto.request.CreateWishRequest;
import com.techeer.wishtree.domain.wish.dto.request.UpdateWishRequest;
import com.techeer.wishtree.domain.wish.dto.response.CreateWishResponse;
import com.techeer.wishtree.domain.wish.dto.response.GetWishResponse;
import com.techeer.wishtree.domain.wish.dto.response.UpdateWishResponse;
import com.techeer.wishtree.domain.wish.service.WishService;
import com.techeer.wishtree.global.result.ResultCode;
import com.techeer.wishtree.global.result.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishes")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class WishController {
    private final WishService wishService;

    @Operation(summary = "소원 생성", description = "소원을 생성합니다.")
    @PostMapping
    public ResponseEntity<ResultResponse> createWish(@Validated @RequestBody CreateWishRequest request) {
        CreateWishResponse response = wishService.createWish(request);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ResultResponse.of(ResultCode.WISH_CREATE_SUCCESS, response));
    }

    @Operation(summary = "소원 삭제", description = "소원을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResultResponse> deleteWish(@PathVariable Long id) {
        wishService.deleteWish(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
            .body(ResultResponse.of(ResultCode.WISH_DELETE_SUCCESS));
    }

    @Operation(summary = "소원 승인/거절", description = "소원을 승인 및 거절합니다.")
    @PatchMapping("/{id}")
    public ResponseEntity<ResultResponse> updateWish(@PathVariable Long id, @RequestBody UpdateWishRequest request) {
        UpdateWishResponse response = wishService.updateWish(id, request);
        return ResponseEntity.status(HttpStatus.OK)
            .body(ResultResponse.of(ResultCode.WISH_UPDATE_SUCCESS, response));
    }

    @Operation(summary = "소원 단일 조회", description = "하나의 소원을 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<ResultResponse> getWish(@PathVariable Long id) {
        GetWishResponse response = wishService.getWish(id);
        return ResponseEntity.status(HttpStatus.OK)
            .body(ResultResponse.of(ResultCode.WISH_GET_SUCCESS, response));
    }

    @Operation(summary = "소원 목록 조회", description = "여러 소원을 조회합니다.")
    @GetMapping
    public ResponseEntity<ResultResponse> getWishes(
        @RequestParam(value = "confirm", required = false) ConfirmEnum isConfirm,
        Pageable pageable) {
        Page<GetWishResponse> response = wishService.getWishes(isConfirm, pageable);
        return ResponseEntity.status(HttpStatus.OK)
            .body(ResultResponse.of(ResultCode.WISH_GET_SUCCESS, response));
    }

    @Operation(summary = "소원 검색", description = "소원을 검색합니다.")
    @GetMapping("/search")
    public ResponseEntity<ResultResponse> searchWishes(
        @RequestParam(value = "keyword") String keyword,
        @RequestParam(value = "category") CategoryEnum category,
        Pageable pageable) {
        Page<GetWishResponse> response = wishService.searchWishes(keyword, category, pageable);
        return ResponseEntity.status(HttpStatus.OK)
            .body(ResultResponse.of(ResultCode.WISH_GET_SUCCESS, response));
    }
}
