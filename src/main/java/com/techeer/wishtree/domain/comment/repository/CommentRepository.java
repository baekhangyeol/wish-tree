package com.techeer.wishtree.domain.comment.repository;

import com.techeer.wishtree.domain.comment.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c WHERE c.wish.id = :wishId AND c.deletedAt IS NULL")
    Page<Comment> findByWishIdAndDeletedAtIsNull(Long wishId, Pageable pageable);
}
