package io.youngwon.app.domain.categories.dto;


import io.youngwon.app.domain.categories.domain.Categories;
import lombok.Getter;

@Getter
public class CategoriesTitleResponseDto {

    private Long id;
    private String title;

    public CategoriesTitleResponseDto(Categories categories){
        this.id = categories.getId();
        this.title = categories.getTitle();;
    }
}
