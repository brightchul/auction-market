package io.youngwon.app.service;


import io.youngwon.app.domain.products.Products;
import io.youngwon.app.domain.products.ProductsRepository;
import io.youngwon.app.web.dto.products.ProductsDto;
import io.youngwon.app.web.dto.products.ProductsListDto;
import io.youngwon.app.web.dto.products.ProductsSaveRequestDto;
import io.youngwon.app.web.dto.products.ProductsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductsService {

    private ProductsRepository productsRepository;

    public ProductsDto findById(Long id) {
        return null;
    }

    public List<ProductsListDto> findAll() {
        return null;
    }

    public Long save(ProductsSaveRequestDto requestDto) {
        return null;
    }

    public Long update(Long id, ProductsUpdateRequestDto requestDto) {
        return null;
    }

    public Long delete(Long id) {
        return null;
    }

    public Boolean like() {
        return null;
    }

    public Boolean unlike() {
        return null;
    }

}
