package io.youngwon.app.domain.products.dao;

import io.youngwon.app.domain.products.domain.Product;
import io.youngwon.app.domain.products.domain.QProduct;
import io.youngwon.app.domain.products.domain.State;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public class ProductsCustomRepositoryImpl extends QuerydslRepositorySupport implements ProductsCustomRepository {

    public ProductsCustomRepositoryImpl() {
        super(Product.class);
    }


    @Override
    public List<Product> findAllForStartCheck(LocalDateTime now) {
        final QProduct product = QProduct.product;

        // 시작 시간이 지났는데 대기중일경우
        return from(product)
                .where(
                        product.startDateTime.before(now),
                        product.state.eq(State.WAITING)
                ).fetch();
    }


    @Override
    public List<Product> findAllForEndCheck(LocalDateTime now) {
        final QProduct product = QProduct.product;

        // 종료 시간이 지났는데 판매중인경우
        return from(product)
                .where(
                        product.endDateTime.before(now),
                        product.state.ne(State.FINISH)
                ).fetch();
    }


}
