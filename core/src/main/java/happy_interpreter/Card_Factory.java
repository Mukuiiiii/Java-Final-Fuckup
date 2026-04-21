package happy_interpreter;

import unhappyEC.ECManager;
import unhappyEC.Entity;
import component.*;

public class Card_Factory {
    private final ECManager EC;
    private final Load_Yaml loader;

    Card_Factory(ECManager EC) {
        this.loader = new Load_Yaml();
        this.EC = EC;
    }




    public Entity terp(String cardId) {
        Map<String, Object> data = loader.load("CardInformation", cardId);

        if (data == null) return null;
        /**
         * I write a simple one first
        entity et = new entity(cardId);
        construct(et, data);
        return et;
         */
    }

    private void construct(Map<> data) {
        Entity entity = EC.newEntity();
        if (data.containsKey("name")) {
            entity.addComponent(BaseInfo("abababab", "ajdsfjklsd"));
        }
        if (data.containsKey("Health")) {
            entity.addComponent(Health(date.get("health").intValue()));
        }
//        感覺應該要有更好的做法，不然一個一個一個if-else加太蠢了
    }

//    private void construct(entity entity, Map<String, Object> data) {
//        if (data.containsKey("name")) {
//            EC.setName(entity, (String) data.get("name"));
//        }
//        if (data.containsKey("damage")) {
//            int dmg = ((Number) data.get("damage")).intValue();
//            EC.setDamage(entity, dmg);
//        }
//        if (data.containsKey("manaCost")) {
//            int cost = ((Number) data.get("manaCost")).intValue();
//            EC.setManaCost(entity, cost);
//        }
//        if (data.containsKey("triggers")) {
//            Map<String, Object> triggers = (Map<String, Object>) data.get("triggers");
//            for (String eventType : triggers.keySet()) {
//                EC.attachTrigger(entity, eventType, triggers.get(eventType));
//            }
//        }
//    }
}
