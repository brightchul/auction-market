package io.youngwon.app.domain.products.service;


import io.youngwon.app.config.errors.NotFoundException;
import io.youngwon.app.domain.categories.domain.Categories;
import io.youngwon.app.domain.products.domain.Products;
import io.youngwon.app.domain.products.dao.ProductsRepository;
import io.youngwon.app.domain.products.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    @Transactional(readOnly = true)
    public ProductsResponseDto findById(Long id, Long userId) {
        Products products = productsRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Could not found product for " + id));

        return new ProductsResponseDto(products, userId);
    }

    @Transactional(readOnly = true)
    public List<ProductsListResponseDto> findAll(
            final ProductsStateType type,
            final String value,
            final Pageable pageable,
            final Long userId) {


        return productsRepository
                .findAll()
                .stream()
                .map(d -> new ProductsListResponseDto(d, userId))
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public List<ProductsListResponseDto> findByCategories(Long id, Long userId) {
        return productsRepository
                .findByCategories(new Categories(id))
                .stream()
                .map(d -> new ProductsListResponseDto(d, userId))
                .collect(Collectors.toList());
    }

//    @Transactional(readOnly = true)
//    public List<ProductsListAuctionResponseDto> findByNeedToFinish() {
//        return productsRepository.findByEndDateTimeLessThanAndIsFinishIs(LocalDateTime.now(), false)
//                .stream()
//                .map(ProductsListAuctionResponseDto::new)
//                .collect(Collectors.toList());
//    }

    @Transactional
    public Long save(ProductsSaveRequestDto requestDto) {
        return productsRepository
                .save(requestDto.toEntity())
                .getId();
    }

    @Transactional
    public Long update(Long id, ProductsUpdateRequestDto requestDto) {
        Products products = productsRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Could not found product for " + id));

        products.update(
                requestDto.getTitle(),
                requestDto.getContent(),
                requestDto.getStartPrice()
        );

        return id;
    }




//    @Transactional
//    public Long toFinish(Long id) {
//        Products products = productsRepository
//                .findById(id)
//                .orElseThrow(() -> new NotFoundException("Could not found product for " + id));
//        products.finish();
//        return id;
//    }

    @Transactional
    public Long delete(Long id) {
        Products products = productsRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Could not found product for " + id));

        productsRepository.delete(products);
        return id;
    }

    @Transactional
    public Boolean like(Long productId, Long userId) {
        return null;
    }

    @Transactional
    public Boolean unlike(Long productId, Long userId) {
        return null;
    }

}
