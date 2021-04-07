package io.youngwon.app.domain.products.dao;

import io.youngwon.app.domain.products.domain.Products;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class ProductsCustomRepositoryImpl extends QuerydslRepositorySupport implements ProductsCustomRepository {

    public ProductsCustomRepositoryImpl(){
        super(Products.class);
    }
//
//    @Override
//    public List<Products> findOnSale(ProductsSearchType type, String value, Pageable pageable) {
//        final QProducts products = QProducts.products;
//        return from(products)
//                .where(products.endDateTime.after(LocalDateTime.now())).fetch();
//    }
}
