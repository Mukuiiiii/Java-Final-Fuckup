package unhappyEC;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ECManager {

    private final Map<Integer, Entity> enties = new ConcurrentHashMap<>();
    private Integer IDconuter = 0;

    public ECManager() {
        //       init
    }

    public Entity newEntity() {
        int currentId = IDconuter++;
        Entity e = new Entity(this, currentId);
        enties.put(currentId, e);
        return e;
    }
    public Entity getEntity(Integer id) {
        return enties.get(id);
    }

    public ArrayList<Entity> EntitiesWithComponents(Class<? extends Component> clazz) {
        return enties.values().stream()
            .filter(e -> e.has(clazz))
            .collect(Collectors.toCollection(ArrayList::new));
    }
}
