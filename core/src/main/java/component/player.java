package component;

import unhappyEC.Component;
import unhappyEC.Entity;

import java.util.HashMap;
import java.util.Map;

public class player extends Component {
    private final Map<String, Entity> stacks = new HashMap<>();

    public void addStack(Entity e) {
        CardStack stack = e.getComponent(CardStack.class);
        stacks.put(stack.name(), e);
    }
    public Entity getStack(String name) {
        return stacks.get(name);
    }
}
