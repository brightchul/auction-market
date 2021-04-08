package io.youngwon.app.domain.products.dao;

import io.youngwon.app.domain.categories.domain.Categories;
import io.youngwon.app.domain.products.domain.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, Long> , ProductsCustomRepository,
        QuerydslPredicateExecutor<Products> {

    public List<Products> findByCategories(Categories categories);

//    public List<Products> findByEndDateTimeLessThanAndIsFinishIs(LocalDateTime endDateTime, Boolean isFinish);

}
