package io.youngwon.app.domain.products;


import io.youngwon.app.domain.BaseTimeEntity;
import io.youngwon.app.domain.categories.Categories;
import io.youngwon.app.domain.users.Users;
import io.youngwon.app.web.dto.products.ProductsDto;
import io.youngwon.app.web.dto.products.ProductsSaveRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Products extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Categories categories;

    @ManyToOne
    private Users seller;

    private String title;

    private String content;


    private Integer viewCount;

    private Long startPrice;

    private LocalDateTime startDate;

    private LocalDateTime endDateTime;


    public Products(ProductsSaveRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.startPrice = requestDto.getStartPrice();
        this.categories = new Categories(requestDto.getCategories());

    }

    public void update(String title, String content, Long startPrice){
        this.title = title;
        this.content = content;
        this.startPrice = startPrice;
    }

    public void increaseViewCount(){
        this.viewCount += 1;
    }

}

