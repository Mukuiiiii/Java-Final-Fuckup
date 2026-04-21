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
        Entity e = new Entity(this,  IDconuter++);
        enties.put(IDconuter, e);
        return e;
    }

}
