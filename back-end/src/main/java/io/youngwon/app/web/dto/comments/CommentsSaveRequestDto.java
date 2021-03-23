package io.youngwon.app.web.dto.comments;

import io.youngwon.app.domain.comments.Comments;
import io.youngwon.app.domain.products.Products;
import lombok.Getter;
import lombok.ToString;

@Getter
public class CommentsSaveRequestDto {

    private String content;

    public Comments toEntity(Products products){
        return new Comments(content, products);
    }
}
