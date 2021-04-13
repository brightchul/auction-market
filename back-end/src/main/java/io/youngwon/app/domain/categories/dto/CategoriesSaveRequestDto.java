package io.youngwon.app.domain.categories.dto;

import io.youngwon.app.domain.categories.domain.Categories;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@NoArgsConstructor
public class CategoriesSaveRequestDto {

    private String title;
    private Long parent;

    public Categories toEntity() {
        return Categories.builder()
                .title(title)
                .parent(parent != null ? new Categories(parent) : null)
                .build();
    }

}
