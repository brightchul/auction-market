package io.youngwon.app.web.dto.categories;

import io.youngwon.app.domain.categories.Categories;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CategoriesListDto {

    private Long id;
    private String title;
    private List<CategoriesListDto> children;


    public CategoriesListDto(Categories entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.children = entity.getChildren().stream().map(CategoriesListDto::new).collect(Collectors.toList());
    }

}
