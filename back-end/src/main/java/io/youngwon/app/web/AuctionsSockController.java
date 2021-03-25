package io.youngwon.app.web;

import io.youngwon.app.service.AuctionsService;

import io.youngwon.app.web.dto.auctions.AuctionsCancelRequestDto;
import io.youngwon.app.web.dto.auctions.AuctionsResponseDto;
import io.youngwon.app.web.dto.auctions.AuctionsEnterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import static io.youngwon.app.utils.ApiUtils.ApiResult;
import static io.youngwon.app.utils.ApiUtils.success;

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
