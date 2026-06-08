package reddot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import component.PlayingCard;
import org.junit.jupiter.api.Test;
import poker.PlayingCards;
import unhappyEC.ECManager;
import unhappyEC.Entity;

public class RedDotGameSmokeTest {
    @Test
    void startsWithPlayerHandsAndTableCards() {
        RedDotGame game = new RedDotGame();
        Entity first = game.addPlayer();
        Entity second = game.addPlayer();

        game.start(6, 4);

        assertEquals(6, game.hand(first).size());
        assertEquals(6, game.hand(second).size());
        assertEquals(4, game.tableCards().size());
    }

    @Test
    void scoresCommonRedDotCards() {
        assertEquals(30, score(PlayingCard.Suit.SPADES, PlayingCard.Rank.ACE));
        assertEquals(20, score(PlayingCard.Suit.CLUBS, PlayingCard.Rank.TWO));
        assertEquals(10, score(PlayingCard.Suit.HEARTS, PlayingCard.Rank.KING));
        assertEquals(7, score(PlayingCard.Suit.DIAMONDS, PlayingCard.Rank.SEVEN));
        assertEquals(0, score(PlayingCard.Suit.SPADES, PlayingCard.Rank.KING));
    }

    private int score(PlayingCard.Suit suit, PlayingCard.Rank rank) {
        Entity entity = new ECManager().newEntity();
        entity.addComponent(new PlayingCard(suit, rank));
        return PlayingCards.redDotScore(PlayingCards.card(entity));
    }
}
