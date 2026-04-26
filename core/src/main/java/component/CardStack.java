package component;

import unhappyEC.Entity;
import unhappyEC.Component;

import java.util.Collections;
import java.util.LinkedList;

public class CardStack extends Component {
    private final LinkedList<Entity> cards = new LinkedList<>();
    private final Entity owner;
    private final String name;
    private final Entity self;


    CardStack(Entity owner, Entity self, String name) {
        this.owner = owner;
        this.name = name;
        this.self = self;
    }

    public void insertCard(Entity e) {
        cards.add(e);
    }
    public void insert2Top(Entity e) {
        cards.addFirst(e);
    }
    public void insert2Bottom(Entity e) {
        cards.addLast(e);
        return;
    }
    public void popFromTop() {
        cards.removeFirst();
    }
    public void popFromBottom() {
        cards.removeLast();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Entity Top() {
        return cards.getFirst();
    }
    public Entity Bottom() {
        return cards.getLast();
    }
    public Integer size() {
        return cards.size();
    }

    public String name() {
        return this.name;
    }
    public Entity self() {
        return this.self;
    }
    public Entity Owner() {
        return this.owner;
    }
}
