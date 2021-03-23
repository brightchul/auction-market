package io.youngwon.app.web;


import io.youngwon.app.service.AuctionsService;
import io.youngwon.app.web.dto.auctions.AuctionsEnterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static io.youngwon.app.utils.ApiUtils.ApiResult;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/products")
public class AuctionsApiController {

    private final AuctionsService auctionsService;
//
//    @GetMapping(path = "{id}/auctions")
//    public ApiResult findAll() {
//        return null;
//    }
//
//    @GetMapping("{id}/auctions/{aid}")
//    public ApiResult findById() {
//        return null;
//    }
//
//
//
//    @PatchMapping("{productId}/auctions")
//    public ApiResult enter(@PathVariable Long productId,
//                          @Valid @RequestBody AuctionsEnterRequestDto requestDto) {
//
//        auctionsService.enter(productId, requestDto);
//
//        // 소켓으로 푸시
//
//        return null;
//    }
//
//
//
//    @PatchMapping("{productId}/auctions/{id}/cancel")
//    public ApiResult cancel(@PathVariable Long productId,
//                            @PathVariable Long id) {
//
//        auctionsService.cancel(productId, id);
//        return null;
//    }




}
