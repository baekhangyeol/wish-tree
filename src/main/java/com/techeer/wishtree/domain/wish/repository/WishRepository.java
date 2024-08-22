package com.techeer.wishtree.domain.wish.repository;

import com.techeer.wishtree.domain.wish.domain.CategoryEnum;
import com.techeer.wishtree.domain.wish.domain.ConfirmEnum;
import com.techeer.wishtree.domain.wish.domain.Wish;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {
    @Query("SELECT w FROM Wish w WHERE w.id = :id AND w.isConfirm = :isConfirm AND w.deletedAt IS NULL")
    Optional<Wish> findByIdAndIsConfirm(@Param("id") Long id, @Param("isConfirm") ConfirmEnum isConfirm);

    @Query("SELECT w FROM Wish w WHERE w.deletedAt IS NULL AND w.isConfirm = :isConfirm ORDER BY w.createdAt DESC")
    Page<Wish> findAllByDeletedAtFalseAndIsConfirm(@Param("isConfirm") ConfirmEnum isConfirm, Pageable pageable);

    @Query("SELECT w FROM Wish w WHERE (w.title LIKE %:keyword% OR w.content LIKE %:keyword%) AND w.category = :category AND w.deletedAt IS NULL ORDER BY w.createdAt DESC")
    Page<Wish> findByKeywordContainingAndDeletedAt(@Param("keyword") String keyword, @Param("category") CategoryEnum category, Pageable pageable);
}
