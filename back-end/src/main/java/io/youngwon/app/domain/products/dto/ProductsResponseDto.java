package io.youngwon.app.domain.products.dto;


import io.youngwon.app.Constant;
import io.youngwon.app.domain.categories.domain.Categories;
import io.youngwon.app.domain.products.domain.Products;
import io.youngwon.app.domain.auctions.dto.AuctionsListResponseDto;
import io.youngwon.app.domain.categories.dto.CategoriesTitleResponseDto;
import io.youngwon.app.domain.files.dto.FilesListResponseDto;
import io.youngwon.app.domain.products.domain.State;
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
    private String startDateTime;
    private String endDateTime;
    private Integer viewCount;
    private List<AuctionsListResponseDto> auctions;
//    private List<CommentsListResponseDto> comments;
    private State state;

    private List<FilesListResponseDto> images;

    // 좋아요
    private Integer numOfLike;
    private Boolean isLike;

    // 대시보드
    private Integer numOfAuctions;
    private Integer numOfParticipant;

    private Long price;


    // 카테고리
    private List<CategoriesTitleResponseDto> categories = new ArrayList<CategoriesTitleResponseDto>();



    public ProductsResponseDto(Products entity, Long userId){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.startPrice = entity.getStartPrice();
        this.startDateTime = entity.getStartDateTime().format(Constant.FORMATTER);
        this.endDateTime = entity.getEndDateTime().format(Constant.FORMATTER);

        if(entity.getFiles() != null) {
            this.images = entity.getFiles().stream().map(FilesListResponseDto::new).collect(Collectors.toList());
        }
//        this.comments = entity.getComments().stream().map(CommentsListResponseDto::new).collect(Collectors.toList());
        this.auctions = entity.getAuctions().stream().map(AuctionsListResponseDto::new).collect(Collectors.toList());

        this.numOfLike = entity.getLikes().size();
        this.viewCount = entity.getViewCount();
        this.state = entity.getState();

        // 내가 지금 라이크를 하고 있는가?
        this.isLike = entity.getLikes()
                .stream()
                .filter(like -> like.getUsers().getId() == userId)
                .collect(Collectors.toList()).size() > 0;

        this.numOfAuctions = entity.getAuctions().size();
        this.numOfParticipant = entity.getAuctions()
                .stream()
                .map(auction->auction.getParticipants().getId())
                .collect(Collectors.toSet()).size();

        if(entity.getAuctions().size() > 0) {
            this.price = entity.getAuctions().get(0).getPrice();
        }

        Categories temp = entity.getCategories();
        while(temp != null){
            this.categories.add(0, new CategoriesTitleResponseDto(temp));
            temp = temp.getParent();
        }
    }
}
