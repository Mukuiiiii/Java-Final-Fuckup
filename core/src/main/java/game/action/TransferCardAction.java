package game.action;

import component.CardStack;
import component.player;
import game.GameContext;
import listeners.Event.CardMoved;
import unhappyEC.Entity;

public class TransferCardAction implements GameAction {
    private final Entity fromOwner;
    private final Entity toOwner;
    private final Entity card;
    private final String from;
    private final String to;

    public TransferCardAction(Entity fromOwner, Entity toOwner, Entity card, String from, String to) {
        this.fromOwner = fromOwner;
        this.toOwner = toOwner;
        this.card = card;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(GameContext context) {
        CardStack fromStack = fromOwner.getComponent(player.class)
            .getStack(from)
            .getComponent(CardStack.class);
        CardStack toStack = toOwner.getComponent(player.class)
            .getStack(to)
            .getComponent(CardStack.class);

        if (!fromStack.removeCard(card)) {
            throw new IllegalStateException("Card is not in stack: " + from);
        }

        toStack.insert2Top(card);
        context.connectEffects(card);
        context.getEventManager().post(new CardMoved(card, toOwner, from, to));
    }
}
