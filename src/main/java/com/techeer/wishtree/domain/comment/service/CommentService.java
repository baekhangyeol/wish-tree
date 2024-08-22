package com.techeer.wishtree.domain.comment.service;

import com.techeer.wishtree.domain.comment.domain.Comment;
import com.techeer.wishtree.domain.comment.dto.request.CreateCommentRequest;
import com.techeer.wishtree.domain.comment.dto.responnse.CreateCommentResponse;
import com.techeer.wishtree.domain.comment.repository.CommentRepository;
import com.techeer.wishtree.domain.wish.domain.Wish;
import com.techeer.wishtree.domain.wish.repository.WishRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentService {
    private final CommentRepository commentRepository;
    private final WishRepository wishRepository;

    @Transactional
    public CreateCommentResponse createComment(CreateCommentRequest request, Long wishId) {
        Wish wish = wishRepository.findById(wishId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 소원이 없습니다."));

        Comment comment = request.toEntity(wish);
        Comment savedComment = commentRepository.save(comment);

        return CreateCommentResponse.from(savedComment);
    }
}
