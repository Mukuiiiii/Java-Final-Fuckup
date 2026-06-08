package listeners.Event;

import listeners.Events;
import unhappyEC.Entity;

public class CardMoved extends Events {
    private final Entity card;
    private final Entity owner;
    private final String from;
    private final String to;

    public CardMoved(Entity card, Entity owner, String from, String to) {
        this.card = card;
        this.owner = owner;
        this.from = from;
        this.to = to;
    }

    public Entity getCard() {
        return card;
    }

    public Entity getOwner() {
        return owner;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
