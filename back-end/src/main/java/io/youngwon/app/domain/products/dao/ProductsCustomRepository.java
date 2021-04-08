package io.youngwon.app.domain.products.dao;

import io.youngwon.app.domain.products.domain.Products;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductsCustomRepository {


    public List<Products> findAllForStartCheck(LocalDateTime now);

    public List<Products> findAllForEndCheck(LocalDateTime now);

}
