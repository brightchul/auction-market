package io.youngwon.app.config.schedule;

import io.youngwon.app.domain.products.Products;
import io.youngwon.app.domain.products.ProductsRepository;
import io.youngwon.app.service.ProductsService;
import io.youngwon.app.web.dto.products.ProductsListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
        List<ProductsListResponseDto> products = productsService.findByNeedToFinish();

        // 전부
        for (int i = 0; i < products.size(); i++) {
            productsService.toFinish(products.get(i).getId());
        }


        messagingTemplate.convertAndSend("/topic/finish", products);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        System.out.println("Java cron job expression:: " + strDate);
    }
}
