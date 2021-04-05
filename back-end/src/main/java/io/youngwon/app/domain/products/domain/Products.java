package io.youngwon.app.domain.products.domain;


import io.youngwon.app.Constant;
import io.youngwon.app.domain.BaseTimeEntity;
import io.youngwon.app.domain.auctions.domain.Auctions;
import io.youngwon.app.domain.categories.domain.Categories;
import io.youngwon.app.domain.comments.domain.Comments;
import io.youngwon.app.domain.files.Files;
import io.youngwon.app.domain.likes.domain.Likes;
import io.youngwon.app.domain.users.domain.User;
import io.youngwon.app.domain.products.dto.ProductsSaveRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ToString
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
    private User seller;

    private String title;

    private String content;

    @ColumnDefault("0")
    private Integer viewCount;

    private Long startPrice;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    @ColumnDefault("false")
    private Boolean isFinish;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Files> files = new ArrayList<Files>();

    @OrderBy("createdAt desc")
    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Auctions> auctions = new ArrayList<Auctions>();

    @OrderBy("createdAt desc")
    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comments> comments = new ArrayList<Comments>();


    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Likes> likes = new ArrayList<Likes>();




    public Products(ProductsSaveRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.startPrice = requestDto.getStartPrice();
        this.categories = new Categories(requestDto.getCategories());

        // 포맷 체크

        this.startDateTime = LocalDateTime.parse(requestDto.getStartDateTime(), Constant.FORMATTER);
        this.endDateTime = LocalDateTime.parse(requestDto.getEndDateTime(), Constant.FORMATTER);


        if(requestDto.getImages() != null) {
            this.files.addAll(Arrays.stream(requestDto.getImages())
                    .map(n -> new Files(this, n))
                    .collect(Collectors.toList()));
        }
    }

    public void update(String title, String content, Long startPrice) {
        this.title = title;
        this.content = content;
        this.startPrice = startPrice;
    }

    public Auctions addAuctions(Auctions auction) {
        this.auctions.add(auction);
        return auction;
    }

    public void addComments(Comments comments) {
        this.comments.add(comments);
    }

    public void updateComents(Long id, String content){
//        this.comments.
    }

    public void deleteComents(Long id){

    }

    public void toFinish(){
        this.isFinish = true;
    }

    public Integer like(Likes likes) {
        this.likes.add(likes);
        return this.likes.size();
    }

    public Integer unlike(Likes likes){
        this.likes.remove(likes);
        return this.likes.size();
    }

    public void increaseViewCount() {
        this.viewCount += 1;
    }

}

