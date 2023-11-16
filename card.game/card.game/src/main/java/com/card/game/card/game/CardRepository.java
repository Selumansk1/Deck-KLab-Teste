package com.card.game.card.game;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository  extends JpaRepository<Vencedor, Long> {
}

