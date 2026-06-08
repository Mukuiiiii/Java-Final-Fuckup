package poker;

import component.PlayingCard;
import component.PlayingCard.Suit;
import unhappyEC.Entity;

public final class PlayingCards {
    private PlayingCards() {
    }

    public static PlayingCard card(Entity entity) {
        PlayingCard card = entity.getComponent(PlayingCard.class);
        if (card == null) {
            throw new IllegalArgumentException("Entity does not have PlayingCard component.");
        }
        return card;
    }

    public static boolean isRed(PlayingCard card) {
        return card.getSuit() == Suit.DIAMONDS || card.getSuit() == Suit.HEARTS;
    }

    public static boolean sameColor(PlayingCard first, PlayingCard second) {
        return isRed(first) == isRed(second);
    }

    public static int redDotScore(PlayingCard card) {
        if (card.getSuit() == Suit.SPADES && card.getRank().getValue() == 14) {
            return 30;
        }

        if (card.getSuit() == Suit.CLUBS && card.getRank().getValue() == 2) {
            return 20;
        }

        if (!isRed(card)) {
            return 0;
        }

        int value = card.getRank().getValue();
        if (value >= 10) {
            return 10;
        }
        return value;
    }
}
