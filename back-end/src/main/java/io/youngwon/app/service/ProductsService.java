package io.youngwon.app.service;


import io.youngwon.app.config.errors.NotFoundException;
import io.youngwon.app.domain.categories.Categories;
import io.youngwon.app.domain.products.Products;
import io.youngwon.app.domain.products.ProductsRepository;
import io.youngwon.app.web.dto.products.ProductsResponseDto;
import io.youngwon.app.web.dto.products.ProductsListResponseDto;
import io.youngwon.app.web.dto.products.ProductsSaveRequestDto;
import io.youngwon.app.web.dto.products.ProductsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    @Transactional(readOnly = true)
    public ProductsResponseDto findById(Long id) {
        Products products = productsRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Could not found product for " + id));

        return new ProductsResponseDto(products);
    }

    @Transactional(readOnly = true)
    public List<ProductsListResponseDto> findAll() {
        return productsRepository
                .findAll()
                .stream()
                .map(ProductsListResponseDto::new)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public List<ProductsListResponseDto> findByCategories(Long id) {
        return productsRepository
                .findByCategories(new Categories(id))
                .stream()
                .map(ProductsListResponseDto::new)
                .collect(Collectors.toList());
    }


    @Transactional
    public Long save(Long userId, ProductsSaveRequestDto requestDto) {
//        requestDto.saveImages();
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
