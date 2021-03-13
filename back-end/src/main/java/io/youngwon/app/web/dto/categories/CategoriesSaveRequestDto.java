package io.youngwon.app.web.dto.categories;

import io.youngwon.app.domain.categories.Categories;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoriesSaveRequestDto {

    private String title;
    private Long parent;

    @Builder
    public CategoriesSaveRequestDto(String title, Long parent) {
        this.title = title;
        this.parent = parent;
    }

    public Categories toEntity() {

        return Categories.builder()
                .title(title)
                .parent(parent != null ? new Categories(parent) : null)
                .build();
    }

}
