package io.youngwon.app.domain.likes.domain;

import io.youngwon.app.domain.BaseTimeEntity;
import io.youngwon.app.domain.products.domain.Products;
import io.youngwon.app.domain.users.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Table(indexes = @Index(name="i_likes", columnList="users, products"))
@Entity
public class Likes extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="products")
    private Products products;

    @ManyToOne
    @JoinColumn(name="users")
    private User users;



    public Likes(Products products, User users){
        this.products = products;
        this.users = users;
    }


    @Override
    public String toString() {
        return "Likes{" +
                "id=" + id +
                ", products=" + products.getId() +
                ", users=" + users.getId() +
                '}';
    }
}
