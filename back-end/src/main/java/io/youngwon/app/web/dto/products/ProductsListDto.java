package io.youngwon.app.web.dto.products;

import io.youngwon.app.domain.products.Products;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ProductsListDto {

    private Long id;
    private String title;
    private String content;
    private Long startPrice;
    private LocalDateTime endDateTime;

    public ProductsListDto(Products entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.startPrice = entity.getStartPrice();
        this.endDateTime = entity.getEndDateTime();
    }

}
