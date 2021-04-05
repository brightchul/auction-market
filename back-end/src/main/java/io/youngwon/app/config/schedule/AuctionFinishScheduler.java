package io.youngwon.app.config.schedule;

import io.youngwon.app.domain.products.dao.ProductsRepository;
import io.youngwon.app.domain.products.service.ProductsService;
import io.youngwon.app.domain.products.dto.ProductsListAuctionResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuctionFinishScheduler {

    public final ProductsService productsService;

    public final ProductsRepository productsRepository;

    private final SimpMessagingTemplate messagingTemplate;



    @Scheduled(cron = "0 * * * * *")
    public void auctionEndCheck() {
        // 완료된 경매가 있는지 검사
        // end면서
//        productsService
        // product


        //findByIsNotFinish
        List<ProductsListAuctionResponseDto> products = productsService.findByNeedToFinish();

        // 전부
        for (int i = 0; i < products.size(); i++) {
            productsService.toFinish(products.get(i).getId());
        }


        messagingTemplate.convertAndSend("/topic/finish", products);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        log.info("Java cron job expression:: " + strDate);
    }
}
