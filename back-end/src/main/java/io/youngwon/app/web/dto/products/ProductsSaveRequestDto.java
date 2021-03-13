package io.youngwon.app.web.dto.products;

import io.youngwon.app.domain.products.Products;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ProductsSaveRequestDto {

    @NotBlank(message = "title must be provided")
    private String title;

    @NotBlank(message = "content must be provided")
    private String content;

    private Long startPrice;

    @NotNull(message = "categories must be provided")
    private Long categories;

    @Builder
    public ProductsSaveRequestDto(String title, String content, Long startPrice, Long categories) {
        this.title = title;
        this.content = content;
        this.startPrice = startPrice;
        this.categories = categories;
    }


    public Products toEntity(){
        return new Products(this);
    }

}
