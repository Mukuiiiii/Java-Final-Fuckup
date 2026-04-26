package component;

import unhappyEC.Entity;
import unhappyEC.Component;

import java.util.Collections;
import java.util.LinkedList;

public class Zone extends Component {
    private final  LinkedList<Entity> cards = new LinkedList<>();
    private final Entity owner;
    private final  String name;
    private final Entity ZoneSelf;


    Zone(Entity owner, String name) {
//        super(owner);
        this.owner = owner;
        this.name = name;
        this.ZoneSelf = owner.getECManager().newEntity();
    }

    public void insertCard(Entity e) {
        cards.add(e);
        return;
    }
    public void insert2Top(Entity e) {
        cards.addFirst(e);
        return;
    }
    public void insert2Bottom(Entity e) {
        cards.addLast(e);
        return;
    }

    public Entity getTop() {
        return cards.getFirst();
    }
    public Entity getBottom() {
        return cards.getLast();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Integer size() {
        return cards.size();
    }

}
