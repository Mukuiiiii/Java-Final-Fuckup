package happy_factory;

import unhappyEC.Entity;
import component.BaseInfo;
import component.EffectComponent;
import java.util.function.Predicate;

public class Effect_Factory {
    public static EffectComponent createEffect(String triggerType, Predicate<Entity> condition, Runnable action) {
        return new EffectComponent(triggerType, condition, action);
    }

    public static Predicate<Entity> getCondition(String type, String param) {
        switch (type) {
            case "NAME_IS":
                return e -> e.has(BaseInfo.class) && e.getComponent(BaseInfo.class).getName().equals(param);
            case "HAS_COMPONENT":
                return e -> e.hasComponent(param);
            default:
                return e -> true;
        }
    }
}
