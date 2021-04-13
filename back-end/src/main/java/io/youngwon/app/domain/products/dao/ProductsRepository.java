package io.youngwon.app.domain.products.dao;

import io.youngwon.app.domain.categories.domain.Categories;
import io.youngwon.app.domain.products.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Product, Long> , ProductsCustomRepository,
        QuerydslPredicateExecutor<Product> {

    public List<Product> findByCategories(Categories categories);

//    public List<Products> findByEndDateTimeLessThanAndIsFinishIs(LocalDateTime endDateTime, Boolean isFinish);

}
