package io.youngwon.app.domain.auctions;


import io.youngwon.app.domain.BaseTimeEntity;
import io.youngwon.app.domain.products.Products;
import io.youngwon.app.domain.users.Users;
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
    private Users participants;

    private Boolean isCancel;

    private Long price;

    @Builder
    public Auctions(Products products, Long price){
        this.products = products;
        this.price = price;
        this.isCancel = false;
    }


    public void cancel(){
        this.isCancel = true;
    }

}
