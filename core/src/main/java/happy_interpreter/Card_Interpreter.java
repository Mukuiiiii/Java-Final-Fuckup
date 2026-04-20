package happy_interpreter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import org.yaml.snakeyaml.Yaml;
import unhappy_EC.*;
import java.util.Map;

public class Card_Interpreter {
    private final Yaml yaml;
    private final entity_control ec;

    public Card_Interpreter() {
        this.yaml = new Yaml();
        this.ec = new entity_control();

    }

    public entity terp(String cardId) {
        FileHandle file = Gdx.files.internal("CardInformation/" + cardId + ".yaml");
        if (!file.exists()) {
            Gdx.app.error("Interpreter", "找不到檔案: " + cardId);
            return null;
        }

        Map<String, Object> data = yaml.load(file.read());
        /**
         * wait for entity
         entity et = new entity(carfID);
         interpreter(et, data);
         return et;
         */
    }

    private void interpreter(entity entity, Map<String, Object> data) {
        /**
         * explain the key of card. More will be add in future. complete will also start in future
         */
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
