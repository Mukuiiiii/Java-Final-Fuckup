package happy_interpreter.Parsers;

import component.Health;
import unhappyEC.Entity;

public class HealthParser implements ComponentParser {
    @Override
    public void parseAndAdd(Entity entity, Object data) {
        Integer health = ((Number) data).intValue();
        entity.addComponent(new Health(health));
    }
}
