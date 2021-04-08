package io.youngwon.app.domain.products.dto;

import io.youngwon.app.domain.products.domain.Products;
import io.youngwon.app.domain.products.domain.State;
import lombok.Getter;

@Getter
public class ProductsListStateResponseDto {
    private Long id;
    private State state;

    public ProductsListStateResponseDto(Products products){
        this.id = products.getId();
        this.state = products.getState();
    }

}
