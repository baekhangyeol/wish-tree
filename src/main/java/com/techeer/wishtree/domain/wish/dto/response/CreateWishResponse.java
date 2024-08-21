package com.techeer.wishtree.domain.wish.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateWishResponse {
    private Long id;

    public static CreateWishResponse of(Long id) {
        return CreateWishResponse.builder()
            .id(id)
            .build();
    }
}
