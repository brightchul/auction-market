package io.youngwon.app.domain.comments;

import io.youngwon.app.domain.BaseTimeEntity;
import io.youngwon.app.domain.products.Products;
import io.youngwon.app.domain.users.Users;
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
    private Users writer;


    public Comments(String content, Products products) {
        this.content = content;
        this.products = products;
    }

    public void update(String content) {
        this.content = content;
    }

}
