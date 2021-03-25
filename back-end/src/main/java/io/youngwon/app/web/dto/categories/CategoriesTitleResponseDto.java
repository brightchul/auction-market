package io.youngwon.app.web.dto.categories;


import io.youngwon.app.domain.categories.Categories;
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
