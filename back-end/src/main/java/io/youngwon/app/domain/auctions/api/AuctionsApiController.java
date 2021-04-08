package io.youngwon.app.domain.auctions.api;


import io.youngwon.app.domain.auctions.service.AuctionsService;
import io.youngwon.app.domain.auctions.dto.AuctionsEnterRequestDto;
import io.youngwon.app.domain.auctions.dto.AuctionsListResponseDto;
import io.youngwon.app.security.JwtAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.youngwon.app.utils.ApiUtils.ApiResult;
import static io.youngwon.app.utils.ApiUtils.success;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/products")
public class AuctionsApiController {

    private final AuctionsService auctionsService;

    private final SimpMessagingTemplate messagingTemplate;


    @PatchMapping(path = "{id}/auctions/enter")
    public ApiResult<List<AuctionsListResponseDto>> enter(
            @PathVariable Long id,
            @RequestBody AuctionsEnterRequestDto requestDto,
            @AuthenticationPrincipal JwtAuthentication authentication) {


        // 시간 채
        // isFinish Check


        List<AuctionsListResponseDto> result = auctionsService.enter(id, requestDto, authentication.id);
        // 새로운 경매요소 반환


        // 상품에 대한 auction 전체 정보 반환?
        messagingTemplate.convertAndSend("/topic/auctions", result);
        return success(result);
    }


    @PatchMapping("{productId}/auctions/{id}/cancel")
    public ApiResult<List<AuctionsListResponseDto>> cancel(@PathVariable Long productId,
                            @PathVariable Long id) {


        auctionsService.cancel(productId, id);
        return null;
    }



//    @GetMapping("auctions")
//    public Long delete(){
//        auctionsService.deleteAll();
//        return 0L;
//    }


}
