package io.youngwon.app.domain.comments.dto;

import io.youngwon.app.domain.comments.domain.Comments;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentsListResponseDto {

    private Long id;
    private String content;
    private LocalDateTime createdAt;


    public CommentsListResponseDto(Comments comments){
        this.id = comments.getId();
        this.content = comments.getContent();
        this.createdAt = comments.getCreatedAt();
    }
}
