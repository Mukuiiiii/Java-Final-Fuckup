package listeners.Event;

import listeners.Events;
import unhappyEC.Entity;

public class Drawcard extends Events {
    private final Entity player;
    private final Entity card;
    private final String from;
    private final String to;

    public Drawcard() {
        this(null, null, null, null);
    }

    public Drawcard(Entity player, Entity card, String from, String to) {
        this.player = player;
        this.card = card;
        this.from = from;
        this.to = to;
    }

    public Entity getPlayer() {
        return player;
    }

    public Entity getCard() {
        return card;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
