package io.youngwon.app.web.dto.products;

import io.youngwon.app.Constant;
import io.youngwon.app.domain.categories.Categories;
import io.youngwon.app.domain.products.Products;
import io.youngwon.app.web.dto.categories.CategoriesResponseDto;
import io.youngwon.app.web.dto.categories.CategoriesTitleResponseDto;
import io.youngwon.app.web.dto.files.FilesListResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ProductsListResponseDto {

    private Long id;
    private String title;
    private String content;
    private Long startPrice;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private List<FilesListResponseDto> images;

    private Integer numOfLike;
    private Boolean isLike;


    private List<CategoriesTitleResponseDto> categories = new ArrayList<CategoriesTitleResponseDto>();


    // 경매 진행중 여부

    // 경매 진행중일시 경과 시간

    // 현재 가격


    public ProductsListResponseDto(Products entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.startPrice = entity.getStartPrice();
        this.startDateTime = entity.getStartDateTime();
        this.endDateTime = entity.getEndDateTime();

//        this.categories = new CategoriesResponseDto(entity.getCategories());

        // 가장 하위
        Categories temp = entity.getCategories();
        while(temp != null){
            this.categories.add(0, new CategoriesTitleResponseDto(temp));
            temp = temp.getParent();
        }


        this.images = entity.getFiles()
                .stream()
                .map(FilesListResponseDto::new)
                .collect(Collectors.toList());

        this.numOfLike = entity.getLikes().size();

        // 내가 지금 라이크를 하고 있는가?
        this.isLike = entity.getLikes()
                .stream()
                .filter(like -> like.getUsers().getId() == Constant.USER_ID)
                .collect(Collectors.toList()).size() > 0;

    }



}
