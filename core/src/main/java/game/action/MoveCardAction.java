package game.action;

import component.CardStack;
import component.player;
import game.GameContext;
import listeners.Event.CardMoved;
import unhappyEC.Entity;

public class MoveCardAction implements GameAction {
    private final Entity owner;
    private final Entity card;
    private final String from;
    private final String to;

    public MoveCardAction(Entity owner, Entity card, String from, String to) {
        this.owner = owner;
        this.card = card;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(GameContext context) {
        player playerComponent = owner.getComponent(player.class);
        CardStack fromStack = playerComponent.getStack(from).getComponent(CardStack.class);
        CardStack toStack = playerComponent.getStack(to).getComponent(CardStack.class);

        if (!fromStack.removeCard(card)) {
            throw new IllegalStateException("Card is not in stack: " + from);
        }

        toStack.insert2Top(card);
        context.getEventManager().post(new CardMoved(card, owner, from, to));
    }
}
