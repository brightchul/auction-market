package io.youngwon.app.web.dto.products;


import io.youngwon.app.Constant;
import io.youngwon.app.domain.categories.Categories;
import io.youngwon.app.domain.products.Products;
import io.youngwon.app.web.dto.auctions.AuctionsListResponseDto;
import io.youngwon.app.web.dto.categories.CategoriesTitleResponseDto;
import io.youngwon.app.web.dto.comments.CommentsListResponseDto;
import io.youngwon.app.web.dto.files.FilesListResponseDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProductsResponseDto {

    private Long id;
    private String title;
    private String content;
    private Long startPrice;

    private List<AuctionsListResponseDto> auctions;
//    private List<CommentsListResponseDto> comments;


    private List<FilesListResponseDto> images;

    // 좋아요
    private Integer numOfLike;
    private Boolean isLike;

    // 대시보드
    private Integer numOfAuctions;
    private Integer numOfParticipant;



    // 카테고리
    private List<CategoriesTitleResponseDto> categories = new ArrayList<CategoriesTitleResponseDto>();



    public ProductsResponseDto(Products entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.startPrice = entity.getStartPrice();
        if(entity.getFiles() != null) {
            this.images = entity.getFiles().stream().map(FilesListResponseDto::new).collect(Collectors.toList());
        }
//        this.comments = entity.getComments().stream().map(CommentsListResponseDto::new).collect(Collectors.toList());
        this.auctions = entity.getAuctions().stream().map(AuctionsListResponseDto::new).collect(Collectors.toList());

        this.numOfLike = entity.getLikes().size();

        // 내가 지금 라이크를 하고 있는가?
        this.isLike = entity.getLikes()
                .stream()
                .filter(like -> like.getUsers().getId() == Constant.USER_ID)
                .collect(Collectors.toList()).size() > 0;

        this.numOfAuctions = entity.getAuctions().size();
        this.numOfParticipant = entity.getAuctions()
                .stream()
                .map(auction->auction.getParticipants().getId())
                .collect(Collectors.toSet()).size();

        Categories temp = entity.getCategories();
        while(temp != null){
            this.categories.add(0, new CategoriesTitleResponseDto(temp));
            temp = temp.getParent();
        }
    }
}
