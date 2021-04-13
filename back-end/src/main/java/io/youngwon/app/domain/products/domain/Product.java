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
@Table(name = "products")
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Categories categories;

    @ManyToOne
    @JoinColumn(name="seller_id")
    private User seller;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    @ColumnDefault("0")
    @Column(name="view_count")
    private Integer viewCount;

    @Column(name="start_price")
    private Long startPrice;

    @Column(name="start_date_time")
    private LocalDateTime startDateTime;

    @Column(name="end_date_time")
    private LocalDateTime endDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name="state")
    private State state;

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


    public Product(Long id) {
        this.id = id;
    }


    public Product(ProductsSaveRequestDto requestDto, Long seller) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.startPrice = requestDto.getStartPrice();
        this.categories = new Categories(requestDto.getCategories());
        this.seller = new User(seller);

        // 초기등록시 무조건 대기
        this.state = State.WAITING;

        this.startDateTime = LocalDateTime.parse(requestDto.getStartDateTime(), Constant.FORMATTER);
        this.endDateTime = LocalDateTime.parse(requestDto.getEndDateTime(), Constant.FORMATTER);

        if (requestDto.getImages() != null) {
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

    public void updateComents(Long id, String content) {
//        this.comments.
    }

    public void deleteComents(Long id) {

    }

    public void onSale() {
        this.state = State.SELLING;
    }

    public void finish() {
        this.state = State.FINISH;
    }

    public Integer like(Likes likes) {
        this.likes.add(likes);
        return this.likes.size();
    }

    public Integer unlike(Likes likes) {
        this.likes.remove(likes);
        return this.likes.size();
    }

    public void increaseViewCount() {
        this.viewCount += 1;
    }

}

