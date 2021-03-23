package io.youngwon.app.web.dto.products;

import io.youngwon.app.domain.products.Products;
import io.youngwon.app.web.dto.files.FilesListResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ProductsListResponseDto {

    private Long id;
    private String title;
    private String content;
    private Long startPrice;
    private LocalDateTime endDateTime;
    private List<FilesListResponseDto> images;

    public ProductsListResponseDto(Products entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.startPrice = entity.getStartPrice();
        this.endDateTime = entity.getEndDateTime();


        this.images = entity.getFiles()
                .stream()
                .map(FilesListResponseDto::new)
                .collect(Collectors.toList());

    }

}
