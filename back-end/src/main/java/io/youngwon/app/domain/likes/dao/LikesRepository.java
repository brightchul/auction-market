package io.youngwon.app.domain.likes.dao;

import io.youngwon.app.domain.likes.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Long> {

//    public Optional<Likes> findByProductsAndUsers(Products products, Users users);
}
