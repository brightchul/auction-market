package io.youngwon.app.domain.likes.domain;

import io.youngwon.app.domain.BaseTimeEntity;
import io.youngwon.app.domain.products.domain.Product;
import io.youngwon.app.domain.users.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Table(
        indexes = @Index(name="i_likes", columnList="users, products"),
        uniqueConstraints={
            @UniqueConstraint(
                    columnNames={"products", "users"}
            )
        })
@Entity
public class Likes extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="products")
    private Product products;

    @ManyToOne
    @JoinColumn(name="users")
    private User users;

    public Likes(Product products, User users){
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
