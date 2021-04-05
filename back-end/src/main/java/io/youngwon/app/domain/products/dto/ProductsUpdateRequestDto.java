package io.youngwon.app.domain.products.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class ProductsUpdateRequestDto {

    @NotBlank(message = "title must be provided")
    private String title;

    @NotBlank(message = "content must be provided")
    private String content;

    private Long startPrice;

    private String startDate;

    private String endDateTime;


}
