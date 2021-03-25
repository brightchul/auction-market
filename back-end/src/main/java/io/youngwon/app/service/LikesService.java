package io.youngwon.app.service;

import io.youngwon.app.config.errors.NotFoundException;
import io.youngwon.app.domain.likes.Likes;
import io.youngwon.app.domain.likes.LikesRepository;
import io.youngwon.app.domain.products.Products;
import io.youngwon.app.domain.products.ProductsRepository;
import io.youngwon.app.domain.users.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LikesService {

    private final ProductsRepository productsRepository;
    private final LikesRepository likesRepository;

    @Transactional
    public Integer like(Long id, Long userId){
        Products products = productsRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Could not found product for " + id));

        return products.like(new Likes(products, new Users(userId)));
    }


    @Transactional
    public Integer unlike(Long id, Long userId){
        Products products = productsRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Could not found product for " + id));

        Likes likes = products.getLikes()
                .stream().filter(like->like.getUsers().getId() == userId).findFirst()
                .orElseThrow(() -> new NotFoundException("Could not found likes for products " + id + " and user " + userId));

        return products.unlike(likes);
    }

}
