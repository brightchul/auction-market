package io.youngwon.app.web.dto.auctions;


import io.youngwon.app.domain.auctions.Auctions;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
