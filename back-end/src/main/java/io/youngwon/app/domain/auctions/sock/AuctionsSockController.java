package io.youngwon.app.domain.auctions.sock;

import io.youngwon.app.domain.auctions.service.AuctionsService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class AuctionsSockController {


    private final AuctionsService auctionsService;


//    /**
//     * @param requestDto
//     * @return infomations of product
//     */
//
//    @MessageMapping("auctions/enter")
//    @SendTo("/topic/auctions")
//    public ApiResult<AuctionsResponseDto> enter(@RequestBody AuctionsEnterRequestDto requestDto) {
//
//        // 새로운 경매요소 반환
//        return success(auctionsService.enter(requestDto));
//    }
//
//
//    @MessageMapping("auctions/cancel")
//    @SendTo("/topic/auctions")
//    public ApiResult<AuctionsResponseDto> cancel(@RequestBody AuctionsCancelRequestDto requestDto) {
//
//
//        // 이전 경매요소 반환
//        return success(auctionsService.cancel(requestDto));
//    }
//

}
