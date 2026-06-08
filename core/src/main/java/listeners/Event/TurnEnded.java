package listeners.Event;

import unhappyEC.Entity;

public class TurnEnded extends TurnStarted {
    public TurnEnded(Entity player, int turnNumber) {
        super(player, turnNumber);
    }
}
