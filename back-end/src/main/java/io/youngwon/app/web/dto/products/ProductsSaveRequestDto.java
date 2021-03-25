package io.youngwon.app.web.dto.products;

import com.google.common.io.ByteSink;
import io.youngwon.app.domain.products.Products;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ProductsSaveRequestDto {

    @NotBlank(message = "title must be provided")
    private String title;

    @NotBlank(message = "content must be provided")
    private String content;

    private Images[] images;

    private Long startPrice;

    private String startDateTime;

    private String endDateTime;

    @NotNull(message = "categories must be provided")
    private Long categories;

//    @Builder
//    public ProductsSaveRequestDto(String title, String content, Long startPrice, Long categories) {
//        this.title = title;
//        this.content = content;
//        this.startPrice = startPrice;
//        this.categories = categories;
//    }

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
