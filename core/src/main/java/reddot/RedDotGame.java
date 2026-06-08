package reddot;

import component.CardStack;
import component.PlayingCard;
import component.player;
import game.CardGameEngine;
import game.action.TransferCardAction;
import java.util.ArrayList;
import java.util.List;
import poker.PlayingCards;
import poker.PokerDeckFactory;
import unhappyEC.Entity;

public class RedDotGame {
    private final CardGameEngine engine;
    private final Entity dealer;
    private final Entity table;
    private final List<Entity> players = new ArrayList<>();

    public RedDotGame() {
        this(new CardGameEngine());
    }

    public RedDotGame(CardGameEngine engine) {
        this.engine = engine;
        this.dealer = engine.createDetachedPlayer();
        this.table = engine.createDetachedPlayer();
        engine.createStack(table, "table");
        new PokerDeckFactory(engine.getContext().getECManager()).createStandardDeckIn(dealer, "deck");
        deck().shuffle();
    }

    public Entity addPlayer() {
        Entity player = engine.createPlayer();
        engine.createStack(player, "capture");
        players.add(player);
        return player;
    }

    public void start(int cardsPerPlayer, int tableCards) {
        for (int i = 0; i < cardsPerPlayer; i++) {
            for (Entity player : players) {
                dealTo(player, "hand");
            }
        }

        for (int i = 0; i < tableCards; i++) {
            dealTo(table, "table");
        }
    }

    public void play(Entity player, Entity handCard, Entity tableCard) {
        if (tableCard != null && !sameRank(handCard, tableCard)) {
            throw new IllegalArgumentException("Red dot capture needs matching ranks.");
        }

        if (tableCard == null) {
            move(player, table, handCard, "hand", "table");
        } else {
            move(player, player, handCard, "hand", "capture");
            move(table, player, tableCard, "table", "capture");
        }

        drawAndResolve(player);
    }

    public void drawAndResolve(Entity player) {
        if (deck().size() == 0) {
            return;
        }

        Entity drawn = deck().Top();
        Entity match = firstTableMatch(drawn);
        if (match == null) {
            move(dealer, table, drawn, "deck", "table");
            return;
        }

        move(dealer, player, drawn, "deck", "capture");
        move(table, player, match, "table", "capture");
    }

    public List<Entity> hand(Entity player) {
        return stack(player, "hand").getCards();
    }

    public List<Entity> tableCards() {
        return stack(table, "table").getCards();
    }

    public List<Entity> captured(Entity player) {
        return stack(player, "capture").getCards();
    }

    public int score(Entity player) {
        int score = 0;
        for (Entity card : captured(player)) {
            score += PlayingCards.redDotScore(PlayingCards.card(card));
        }
        return score;
    }

    public boolean isRoundOver() {
        if (deck().size() > 0) {
            return false;
        }

        for (Entity player : players) {
            if (stack(player, "hand").size() > 0) {
                return false;
            }
        }
        return true;
    }

    private void dealTo(Entity target, String stackName) {
        if (deck().size() == 0) {
            throw new IllegalStateException("Deck is empty.");
        }
        move(dealer, target, deck().Top(), "deck", stackName);
    }

    private Entity firstTableMatch(Entity card) {
        for (Entity tableCard : tableCards()) {
            if (sameRank(card, tableCard)) {
                return tableCard;
            }
        }
        return null;
    }

    private boolean sameRank(Entity first, Entity second) {
        PlayingCard firstCard = PlayingCards.card(first);
        PlayingCard secondCard = PlayingCards.card(second);
        return firstCard.getRank() == secondCard.getRank();
    }

    private void move(Entity fromOwner, Entity toOwner, Entity card, String from, String to) {
        new TransferCardAction(fromOwner, toOwner, card, from, to).execute(engine.getContext());
    }

    private CardStack deck() {
        return stack(dealer, "deck");
    }

    private CardStack stack(Entity owner, String name) {
        return owner.getComponent(player.class)
            .getStack(name)
            .getComponent(CardStack.class);
    }
}
