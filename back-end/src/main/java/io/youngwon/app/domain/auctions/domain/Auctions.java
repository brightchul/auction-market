package io.youngwon.app.domain.auctions.domain;


import io.youngwon.app.domain.BaseTimeEntity;
import io.youngwon.app.domain.products.domain.Products;
import io.youngwon.app.domain.users.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Auctions extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Products products;

    @ManyToOne
    private User participants;

    private Boolean isCancel;

    private Long price;

    @Builder
    public Auctions(Products products, Long price, User participants){
        this.products = products;
        this.price = price;
        this.participants = participants;
        this.isCancel = false;

    }


    public void cancel(){
        this.isCancel = true;
    }

}
