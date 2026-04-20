package UnhappyEC;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Entity {
    private final Map<Class<? extends Component>, Component> components = new HashMap<>();
    private final Integer id;
    private final EntityControler EC;

    boolean dead;


    Entity(EntityControler EC, Integer id) {
        this.EC = EC;
        this.id = id;
    }


    public boolean hasComponent(Class<? extends Component> clazz) {
        return  components.containsKey(clazz);
    }



    public Entity addComponent(Class<? extends Component> clazz) {

    }

    public int getId() {
        return id;
    }



}
