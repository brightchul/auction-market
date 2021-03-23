package io.youngwon.app.web.dto.products;


import io.youngwon.app.domain.products.Products;
import io.youngwon.app.web.dto.auctions.AuctionsListResponseDto;
import io.youngwon.app.web.dto.comments.CommentsListResponseDto;
import io.youngwon.app.web.dto.files.FilesListResponseDto;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProductsResponseDto {

    private Long id;
    private String title;
    private String content;
    private Long startPrice;

    private List<AuctionsListResponseDto> auctions;
    private List<CommentsListResponseDto> comments;

    private List<FilesListResponseDto> images;


    // 카테고리



    public ProductsResponseDto(Products entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.startPrice = entity.getStartPrice();
        if(entity.getFiles() != null) {
            this.images = entity.getFiles().stream().map(FilesListResponseDto::new).collect(Collectors.toList());
        }
        this.comments = entity.getComments().stream().map(CommentsListResponseDto::new).collect(Collectors.toList());
        this.auctions = entity.getAuctions().stream().map(AuctionsListResponseDto::new).collect(Collectors.toList());


    }

}
