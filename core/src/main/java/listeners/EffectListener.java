package listeners;

import component.EffectComponent;
import unhappyEC.Entity;

public class EffectListener extends Listener<Events> {
    private final EffectComponent effect;

    public EffectListener(EffectComponent effect) {
        super(Events.class);
        this.effect = effect;
    }

    @Override
    public void onEvent(Events event) {
        if (!matchesTrigger(event)) {
            return;
        }

        Entity owner = effect.getEntity();
        if (effect.condition != null && !effect.condition.test(owner)) {
            return;
        }

        if (effect.action != null) {
            effect.action.run();
        }
    }

    private boolean matchesTrigger(Events event) {
        if (effect.triggerType == null || effect.triggerType.isBlank()) {
            return true;
        }

        Class<?> eventClass = event.getClass();
        return effect.triggerType.equals(eventClass.getSimpleName())
            || effect.triggerType.equals(eventClass.getName());
    }
}
