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

@Getter
@NoArgsConstructor
public class ProductsSaveRequestDto {

    @NotBlank(message = "title must be provided")
    private String title;

    @NotBlank(message = "content must be provided")
    private String content;

    private Images[] images;

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

    public Products toEntity() {
        return new Products(this);
    }

    public void saveImages() {
        if (images != null) {

            Path path = Paths.get("upload");
            for (Images image : images) {


                try {
                    byte[] bytes = java.util.Base64.getDecoder().decode(image.dataURL.substring(image.dataURL.indexOf(",") + 1));

                    Files.write(Paths.get("uploads", "output.png") , bytes);

                }catch(Exception e){
                    e.printStackTrace();
                }
//                Files.copy(new ByteArrayInputStream(bytes), path.resolve("123.png"));
//                return path.resolve("123.png").toAbsolutePath().toString();

            }
        }
    }

    @ToString
    @Getter
    @NoArgsConstructor
    public static class Images {
        private String dataURL;

    }
}
