package io.youngwon.app.web.dto.products;


import io.youngwon.app.domain.products.Products;
import lombok.Getter;

@Getter
public class ProductsDto {

    private Long id;
    private String title;
    private String content;
    private Long startPrice;


    // 카테고리



    public ProductsDto(Products entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.startPrice = entity.getStartPrice();
    }

}
