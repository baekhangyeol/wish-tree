package com.techeer.wishtree.domain.wish.service;

import com.techeer.wishtree.domain.wish.domain.CategoryEnum;
import com.techeer.wishtree.domain.wish.domain.ConfirmEnum;
import com.techeer.wishtree.domain.wish.domain.Wish;
import com.techeer.wishtree.domain.wish.dto.request.CreateWishRequest;
import com.techeer.wishtree.domain.wish.dto.request.UpdateWishRequest;
import com.techeer.wishtree.domain.wish.dto.response.CreateWishResponse;
import com.techeer.wishtree.domain.wish.dto.response.GetWishResponse;
import com.techeer.wishtree.domain.wish.dto.response.UpdateWishResponse;
import com.techeer.wishtree.domain.wish.repository.WishRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class WishService {
    private final WishRepository wishRepository;
    private final ModelMapper modelMapper;

    public CreateWishResponse createWish(CreateWishRequest request) {
        Wish entity = request.toEntity();
        if(entity.getTitle() == null || entity.getContent() == null || entity.getCategory() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 요청입니다.");
        }
        Wish wish = wishRepository.save(entity);

        return CreateWishResponse.of(wish.getId());
    }

    @Transactional
    public void deleteWish(Long id) {
        Wish wish = wishRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 소원이 없습니다."));

        if (wish.getDeletedAt() != null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "이미 삭제된 소원입니다.");
        }

        wish.delete();
    }

    @Transactional
    public UpdateWishResponse updateWish(Long id, UpdateWishRequest request) {
        Wish wish = wishRepository.findByIdAndIsConfirm(id, ConfirmEnum.PENDING)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 소원이 없거나 이미 승인 혹은 거절된 소원입니다.."));

        if (wish.getDeletedAt() != null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "이미 삭제된 소원입니다.");
        }
        wish.update(request);

        return UpdateWishResponse.of(wish.getIsConfirm());
    }

    public GetWishResponse getWish(Long id) {
        Wish wish = wishRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 소원이 없습니다."));

        if (wish.getDeletedAt() != null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "이미 삭제된 소원입니다.");
        }

        return GetWishResponse.of(wish.getId(), wish.getTitle(), wish.getContent(), wish.getCategory(), wish.getCreatedAt());
    }

    public Page<GetWishResponse> getWishes(ConfirmEnum isConfirm, Pageable pageable) {
        Page<Wish> wishes = wishRepository.findAllByIsConfirm(isConfirm, pageable);
        return wishes.map(wish -> modelMapper.map(wish, GetWishResponse.class));
    }

    public Page<GetWishResponse> searchWishes(String keyword, CategoryEnum category, Pageable pageable) {
        Page<Wish> wishes = wishRepository.findByKeywordAndCategory(keyword, category, pageable);
        return wishes.map(wish -> modelMapper.map(wish, GetWishResponse.class));
    }
}
