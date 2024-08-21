package com.techeer.wishtree.domain.wish.dto.response;

import com.techeer.wishtree.domain.wish.domain.ConfirmEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWishResponse {
    private ConfirmEnum isConfirmed;

    public static UpdateWishResponse of(ConfirmEnum isConfirmed) {
        return UpdateWishResponse.builder()
            .isConfirmed(isConfirmed)
            .build();
    }
}
