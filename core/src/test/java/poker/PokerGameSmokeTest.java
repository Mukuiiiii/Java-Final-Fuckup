package poker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.junit.jupiter.api.Test;
import unhappyEC.Entity;

public class PokerGameSmokeTest {
    @Test
    void dealsFiveCardHandsAndFindsWinner() {
        PokerGame game = new PokerGame();
        Entity first = game.addPlayer();
        Entity second = game.addPlayer();

        game.deal(5);

        assertEquals(5, game.hand(first).size());
        assertEquals(5, game.hand(second).size());
        assertNotNull(game.evaluateHand(first));
        assertNotNull(game.evaluateHand(second));
        assertNotNull(game.winner());
    }

    @Test
    void straightFlushBeatsFourOfAKind() {
        PokerHandEvaluator evaluator = new PokerHandEvaluator();

        PokerHandValue straightFlush = evaluator.evaluate(List.of(
            card(component.PlayingCard.Suit.HEARTS, component.PlayingCard.Rank.TEN),
            card(component.PlayingCard.Suit.HEARTS, component.PlayingCard.Rank.JACK),
            card(component.PlayingCard.Suit.HEARTS, component.PlayingCard.Rank.QUEEN),
            card(component.PlayingCard.Suit.HEARTS, component.PlayingCard.Rank.KING),
            card(component.PlayingCard.Suit.HEARTS, component.PlayingCard.Rank.ACE)
        ));

        PokerHandValue fourOfAKind = evaluator.evaluate(List.of(
            card(component.PlayingCard.Suit.CLUBS, component.PlayingCard.Rank.NINE),
            card(component.PlayingCard.Suit.DIAMONDS, component.PlayingCard.Rank.NINE),
            card(component.PlayingCard.Suit.HEARTS, component.PlayingCard.Rank.NINE),
            card(component.PlayingCard.Suit.SPADES, component.PlayingCard.Rank.NINE),
            card(component.PlayingCard.Suit.CLUBS, component.PlayingCard.Rank.ACE)
        ));

        assertEquals(PokerHandRank.STRAIGHT_FLUSH, straightFlush.getRank());
        assertEquals(PokerHandRank.FOUR_OF_A_KIND, fourOfAKind.getRank());
        assertEquals(1, Integer.signum(straightFlush.compareTo(fourOfAKind)));
    }

    private Entity card(component.PlayingCard.Suit suit, component.PlayingCard.Rank rank) {
        Entity entity = new unhappyEC.ECManager().newEntity();
        entity.addComponent(new component.PlayingCard(suit, rank));
        return entity;
    }
}
