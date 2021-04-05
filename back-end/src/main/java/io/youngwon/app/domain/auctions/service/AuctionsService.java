package io.youngwon.app.domain.auctions.service;

import io.youngwon.app.config.errors.NotFoundException;
import io.youngwon.app.domain.auctions.domain.Auctions;
import io.youngwon.app.domain.auctions.domain.AuctionsRepository;
import io.youngwon.app.domain.products.domain.Products;
import io.youngwon.app.domain.products.dao.ProductsRepository;
import io.youngwon.app.domain.users.domain.User;
import io.youngwon.app.domain.auctions.dto.AuctionsListResponseDto;
import io.youngwon.app.domain.auctions.dto.AuctionsResponseDto;
import io.youngwon.app.domain.auctions.dto.AuctionsEnterRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuctionsService {

    private final ProductsRepository productsRepository;
    private final AuctionsRepository auctionsRepository;


    @Transactional
    public List<AuctionsListResponseDto> enter(Long id, AuctionsEnterRequestDto requestDto, Long userId){
        Products products =  productsRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Could not found product for " + id));

        Auctions auctions = auctionsRepository.save(requestDto.toEntity(products, new User(userId)));
        products.addAuctions(auctions);

        return products.getAuctions().stream().map(AuctionsListResponseDto::new).collect(Collectors.toList());
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
