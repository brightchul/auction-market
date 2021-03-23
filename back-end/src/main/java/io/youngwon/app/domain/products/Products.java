package io.youngwon.app.domain.products;


import io.youngwon.app.domain.BaseTimeEntity;
import io.youngwon.app.domain.auctions.Auctions;
import io.youngwon.app.domain.categories.Categories;
import io.youngwon.app.domain.comments.Comments;
import io.youngwon.app.domain.files.Files;
import io.youngwon.app.domain.users.Users;
import io.youngwon.app.web.dto.products.ProductsSaveRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
    private List<Files> files = new ArrayList<Files>();

    @OrderBy("createdAt desc")
    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Auctions> auctions = new ArrayList<Auctions>();

    @OrderBy("createdAt desc")
    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comments> comments = new ArrayList<Comments>();;


    public Products(ProductsSaveRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.startPrice = requestDto.getStartPrice();
        this.categories = new Categories(requestDto.getCategories());


        this.files.addAll(Arrays.stream(requestDto.getImages())
                .map(n -> new Files(this, n))
                .collect(Collectors.toList()));

    }


    public void update(String title, String content, Long startPrice){
        this.title = title;
        this.content = content;
        this.startPrice = startPrice;
    }

    public void addAuctions(Auctions auction){
        this.auctions.add(auction);
    }
    public void addComments(Comments comments) {
        this.comments.add(comments);
    }

    public void increaseViewCount(){
        this.viewCount += 1;
    }

}

