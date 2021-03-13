package io.youngwon.app.domain.likes;

import io.youngwon.app.domain.BaseTimeEntity;
import io.youngwon.app.domain.products.Products;
import io.youngwon.app.domain.users.Users;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class Likes extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Users users;

    @ManyToOne
    private Products products;


}
