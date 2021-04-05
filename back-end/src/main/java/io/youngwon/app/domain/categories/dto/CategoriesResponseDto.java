package io.youngwon.app.domain.categories.dto;

import io.youngwon.app.domain.categories.domain.Categories;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoriesResponseDto {

    private Long id;
    private String title;
    private CategoriesResponseDto parent;

    public CategoriesResponseDto(Categories categories){
        this.id = categories.getId();
        this.title = categories.getTitle();
        if(categories.getParent() != null) {
            parent = new CategoriesResponseDto(categories.getParent());
        }
    }



}
