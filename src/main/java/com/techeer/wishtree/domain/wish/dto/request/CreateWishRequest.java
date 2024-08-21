package com.techeer.wishtree.domain.wish.dto.request;

import com.techeer.wishtree.domain.wish.domain.CategoryEnum;
import com.techeer.wishtree.domain.wish.domain.Wish;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class CreateWishRequest {
    private String title;
    private String content;
    private CategoryEnum category;

    public Wish toEntity() {
        return Wish.builder()
            .title(title)
            .content(content)
            .category(category)
            .build();
    }
}
