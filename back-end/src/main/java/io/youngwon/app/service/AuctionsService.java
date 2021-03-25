package io.youngwon.app.service;

import io.youngwon.app.config.errors.NotFoundException;
import io.youngwon.app.domain.auctions.Auctions;
import io.youngwon.app.domain.auctions.AuctionsRepository;
import io.youngwon.app.domain.products.Products;
import io.youngwon.app.domain.products.ProductsRepository;
import io.youngwon.app.domain.users.Users;
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
    public AuctionsResponseDto enter(Long id, AuctionsEnterRequestDto requestDto, Long userId){
        Products products =  productsRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Could not found product for " + id));

        Auctions auctions = auctionsRepository.save(requestDto.toEntity(products, new Users(userId)));
        products.addAuctions(auctions);
        return new AuctionsResponseDto(auctions);
    }


    @Transactional
    public AuctionsResponseDto cancel(Long id, Long auctionId){
        Products products =  productsRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Could not found product for " + id));

//        Auctions auctions = auctionsRepository.findById(requestDto.getAuctoinsId())
//                .orElseThrow(() -> new NotFoundException("Could not found auction for " + requestDto.getAuctoinsId()));

        // 자신의 입찰이 가장 상위에 존재하면
        if(products.getAuctions().size() > 0 &&
                products.getAuctions().get(0).getId() == auctionId){
            products.getAuctions().get(0).cancel();
        }

        if(products.getAuctions().size() > 1){
            return new AuctionsResponseDto(products.getAuctions().get(1));
        }

        return null;
    }




    @Transactional
    public void deleteAll(){


        auctionsRepository.deleteAll();;

    }


}
