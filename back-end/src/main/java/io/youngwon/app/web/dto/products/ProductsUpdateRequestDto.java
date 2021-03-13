package io.youngwon.app.web.dto.products;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Getter
@NoArgsConstructor
public class ProductsUpdateRequestDto {

    @NotBlank(message = "title must be provided")
    private String title;

    @NotBlank(message = "content must be provided")
    private String content;

    private Long startPrice;


}
