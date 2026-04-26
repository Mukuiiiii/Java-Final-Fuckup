package happy_factory.Parsers;

import component.Damage;
import unhappyEC.Entity;

public class DamageParser implements ComponentParser {
    @Override
    public void parseAndAdd(Entity entity, Object data) {
        Integer damage = ((Number) data).intValue();
        entity.addComponent(new Damage(damage));
    }
}
