package com.techeer.wishtree.domain.wish.repository;

import com.techeer.wishtree.domain.wish.domain.ConfirmEnum;
import com.techeer.wishtree.domain.wish.domain.Wish;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {
    @Query("SELECT w FROM Wish w WHERE w.id = :id AND w.isConfirm = :isConfirm")
    Optional<Wish> findByIdAndIsConfirm(Long id, ConfirmEnum isConfirm);
}
