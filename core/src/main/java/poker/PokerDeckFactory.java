package poker;

import component.CardStack;
import component.PlayingCard;
import component.PlayingCard.Rank;
import component.PlayingCard.Suit;
import component.player;
import java.util.ArrayList;
import java.util.List;
import unhappyEC.ECManager;
import unhappyEC.Entity;

public class PokerDeckFactory {
    private final ECManager ecManager;

    public PokerDeckFactory(ECManager ecManager) {
        this.ecManager = ecManager;
    }

    public List<Entity> createStandardDeck() {
        ArrayList<Entity> cards = new ArrayList<>();

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                Entity card = ecManager.newEntity();
                card.addComponent(new PlayingCard(suit, rank));
                cards.add(card);
            }
        }

        return cards;
    }

    public List<Entity> createStandardDeckIn(Entity owner, String stackName) {
        List<Entity> cards = createStandardDeck();
        CardStack stack = owner.getComponent(player.class)
            .getStack(stackName)
            .getComponent(CardStack.class);

        for (Entity card : cards) {
            stack.insertCard(card);
        }

        return cards;
    }
}
