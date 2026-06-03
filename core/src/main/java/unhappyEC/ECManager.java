package unhappyEC;


import java.util.HashMap;
import java.util.Map;

public class ECManager {

    private final Map<Integer, Entity> enties = new HashMap<>();
//    unsafe for mulit player or client
    private Integer IDconuter = 0;

    ECManager() {

        //       init
    }

    public Entity newEntity() {
        int currentId = IDconuter++;
        Entity e = new Entity(this, currentId);
        enties.put(currentId, e);
        return e;
    }

}
