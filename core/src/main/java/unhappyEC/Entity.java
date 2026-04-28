package unhappyEC;

import java.util.Map;
import java.util.HashMap;

public class Entity {
    private final Map<Class<? extends Component>, Component> componentList = new HashMap<>();
    private final Map<Class<? extends Component>, Component> moveList = new HashMap<>();

    private final Integer id;
    private final ECManager EC;

    boolean dead;

    protected Entity(ECManager EC, Integer id) {
        this.EC = EC;
        this.id = id;
    }

    public boolean has(Class<? extends Component> clazz) {
        return componentList.containsKey(clazz);
    }

    public void addComponent(Component component) {
       componentList.put(component.getClass(), component);
       component.setEntity(this);
    }

    public int getId() {
        return id;
    }

    public ECManager getECManager() {
        return EC;
    }

    public <T extends Component> T getComponent(Class<T> componentClass) {
        Component component = componentList.get(componentClass);
        return componentClass.cast(component);
    }

}
