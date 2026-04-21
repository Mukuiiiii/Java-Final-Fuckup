package happy_interpreter;

import unhappyEC.ECManager;
import unhappyEC.Entity;
import component.*;
import happy_interpreter.Parsers.*;
import java.util.HashMap;
import java.util.Map;

public class Card_Factory {
    private final ECManager EC;
    private final Load_Yaml loader;
    private final Map<String, ComponentParser> registry = new HashMap<>();

    public Card_Factory(ECManager EC) {
        this.loader = new Load_Yaml();
        this.EC = EC;
        setupRegistry();
    }

    private void setupRegistry() {
        registry.put("BaseInfo", new BaseInfoParser());
        registry.put("Health", new HealthParser());
        registry.put("Damage", new DamageParser());
        // add more
    }


    public Entity terp(String cardId) {
        Map<String, Object> data = loader.load("CardInformation", cardId);

        if (data == null) return null;

        Entity et = EC.newEntity();
        construct(et, data);
        return et;
    }

    private void construct(final Entity entity, Map<String, Object> data) {
        data.forEach((key, value) -> {
            ComponentParser parser = registry.get(key);
            parser.parseAndAdd(entity, value);
        });
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
