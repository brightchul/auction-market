package io.youngwon.app.web;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.youngwon.app.Constant;
import io.youngwon.app.service.AuctionsService;
import io.youngwon.app.web.dto.auctions.AuctionsEnterRequestDto;
import io.youngwon.app.web.dto.auctions.AuctionsListResponseDto;
import io.youngwon.app.web.dto.auctions.AuctionsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    public ApiResult<List<AuctionsListResponseDto>> enter(@PathVariable Long id,
                                                @RequestBody AuctionsEnterRequestDto requestDto) {


        // 시간 채
        // isFinish Check


        List<AuctionsListResponseDto> result = auctionsService.enter(id, requestDto, Constant.USER_ID);
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
