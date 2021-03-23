package io.youngwon.app.domain.files;

import io.youngwon.app.domain.BaseTimeEntity;
import io.youngwon.app.domain.products.Products;
import io.youngwon.app.web.dto.products.ProductsSaveRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@NoArgsConstructor
@Entity
public class Files extends BaseTimeEntity {

    private static final String BASE64_HEADER_EXP = "^data:[^/]+/([^;]+);base64,";
    private static final Pattern PATTERN_BASE64_HEADER = Pattern.compile(BASE64_HEADER_EXP);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String path;

    private String filename;

    @ManyToOne
    @JoinColumn(name = "products")
    private Products products;

    public Files(Products products, ProductsSaveRequestDto.Images image) {

        // 파일 업로드
        this.path = "uploads";
        this.filename = UUID.randomUUID().toString();
        this.products = products;




        byte[] bytes = Base64
                .getDecoder()
                .decode(image.getDataURL().substring(image.getDataURL().indexOf(",") + 1));
        try {

//            Matcher base64HeaderMatcher = PATTERN_BASE64_HEADER.matcher(image.getDataURL());
//
//            String extension = base64HeaderMatcher.group(1);

            java.nio.file.Files.write(Paths.get(path, filename), bytes);
        }catch(Exception e){
            e.printStackTrace();
        }



    }
}
