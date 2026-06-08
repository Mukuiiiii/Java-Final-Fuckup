package solitaire;

import component.CardFace;
import component.CardStack;
import component.PlayingCard;
import component.PlayingCard.Rank;
import component.PlayingCard.Suit;
import component.player;
import game.CardGameEngine;
import game.action.MoveCardAction;
import java.util.List;
import poker.PlayingCards;
import poker.PokerDeckFactory;
import unhappyEC.Entity;

public class KlondikeSolitaireGame {
    private final CardGameEngine engine;
    private final Entity board;

    public KlondikeSolitaireGame() {
        this(new CardGameEngine());
    }

    public KlondikeSolitaireGame(CardGameEngine engine) {
        this.engine = engine;
        this.board = engine.createDetachedPlayer();
        createStacks();
        new PokerDeckFactory(engine.getContext().getECManager()).createStandardDeckIn(board, "deck");
        for (Entity card : stack("deck").getCards()) {
            card.addComponent(new CardFace(false));
        }
        stack("deck").shuffle();
        dealTableau();
    }

    public Entity getBoard() {
        return board;
    }

    public void drawFromStock() {
        CardStack deck = stack("deck");
        if (deck.size() == 0) {
            recycleWaste();
            return;
        }

        Entity card = deck.Top();
        face(card).flipUp();
        move(card, "deck", "waste");
    }

    public boolean moveWasteToFoundation() {
        CardStack waste = stack("waste");
        if (waste.size() == 0) {
            return false;
        }
        return moveToFoundation(waste.Top(), "waste");
    }

    public boolean moveTableauToFoundation(int tableauIndex) {
        String from = tableauName(tableauIndex);
        CardStack tableau = stack(from);
        if (tableau.size() == 0 || !face(tableau.Top()).isFaceUp()) {
            return false;
        }
        boolean moved = moveToFoundation(tableau.Top(), from);
        flipTopTableau(tableauIndex);
        return moved;
    }

    public boolean moveWasteToTableau(int tableauIndex) {
        CardStack waste = stack("waste");
        if (waste.size() == 0) {
            return false;
        }
        return moveToTableau(waste.Top(), "waste", tableauIndex);
    }

    public boolean moveTableauToTableau(int fromIndex, int toIndex, int cardOffset) {
        String from = tableauName(fromIndex);
        List<Entity> cards = stack(from).getCards();
        if (cardOffset < 0 || cardOffset >= cards.size()) {
            return false;
        }

        Entity firstMovingCard = cards.get(cardOffset);
        if (!face(firstMovingCard).isFaceUp() || !canMoveSequence(cards, cardOffset)) {
            return false;
        }

        if (!canPlaceOnTableau(firstMovingCard, tableauName(toIndex))) {
            return false;
        }

        for (int i = cardOffset; i >= 0; i--) {
            move(cards.get(i), from, tableauName(toIndex));
        }
        flipTopTableau(fromIndex);
        return true;
    }

    public boolean isWon() {
        for (Suit suit : Suit.values()) {
            if (stack(foundationName(suit)).size() != 13) {
                return false;
            }
        }
        return true;
    }

    public List<Entity> stackCards(String stackName) {
        return stack(stackName).getCards();
    }

    private void createStacks() {
        engine.createStack(board, "waste");
        for (Suit suit : Suit.values()) {
            engine.createStack(board, foundationName(suit));
        }
        for (int i = 1; i <= 7; i++) {
            engine.createStack(board, tableauName(i));
        }
    }

    private void dealTableau() {
        for (int column = 1; column <= 7; column++) {
            for (int row = 1; row <= column; row++) {
                Entity card = stack("deck").Top();
                if (row == column) {
                    face(card).flipUp();
                }
                move(card, "deck", tableauName(column));
            }
        }
    }

    private void recycleWaste() {
        while (stack("waste").size() > 0) {
            Entity card = stack("waste").Top();
            face(card).flipDown();
            move(card, "waste", "deck");
        }
    }

    private boolean moveToFoundation(Entity card, String from) {
        PlayingCard playingCard = PlayingCards.card(card);
        String foundation = foundationName(playingCard.getSuit());
        if (!canPlaceOnFoundation(card, foundation)) {
            return false;
        }

        move(card, from, foundation);
        return true;
    }

    private boolean moveToTableau(Entity card, String from, int tableauIndex) {
        String to = tableauName(tableauIndex);
        if (!face(card).isFaceUp() || !canPlaceOnTableau(card, to)) {
            return false;
        }

        move(card, from, to);
        return true;
    }

    private boolean canPlaceOnFoundation(Entity card, String foundation) {
        PlayingCard playingCard = PlayingCards.card(card);
        CardStack stack = stack(foundation);
        if (stack.size() == 0) {
            return playingCard.getRank() == Rank.ACE;
        }

        PlayingCard top = PlayingCards.card(stack.Top());
        return top.getSuit() == playingCard.getSuit()
            && playingCard.getRank().getValue() == top.getRank().getValue() + 1;
    }

    private boolean canPlaceOnTableau(Entity card, String tableau) {
        PlayingCard playingCard = PlayingCards.card(card);
        CardStack stack = stack(tableau);
        if (stack.size() == 0) {
            return playingCard.getRank() == Rank.KING;
        }

        Entity topEntity = stack.Top();
        if (!face(topEntity).isFaceUp()) {
            return false;
        }

        PlayingCard top = PlayingCards.card(topEntity);
        return !PlayingCards.sameColor(playingCard, top)
            && playingCard.getRank().getValue() == top.getRank().getValue() - 1;
    }

    private boolean canMoveSequence(List<Entity> cards, int offset) {
        for (int i = 0; i <= offset; i++) {
            if (!face(cards.get(i)).isFaceUp()) {
                return false;
            }
        }

        for (int i = offset; i > 0; i--) {
            PlayingCard lower = PlayingCards.card(cards.get(i));
            PlayingCard upper = PlayingCards.card(cards.get(i - 1));
            if (PlayingCards.sameColor(lower, upper)
                || upper.getRank().getValue() != lower.getRank().getValue() - 1) {
                return false;
            }
        }
        return true;
    }

    private void flipTopTableau(int index) {
        CardStack tableau = stack(tableauName(index));
        if (tableau.size() > 0) {
            face(tableau.Top()).flipUp();
        }
    }

    private void move(Entity card, String from, String to) {
        new MoveCardAction(board, card, from, to).execute(engine.getContext());
    }

    private CardFace face(Entity card) {
        CardFace face = card.getComponent(CardFace.class);
        if (face == null) {
            face = new CardFace(true);
            card.addComponent(face);
        }
        return face;
    }

    private CardStack stack(String name) {
        return board.getComponent(player.class)
            .getStack(name)
            .getComponent(CardStack.class);
    }

    private String tableauName(int index) {
        if (index < 1 || index > 7) {
            throw new IllegalArgumentException("Tableau index must be 1..7.");
        }
        return "tableau" + index;
    }

    private String foundationName(Suit suit) {
        return "foundation" + suit.name();
    }
}
