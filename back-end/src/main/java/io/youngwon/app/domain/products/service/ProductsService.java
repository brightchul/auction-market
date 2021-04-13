package io.youngwon.app.domain.products.service;


import io.youngwon.app.config.errors.NotFoundException;
import io.youngwon.app.domain.categories.domain.Categories;
import io.youngwon.app.domain.products.domain.Product;
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
        Product products = productsRepository
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

    @Transactional(readOnly = true)
    public List<ProductsListStateResponseDto> findAllForStartCheck(LocalDateTime now) {
        return productsRepository
                .findAllForStartCheck(now)
                .stream()
                .map(ProductsListStateResponseDto::new)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public List<ProductsListStateResponseDto> findAllForEndCheck(LocalDateTime now) {
        return productsRepository
                .findAllForEndCheck(now)
                .stream()
                .map(ProductsListStateResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long updateState(Long id, ProductsStateType type){

        Product products = productsRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Could not found product for " + id));

        if(type == ProductsStateType.SELLING){
            products.onSale();
        }else if(type == ProductsStateType.FINISH){
            products.finish();
        }

        return id;
    }

    @Transactional
    public Long save(ProductsSaveRequestDto requestDto, Long userId) {
        return productsRepository
                .save(requestDto.toEntity(userId))
                .getId();
    }

    @Transactional
    public Long update(Long id, ProductsUpdateRequestDto requestDto) {
        Product products = productsRepository
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
        Product products = productsRepository
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
