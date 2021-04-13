package io.youngwon.app.config.schedule;

import io.youngwon.app.domain.products.dto.ProductsListStateResponseDto;
import io.youngwon.app.domain.products.dto.ProductsStateType;
import io.youngwon.app.domain.products.service.ProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuctionFinishScheduler {



    public final ProductsService productsService;

    private final SimpMessagingTemplate messagingTemplate;


    @Transactional
    @Scheduled(cron = "0 * * * * *")
    public void auctionEndCheck() {
        // 완료된 경매가 있는지 검사
        // end면서
//        productsService
        // product



        LocalDateTime now = LocalDateTime.now();
        // on sale
        List<ProductsListStateResponseDto> startedList = productsService.findAllForStartCheck(now);
        List<ProductsListStateResponseDto> finishedList = productsService.findAllForEndCheck(now);



        for(ProductsListStateResponseDto product : startedList){
            productsService.updateState(product.getId(), ProductsStateType.SELLING);
        }

        for(ProductsListStateResponseDto product : finishedList){
            productsService.updateState(product.getId(), ProductsStateType.FINISH);
        }

        // finish




        //findByIsNotFinish
//        List<ProductsListAuctionResponseDto> products = productsService.findByNeedToFinish();
//
//        // 전부
//        for (int i = 0; i < products.size(); i++) {
//            productsService.toFinish(products.get(i).getId());
//        }

        messagingTemplate.convertAndSend("/topic/state",
                finishedList
        );



        log.info("Java cron job expression:: " + now);
    }
}
