package happy_interpreter;

import unhappy_EC.*;
import java.util.Map;

public class Card_Interpreter {
    private final entity_control ec;
    private final Load_Yaml loader;

    public Card_Interpreter() {
        this.ec = new entity_control();
        this.loader = new Load_Yaml();
    }

    public entity terp(String cardId) {
        Map<String, Object> data = loader.load("CardInformation", cardId);

        if (data == null) return null;
        /**
         * I write a simple one first
        entity et = new entity(cardId);
        construct(et, data);
        return et;
         */
    }

    private void construct(entity entity, Map<String, Object> data) {
        if (data.containsKey("name")) {
            ec.setName(entity, (String) data.get("name"));
        }
        if (data.containsKey("damage")) {
            int dmg = ((Number) data.get("damage")).intValue();
            ec.setDamage(entity, dmg);
        }
        if (data.containsKey("manaCost")) {
            int cost = ((Number) data.get("manaCost")).intValue();
            ec.setManaCost(entity, cost);
        }
        if (data.containsKey("triggers")) {
            Map<String, Object> triggers = (Map<String, Object>) data.get("triggers");
            for (String eventType : triggers.keySet()) {
                ec.attachTrigger(entity, eventType, triggers.get(eventType));
            }
        }
    }
}
