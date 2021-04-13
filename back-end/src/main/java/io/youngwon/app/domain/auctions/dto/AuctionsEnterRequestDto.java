package io.youngwon.app.domain.auctions.dto;

import io.youngwon.app.domain.BaseTimeEntity;
import io.youngwon.app.domain.auctions.domain.Auctions;
import io.youngwon.app.domain.products.domain.Product;
import io.youngwon.app.domain.users.domain.User;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class AuctionsEnterRequestDto extends BaseTimeEntity {


    private Long price;

    public Auctions toEntity(Product products, User users){
        return Auctions.builder().products(products).price(price).participants(users)
                .build();
    }
}
