package io.youngwon.app.web.dto.auctions;

import io.youngwon.app.domain.BaseTimeEntity;
import io.youngwon.app.domain.auctions.Auctions;
import io.youngwon.app.domain.products.Products;
import io.youngwon.app.domain.users.Users;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class AuctionsEnterRequestDto extends BaseTimeEntity {


    private Long price;

    public Auctions toEntity(Products products, Users users){
        return Auctions.builder().products(products).price(price).participants(users)
                .build();
    }
}
