package io.youngwon.app.domain.likes;

import io.youngwon.app.domain.BaseTimeEntity;
import io.youngwon.app.domain.products.Products;
import io.youngwon.app.domain.users.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private Users users;



    public Likes(Products products, Users users){
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
