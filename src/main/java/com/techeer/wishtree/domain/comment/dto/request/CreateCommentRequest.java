package com.techeer.wishtree.domain.comment.dto.request;

import com.techeer.wishtree.domain.comment.domain.Comment;
import com.techeer.wishtree.domain.wish.domain.Wish;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateCommentRequest {
    private String content;

    public Comment toEntity(Wish wish) {
        return new Comment(content, wish);
    }
}
