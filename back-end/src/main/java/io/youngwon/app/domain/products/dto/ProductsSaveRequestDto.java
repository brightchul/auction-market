package io.youngwon.app.domain.products.dto;

import io.youngwon.app.domain.products.domain.Products;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    @Builder
    public ProductsSaveRequestDto(String title, Long categories, String content, Long startPrice, String startDateTime, String endDateTime) {
        this.title = title;
        this.content = content;
        this.startPrice = startPrice;
        this.categories = categories;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.categories = categories;
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
