package listeners.Event;

import unhappyEC.Entity;

public class BeforeDrawCard extends Drawcard {
    public BeforeDrawCard(Entity player, String from, String to) {
        super(player, null, from, to);
    }
}
