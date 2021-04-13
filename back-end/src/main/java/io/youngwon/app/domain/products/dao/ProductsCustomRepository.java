package io.youngwon.app.domain.products.dao;

import io.youngwon.app.domain.products.domain.Product;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductsCustomRepository {


    public List<Product> findAllForStartCheck(LocalDateTime now);

    public List<Product> findAllForEndCheck(LocalDateTime now);

}
