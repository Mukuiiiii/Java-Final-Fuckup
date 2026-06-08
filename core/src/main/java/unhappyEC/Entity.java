package unhappyEC;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

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
        if (componentList.containsKey(clazz)) {
            return true;
        }

        return componentList.keySet().stream()
            .anyMatch(clazz::isAssignableFrom);
    }

    public boolean hasComponent(String componentName) {
        return componentList.keySet().stream()
            .anyMatch(componentClass ->
                componentClass.getSimpleName().equals(componentName)
                    || componentClass.getName().equals(componentName)
            );
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
        if (component == null) {
            component = componentList.entrySet().stream()
                .filter(entry -> componentClass.isAssignableFrom(entry.getKey()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
        }

        return componentClass.cast(component);
    }

    public <T extends Component> List<T> getComponents(Class<T> componentClass) {
        ArrayList<T> components = new ArrayList<>();

        for (Component component : componentList.values()) {
            if (componentClass.isInstance(component)) {
                components.add(componentClass.cast(component));
            }
        }

        return components;
    }

}
