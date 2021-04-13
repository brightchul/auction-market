package io.youngwon.app.domain.products.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import io.youngwon.app.domain.auctions.domain.QAuctions;
import io.youngwon.app.domain.categories.domain.Categories;
import io.youngwon.app.domain.likes.domain.QLikes;
import io.youngwon.app.domain.products.domain.Product;
import io.youngwon.app.domain.products.domain.QProduct;
import io.youngwon.app.domain.products.domain.State;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ProductsSearchService extends QuerydslRepositorySupport {

    public ProductsSearchService() {
        super(Product.class);
    }

    final QProduct product = QProduct.product;
    final QLikes likes = QLikes.likes;
    final QAuctions auctions = QAuctions.auctions;

    public Page<ProductsListResponseDto> findAll(
            final Categories categories,
            final String title,
            final String content,
            final ProductsStateType type,
//            final Boolean own,
//            final Boolean onLike,
//            final Boolean onAuction,
            final Long userId,
            final Pageable pageable
    ) {

        JPQLQuery<Product> query = from(product);

        // 동시검색?
        // 좋아요
        if (type != null && type == ProductsStateType.LIKE) {
            query = query.innerJoin(product.likes, likes)
                    .on(likes.users.id.eq(userId));
        }

        // 경매참여
        if (type != null && type == ProductsStateType.AUCTION) {
            query = query.innerJoin(product.auctions, auctions)
                    .on(auctions.participants.id.eq(userId));
        }


        query = query.where(
                eqCategories(categories),
                likeTitle(title),
                eqState(type),
                onlyOwn(type != null && type == ProductsStateType.OWN ? true : false, userId)
        ).orderBy(
                product.state.desc(),
                product.id.desc()
        );

        final List<Product> products = getQuerydsl().applyPagination(pageable, query).fetch();
        return new PageImpl<>(
                products.stream().map(d -> new ProductsListResponseDto(d, userId)).collect(Collectors.toList()),
                pageable,
                query.fetchCount());
    }


    private BooleanExpression eqCategories(Categories categories) {
        if (categories == null) {
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


    private BooleanExpression eqState(ProductsStateType type) {

        if (type == null || type == ProductsStateType.ALL) {
            return null;
        }

        switch (type) {
            case SELLING:
                return product.state.eq(State.SELLING);
            case WAITING:
                return product.state.eq(State.WAITING);
            case FINISH:
                return product.state.eq(State.FINISH);
            default:
                return null;
        }
    }


    private BooleanExpression onlyLike(Boolean onLike, Long userId) {


        if (onLike == null || !onLike) {
            return null;
        }

//        return product.likes.
        //contains(new Likes(like.products, new User(userId)));
//        users.eq(new User(userId));

//                .users.eq(new User(userId));
        return null;

    }


    private BooleanExpression onlyOwn(Boolean own, Long userId) {
        if (own == null) {
            return null;
        }
        return product.seller.eq(new User(userId));
    }


}
