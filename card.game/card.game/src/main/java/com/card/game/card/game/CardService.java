package com.card.game.card.game;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "card-api", url = "https://deckofcardsapi.com/api/deck/")
public interface CardService {

    @GetMapping(path = "/new/shuffle/?deck_count=1", consumes = "application/json")
    Map<String, Object> createNewDeck();

    @GetMapping(path = "/{deckId}/shuffle/", consumes = "application/json")
    void shuffleDeck(@PathVariable String deckId);

    @GetMapping(path = "/{deckId}/draw/?count={count}", consumes = "application/json")
    Map<String, Object> drawCards(@PathVariable String deckId, @PathVariable int count);
}

