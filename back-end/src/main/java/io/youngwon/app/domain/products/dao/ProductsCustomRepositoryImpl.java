package io.youngwon.app.domain.products.dao;

import com.querydsl.core.types.dsl.BooleanExpression;
import io.youngwon.app.domain.products.domain.Products;
import io.youngwon.app.domain.products.domain.QProducts;
import io.youngwon.app.domain.products.domain.State;
import io.youngwon.app.domain.products.dto.ProductsStateType;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public class ProductsCustomRepositoryImpl extends QuerydslRepositorySupport implements ProductsCustomRepository {

    public ProductsCustomRepositoryImpl() {
        super(Products.class);
    }


    @Override
    public List<Products> findAllForStartCheck(LocalDateTime now) {
        final QProducts product = QProducts.products;

        // 시작 시간이 지났는데 대기중일경우
        return from(product)
                .where(
                        product.startDateTime.before(now),
                        product.state.eq(State.WAITING)
                ).fetch();
    }


    @Override
    public List<Products> findAllForEndCheck(LocalDateTime now) {
        final QProducts product = QProducts.products;

        // 종료 시간이 지났는데 판매중인경우
        return from(product)
                .where(
                        product.endDateTime.before(now),
                        product.state.ne(State.FINISH)
                ).fetch();
    }


}
