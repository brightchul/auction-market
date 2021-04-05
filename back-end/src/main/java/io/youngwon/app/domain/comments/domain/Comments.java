package io.youngwon.app.domain.comments.domain;

import io.youngwon.app.domain.BaseTimeEntity;
import io.youngwon.app.domain.products.domain.Products;
import io.youngwon.app.domain.users.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comments extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    private Products products;

    @ManyToOne
    private User writer;


    public Comments(String content, Products products) {
        this.content = content;
        this.products = products;
    }

    public void update(String content) {
        this.content = content;
    }

}
