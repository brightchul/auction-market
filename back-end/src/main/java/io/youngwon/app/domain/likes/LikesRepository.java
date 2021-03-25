package io.youngwon.app.domain.likes;

import io.youngwon.app.domain.products.Products;
import io.youngwon.app.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {

//    public Optional<Likes> findByProductsAndUsers(Products products, Users users);
}
