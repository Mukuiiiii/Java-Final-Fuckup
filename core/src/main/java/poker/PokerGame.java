package poker;

import component.CardStack;
import component.player;
import game.CardGameEngine;
import game.GameContext;
import game.action.TransferCardAction;
import java.util.ArrayList;
import java.util.List;
import unhappyEC.Entity;

public class PokerGame {
    private final CardGameEngine engine;
    private final PokerDeckFactory deckFactory;
    private final PokerHandEvaluator handEvaluator = new PokerHandEvaluator();
    private final Entity dealer;
    private final List<Entity> players = new ArrayList<>();

    public PokerGame() {
        this(new CardGameEngine());
    }

    public PokerGame(CardGameEngine engine) {
        this.engine = engine;
        GameContext context = engine.getContext();
        this.deckFactory = new PokerDeckFactory(context.getECManager());
        this.dealer = engine.createDetachedPlayer();
        deckFactory.createStandardDeckIn(dealer, "deck");
        dealerDeck().shuffle();
    }

    public CardGameEngine getEngine() {
        return engine;
    }

    public Entity addPlayer() {
        Entity player = engine.createPlayer();
        players.add(player);
        return player;
    }

    public List<Entity> getPlayers() {
        return new ArrayList<>(players);
    }

    public void shuffleDeck() {
        dealerDeck().shuffle();
    }

    public void deal(int cardsPerPlayer) {
        for (int i = 0; i < cardsPerPlayer; i++) {
            for (Entity player : players) {
                dealOne(player);
            }
        }
    }

    public void dealOne(Entity player) {
        CardStack deck = dealerDeck();
        if (deck.size() == 0) {
            throw new IllegalStateException("Dealer deck is empty.");
        }

        Entity card = deck.Top();
        new TransferCardAction(dealer, player, card, "deck", "hand").execute(engine.getContext());
    }

    public List<Entity> hand(Entity player) {
        return handStack(player).getCards();
    }

    public PokerHandValue evaluateHand(Entity player) {
        return handEvaluator.evaluate(hand(player));
    }

    public Entity winner() {
        if (players.isEmpty()) {
            return null;
        }

        Entity winner = players.get(0);
        PokerHandValue best = evaluateHand(winner);
        for (int i = 1; i < players.size(); i++) {
            Entity player = players.get(i);
            PokerHandValue value = evaluateHand(player);
            if (value.compareTo(best) > 0) {
                best = value;
                winner = player;
            }
        }
        return winner;
    }

    private CardStack dealerDeck() {
        return dealer.getComponent(player.class)
            .getStack("deck")
            .getComponent(CardStack.class);
    }

    private CardStack handStack(Entity player) {
        return player.getComponent(player.class)
            .getStack("hand")
            .getComponent(CardStack.class);
    }
}
