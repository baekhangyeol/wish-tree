package com.techeer.wishtree.domain.wish.service;

import com.techeer.wishtree.domain.wish.domain.Wish;
import com.techeer.wishtree.domain.wish.dto.request.CreateWishRequest;
import com.techeer.wishtree.domain.wish.dto.response.CreateWishResponse;
import com.techeer.wishtree.domain.wish.repository.WishRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class WishService {
    private final WishRepository wishRepository;

    public CreateWishResponse createWish(CreateWishRequest request) {
        Wish entity = request.toEntity();
        if(entity.getTitle() == null || entity.getContent() == null || entity.getCategory() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 요청입니다.");
        }
        Wish wish = wishRepository.save(entity);

        return CreateWishResponse.of(wish.getId());
    }
}
