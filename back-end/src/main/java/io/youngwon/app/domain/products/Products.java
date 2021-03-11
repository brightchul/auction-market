package io.youngwon.app.domain.products;


import io.youngwon.app.domain.BaseTimeEntity;
import io.youngwon.app.domain.users.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Products extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Users seller;

    private String title;

    private String content;

    private Integer view_count;

    private void update(String title, String content){

    }

    public void increaseViewCount(){

    }

}

