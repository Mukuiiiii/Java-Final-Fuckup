package unhappyEC;

import java.util.Map;
import java.util.HashMap;

public class Entity {
    private final Map<Class<? extends Component>, Component> componentList = new HashMap<>();
    private final Integer id;
    private final ECManager EC;

    boolean dead;

    Entity(ECManager EC, Integer id) {
        this.EC = EC;
        this.id = id;
    }

    public boolean hasComponent(Class<? extends Component> clazz) {
        return componentList.containsKey(clazz);
    }

    public Entity addComponent(Component component) {
       componentList.put(component.getClass(), component);
       component.setEntity(this);
        return this;
    }

    public int getId() {
        return id;
    }



}
