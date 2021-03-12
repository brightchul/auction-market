package io.youngwon.app.web.dto.products;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class ProductsSaveRequestDto {

    @NotBlank(message = "title must be provided")
    private String title;

    @NotBlank(message = "content must be provided")
    private String content;
//    private Long categories;

    @Builder
    public ProductsSaveRequestDto(String title, String content){
        this.title = title;
        this.content = content;
    }
}
