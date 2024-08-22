package com.techeer.wishtree.domain.comment.controller;

import com.techeer.wishtree.domain.comment.dto.request.CreateCommentRequest;
import com.techeer.wishtree.domain.comment.dto.responnse.CreateCommentResponse;
import com.techeer.wishtree.domain.comment.service.CommentService;
import com.techeer.wishtree.global.result.ResultCode;
import com.techeer.wishtree.global.result.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentController {
    private final CommentService commentService;

    @Operation(summary = "댓글 생성", description = "댓글을 생성합니다.")
    @PostMapping("/{wishId}")
    public ResponseEntity<ResultResponse> createComment(@Validated @RequestBody CreateCommentRequest request, @PathVariable Long wishId) {
        CreateCommentResponse response = commentService.createComment(request, wishId);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ResultResponse.of(ResultCode.COMMENT_CREATE_SUCCESS, response));
    }

}
