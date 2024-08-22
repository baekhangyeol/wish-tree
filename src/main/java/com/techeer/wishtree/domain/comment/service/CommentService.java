package com.techeer.wishtree.domain.comment.service;

import com.techeer.wishtree.domain.comment.domain.Comment;
import com.techeer.wishtree.domain.comment.dto.request.CreateCommentRequest;
import com.techeer.wishtree.domain.comment.dto.responnse.CreateCommentResponse;
import com.techeer.wishtree.domain.comment.dto.responnse.GetCommentResponse;
import com.techeer.wishtree.domain.comment.repository.CommentRepository;
import com.techeer.wishtree.domain.wish.domain.Wish;
import com.techeer.wishtree.domain.wish.repository.WishRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<GetCommentResponse> getComments(Long wishId, Pageable pageable) {
        Wish wish = wishRepository.findByIdAndDeletedAtIsNull(wishId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 소원이 없습니다."));
        Page<Comment> comments = commentRepository.findByWishIdAndDeletedAtIsNull(wish.getId(), pageable);
        return comments.map(GetCommentResponse::from);
    }

    @Transactional
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 댓글이 없습니다."));

        if (comment.getDeletedAt() != null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "이미 삭제된 댓글입니다.");
        }

        comment.delete();
    }
}
