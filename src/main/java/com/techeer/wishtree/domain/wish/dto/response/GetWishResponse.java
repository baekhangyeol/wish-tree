package com.techeer.wishtree.domain.wish.dto.response;

import com.techeer.wishtree.domain.wish.domain.CategoryEnum;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetWishResponse {
    private Long id;
    private String title;
    private String content;
    private CategoryEnum category;
    private LocalDateTime createdAt;

    public static GetWishResponse of(Long id, String title, String content, CategoryEnum category, LocalDateTime createdAt) {
        return GetWishResponse.builder()
            .id(id)
            .title(title)
            .content(content)
            .category(category)
            .createdAt(createdAt)
            .build();
    }
}
