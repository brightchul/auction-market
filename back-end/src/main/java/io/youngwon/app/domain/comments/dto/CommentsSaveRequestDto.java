package io.youngwon.app.domain.comments.dto;

import io.youngwon.app.domain.comments.domain.Comments;
import io.youngwon.app.domain.products.domain.Product;
import lombok.Getter;

@Getter
public class CommentsSaveRequestDto {

    private String content;

    public Comments toEntity(Product products){
        return new Comments(content, products);
    }
}
