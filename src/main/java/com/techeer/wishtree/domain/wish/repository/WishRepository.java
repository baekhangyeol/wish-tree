package com.techeer.wishtree.domain.wish.repository;

import com.techeer.wishtree.domain.wish.domain.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {

}
