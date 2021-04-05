package io.youngwon.app.domain.categories.dto;

import io.youngwon.app.domain.categories.domain.Categories;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CategoriesListResponseDto {

    private Long id;
    private String title;
    private Integer numOfProduct;
    private List<CategoriesListResponseDto> children;


    public CategoriesListResponseDto(Categories entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.children = entity.getChildren().stream().map(CategoriesListResponseDto::new).collect(Collectors.toList());
        this.numOfProduct = entity.getProducts().size();
    }

}
