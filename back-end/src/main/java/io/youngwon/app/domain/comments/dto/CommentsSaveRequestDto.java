package io.youngwon.app.domain.comments.dto;

import io.youngwon.app.domain.comments.domain.Comments;
import io.youngwon.app.domain.products.domain.Products;
import lombok.Getter;

@Getter
public class CommentsSaveRequestDto {

    private String content;

    public Comments toEntity(Products products){
        return new Comments(content, products);
    }
}
