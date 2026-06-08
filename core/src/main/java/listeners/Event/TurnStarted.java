package listeners.Event;

import listeners.Events;
import unhappyEC.Entity;

public class TurnStarted extends Events {
    private final Entity player;
    private final int turnNumber;

    public TurnStarted(Entity player, int turnNumber) {
        this.player = player;
        this.turnNumber = turnNumber;
    }

    public Entity getPlayer() {
        return player;
    }

    public int getTurnNumber() {
        return turnNumber;
    }
}
