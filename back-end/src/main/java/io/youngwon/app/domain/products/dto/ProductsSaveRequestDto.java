package io.youngwon.app.domain.products.dto;

import io.youngwon.app.domain.products.domain.Products;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ProductsSaveRequestDto {

    @NotBlank(message = "title must be provided")
    private String title;

    @NotBlank(message = "content must be provided")
    private String content;

    private Images[] images;

    @NotNull(message = "startPrice must be provided")
    private Long startPrice;

    @NotBlank(message = "startDateTime must be provided")
    private String startDateTime;

    @NotBlank(message = "endDateTime must be provided")
    private String endDateTime;

    @NotNull(message = "categories must be provided")
    private Long categories;

    @Setter
    private Long seller;

    @Builder
    public ProductsSaveRequestDto(String title, Long categories, String content, Long startPrice, String startDateTime, String endDateTime, Long seller) {
        this.title = title;
        this.content = content;
        this.startPrice = startPrice;
        this.categories = categories;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.categories = categories;
        this.seller = seller;
    }

    public Products toEntity() {
        return new Products(this);
    }

    @ToString
    @Getter
    @NoArgsConstructor
    public static class Images {
        private String dataURL;
    }
}
