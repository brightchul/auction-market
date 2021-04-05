package io.youngwon.app.domain.users.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    public Optional<User> findByEmail(String email);
}

