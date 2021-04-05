package io.youngwon.app.domain.products.service;

import com.querydsl.jpa.JPQLQuery;
import io.youngwon.app.domain.products.domain.Products;
import io.youngwon.app.domain.products.dto.ProductsSearchType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ProductsSearchService extends QuerydslRepositorySupport {

    public ProductsSearchService() {
        super(Products.class);
    }

    public Page<Products> search(final ProductsSearchType type, final String value, final Pageable pageable) {
//        final QProducts account = QProducts.account;
        final JPQLQuery<Products> query;

        switch (type) {
//            case EMAIL:
//                query = from(account)
//                        .where(account.email.value.likeIgnoreCase(value + "%"));
//                break;
//            case NAME:
//                query = from(account)
//                        .where(account.firstName.likeIgnoreCase(value + "%")
//                                .or(account.lastName.likeIgnoreCase(value + "%")));
//                break;
//            case ALL:
//                query = from(account).fetchAll();
//                break;
            default:
                throw new IllegalArgumentException();
        }
//        final List<Products> products = getQuerydsl().applyPagination(pageable, query).fetch();
//        return new PageImpl<>(products, pageable, query.fetchCount());
    }


}
