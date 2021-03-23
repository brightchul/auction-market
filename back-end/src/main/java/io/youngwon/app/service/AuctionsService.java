package io.youngwon.app.service;

import io.youngwon.app.config.errors.NotFoundException;
import io.youngwon.app.domain.auctions.Auctions;
import io.youngwon.app.domain.auctions.AuctionsRepository;
import io.youngwon.app.domain.products.Products;
import io.youngwon.app.domain.products.ProductsRepository;
import io.youngwon.app.web.dto.auctions.AuctionsCancelRequestDto;
import io.youngwon.app.web.dto.auctions.AuctionsResponseDto;
import io.youngwon.app.web.dto.auctions.AuctionsEnterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuctionsService {

    private final ProductsRepository productsRepository;
    private final AuctionsRepository auctionsRepository;


    @Transactional
    public AuctionsResponseDto enter(AuctionsEnterRequestDto requestDto){
        Products products =  productsRepository
                .findById(requestDto.getProductsId())
                .orElseThrow(() -> new NotFoundException("Could not found product for " + requestDto.getProductsId()));

        Auctions auctions = requestDto.toEntity(products);
        products.addAuctions(auctions);
        return new AuctionsResponseDto(auctions);
    }

    @Transactional
    public AuctionsResponseDto cancel(AuctionsCancelRequestDto requestDto){
        Products products =  productsRepository
                .findById(requestDto.getProductsId())
                .orElseThrow(() -> new NotFoundException("Could not found product for " + requestDto.getProductsId()));

//        Auctions auctions = auctionsRepository.findById(requestDto.getAuctoinsId())
//                .orElseThrow(() -> new NotFoundException("Could not found auction for " + requestDto.getAuctoinsId()));

        if(products.getAuctions().size() > 0 &&
                products.getAuctions().get(0).getId() == requestDto.getAuctoinsId()){
            products.getAuctions().get(0).cancel();
        }

        if(products.getAuctions().size() > 1){
            return new AuctionsResponseDto(products.getAuctions().get(1));
        }

        return null;
    }



}
