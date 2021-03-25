package io.youngwon.app.domain.products;


import io.youngwon.app.domain.BaseTimeEntity;
import io.youngwon.app.domain.auctions.Auctions;
import io.youngwon.app.domain.categories.Categories;
import io.youngwon.app.domain.comments.Comments;
import io.youngwon.app.domain.files.Files;
import io.youngwon.app.domain.likes.Likes;
import io.youngwon.app.domain.users.Users;
import io.youngwon.app.web.dto.products.ProductsSaveRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private Users seller;

    private String title;

    private String content;

    private Integer viewCount;

    private Long startPrice;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm A");

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


        if (StringUtils.isBlank(requestDto.getStartDateTime())) {
            this.startDateTime = LocalDateTime.parse(requestDto.getStartDateTime(), formatter);
        }

        if (StringUtils.isBlank(requestDto.getStartDateTime())) {
            this.endDateTime = LocalDateTime.parse(requestDto.getEndDateTime(), formatter);
        }


        this.files.addAll(Arrays.stream(requestDto.getImages())
                .map(n -> new Files(this, n))
                .collect(Collectors.toList()));

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

