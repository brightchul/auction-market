package io.youngwon.app.domain.auctions.dto;


import io.youngwon.app.domain.auctions.domain.Auctions;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AuctionsListResponseDto {

    private Long id;
    private Long price;
    private LocalDateTime createdAt;
    private Boolean isCancel;

    public AuctionsListResponseDto(Auctions auctions){
        this.id = auctions.getId();
        this.price = auctions.getPrice();
        this.isCancel = auctions.getIsCancel();
        this.createdAt = auctions.getCreatedAt();
    }

}
