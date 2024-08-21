package com.techeer.wishtree.domain.wish.dto.request;

import com.techeer.wishtree.domain.wish.domain.ConfirmEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class UpdateWishRequest {
    private ConfirmEnum isConfirm;
}
