package io.youngwon.app.domain.auctions.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionsRepository extends JpaRepository<Auctions, Long> {
}
