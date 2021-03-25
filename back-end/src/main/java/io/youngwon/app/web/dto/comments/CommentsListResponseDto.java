package io.youngwon.app.web.dto.comments;

import io.youngwon.app.domain.comments.Comments;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
