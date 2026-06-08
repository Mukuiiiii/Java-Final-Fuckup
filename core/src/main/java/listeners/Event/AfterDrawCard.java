package listeners.Event;

import unhappyEC.Entity;

public class AfterDrawCard extends Drawcard {
    public AfterDrawCard(Entity player, Entity card, String from, String to) {
        super(player, card, from, to);
    }
}
