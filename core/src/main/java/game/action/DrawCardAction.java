package game.action;

import component.CardStack;
import component.player;
import game.GameContext;
import listeners.Event.AfterDrawCard;
import listeners.Event.BeforeDrawCard;
import unhappyEC.Entity;

public class DrawCardAction implements GameAction {
    private final Entity playerEntity;
    private final String from;
    private final String to;

    public DrawCardAction(Entity playerEntity) {
        this(playerEntity, "deck", "hand");
    }

    public DrawCardAction(Entity playerEntity, String from, String to) {
        this.playerEntity = playerEntity;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(GameContext context) {
        context.getEventManager().post(new BeforeDrawCard(playerEntity, from, to));

        CardStack fromStack = playerEntity.getComponent(player.class)
            .getStack(from)
            .getComponent(CardStack.class);

        if (fromStack.size() == 0) {
            throw new IllegalStateException("Cannot draw from empty stack: " + from);
        }

        Entity card = fromStack.Top();
        new MoveCardAction(playerEntity, card, from, to).execute(context);

        context.getEventManager().post(new AfterDrawCard(playerEntity, card, from, to));
    }
}
