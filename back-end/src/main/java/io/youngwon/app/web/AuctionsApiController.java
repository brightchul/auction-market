package io.youngwon.app.web;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.youngwon.app.Constant;
import io.youngwon.app.service.AuctionsService;
import io.youngwon.app.web.dto.auctions.AuctionsEnterRequestDto;
import io.youngwon.app.web.dto.auctions.AuctionsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static io.youngwon.app.utils.ApiUtils.ApiResult;
import static io.youngwon.app.utils.ApiUtils.success;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/products")
public class AuctionsApiController {

    private final AuctionsService auctionsService;

    private final SimpMessagingTemplate messagingTemplate;


    @PatchMapping(path = "{id}/auctions/enter")
    public ApiResult<AuctionsResponseDto> enter(@PathVariable Long id,
                                                @RequestBody AuctionsEnterRequestDto requestDto) {

        AuctionsResponseDto result = auctionsService.enter(id, requestDto, Constant.USER_ID);
        // 새로운 경매요소 반환

        messagingTemplate.convertAndSend("/topic/auctions", result);
        return success(result);
    }


    @PatchMapping("{productId}/auctions/{id}/cancel")
    public ApiResult cancel(@PathVariable Long productId,
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
