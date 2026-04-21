package happy_interpreter.Parsers;

import component.Damage;
import component.Health;
import unhappyEC.Entity;

public class DamageParser implements ComponentParser {
    @Override
    public void parseAndAdd(Entity entity, Object data) {
        Integer damage = ((Number) data).intValue();
        entity.addComponent(new Damage(damage));
    }
}
