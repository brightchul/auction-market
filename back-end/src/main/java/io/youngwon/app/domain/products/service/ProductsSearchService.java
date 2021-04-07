package io.youngwon.app.domain.products.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import io.youngwon.app.domain.categories.domain.Categories;
import io.youngwon.app.domain.products.domain.Products;
import io.youngwon.app.domain.products.domain.QProducts;
import io.youngwon.app.domain.products.dto.ProductsListResponseDto;
import io.youngwon.app.domain.products.dto.ProductsStateType;
import io.youngwon.app.domain.users.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ProductsSearchService extends QuerydslRepositorySupport {

    public ProductsSearchService() {
        super(Products.class);
    }

    final QProducts product = QProducts.products;


    public Page<ProductsListResponseDto> findAll(
            final Categories categories,
            final String title,
            final String content,
            final ProductsStateType type,
            final Boolean own,
            final Long userId,
            final Pageable pageable
    ) {

        final JPQLQuery<Products> query;

        query = from(product)
                    .where(
                            eqCategories(categories),
                            likeTitle(title),
                            eqType(type),
                            onlyOwn(own, userId)
                    ).orderBy(
                            product.isFinish.asc(),
                            product.id.desc()
                    );

        final List<Products> products = getQuerydsl().applyPagination(pageable, query).fetch();
        return new PageImpl<>(
                products.stream().map(d->new ProductsListResponseDto(d, userId)).collect(Collectors.toList()),
                pageable,
                query.fetchCount());
    }


    private BooleanExpression eqCategories(Categories categories){
        if(categories == null){
            return null;
        }
        return product.categories.eq(categories);
    }

    private BooleanExpression likeTitle(String title) {
        if (StringUtils.isEmpty(title)) {
            return null;
        }
        return product.title.like("%" + title + "%");


    }

    private BooleanExpression eqType(ProductsStateType type) {
        if(type == null || type == ProductsStateType.ALL){
            return null;
        }

        LocalDateTime now = LocalDateTime.now();
        if(type == ProductsStateType.SELLING){
            return product.startDateTime.before(now)
                    .and(product.endDateTime.after(now));
        }

        if(type == ProductsStateType.FINISH){
            // 시간으로 체크 ?
            return product.isFinish.eq(true);
        }

        // WAITTING
        return product.startDateTime.after(now);
    }


    private BooleanExpression onlyOwn(Boolean own, Long userId) {
        if(own == null){
            return null;
        }
        return product.seller.eq(new User(userId));
    }


}
