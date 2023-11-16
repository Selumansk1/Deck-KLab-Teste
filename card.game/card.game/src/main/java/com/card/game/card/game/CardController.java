package com.card.game.card.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardRepository cardRepository;
    List<String> player1Cards =  new ArrayList<>();
    List<String> player2Cards =  new ArrayList<>();
    List<String> player3Cards = new ArrayList<>();
    List<String> player4Cards = new ArrayList<>();

    @Autowired
    private CardService cardService;

    @GetMapping("/play")
    public String playCardGame() {
        Map<String, Object> newDeck = cardService.createNewDeck();
        String deckId = "new";

        cardService.shuffleDeck(deckId);

        player1Cards = drawCards(deckId, 5);
        player2Cards = drawCards(deckId, 5);
        player3Cards = drawCards(deckId, 5);
        player4Cards = drawCards(deckId, 5);

        int player1Points = calculatePoints(player1Cards);
        int player2Points = calculatePoints(player2Cards);
        int player3Points = calculatePoints(player3Cards);
        int player4Points = calculatePoints(player4Cards);

        String winner = determineWinner(player1Points, player2Points, player3Points, player4Points);

        int winnerPoints = calculatePoints(getPlayerCardsByNumber(winner));

        Vencedor vencedor = new Vencedor();
        vencedor.setNome(winner);
        vencedor.setPontos(winnerPoints);
        incluir(vencedor);

        return "Vencedor é o Jogador " + winner + " com " + winnerPoints + " pontos.";
    }

    @PostMapping
    public String incluir(@RequestBody Vencedor vencedor) {
        cardRepository.save(vencedor);
        return "Vencedor cadastrdo!";
    }

    private List<String> drawCards(String deckId, int count) {
        Map<String, Object> drawResult = cardService.drawCards(deckId, count);
        List<Map<String, Object>> cardsList = (List<Map<String, Object>>) drawResult.get("cards");

        List<String> extractedCards = new ArrayList<>();
        for (Map<String, Object> cardMap : cardsList) {
            Object value = cardMap.get("value");
            if (value instanceof String) {
                extractedCards.add((String) value);
            }
        }
        return extractedCards;
    }

    private int calculatePoints(List<String> cards) {
        int points = 0;

        for (String card : cards) {
            points += getCardValue(card);
        }

        return points;
    }

    private int getCardValue(String card) {
        switch (card.charAt(0)) {
            case 'A':
                return 1;
            case 'K':
                return 13;
            case 'Q':
                return 12;
            case 'J':
                return 11;
            default:
                return Character.getNumericValue(card.charAt(0));
        }
    }

    private String determineWinner(int player1Points, int player2Points, int player3Points, int player4Points) {
        if (player1Points >= player2Points && player1Points >= player3Points && player1Points >= player4Points) {
            return "1";
        } else if (player2Points >= player1Points && player2Points >= player3Points && player2Points >= player4Points) {
            return "2";
        } else if (player3Points >= player1Points && player3Points >= player2Points && player3Points >= player4Points) {
            return "3";
        } else {
            return "4";
        }
    }

    private List<String> getPlayerCardsByNumber(String playerNumber) {
        switch (playerNumber) {
            case "1":
                return player1Cards;
            case "2":
                return player2Cards;
            case "3":
                return player3Cards;
            case "4":
                return player4Cards;
            default:
                return Collections.emptyList();
        }
    }
}

