package com.techeer.wishtree.domain.comment.dto.responnse;

import com.techeer.wishtree.domain.comment.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCommentResponse {
    private Long id;
    private String content;
    private Long wishId;

    public static GetCommentResponse from(Comment comment) {
        return GetCommentResponse.builder()
            .id(comment.getId())
            .content(comment.getContent())
            .wishId(comment.getWish().getId())
            .build();
    }
}
