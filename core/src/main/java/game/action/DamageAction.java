package game.action;

import component.Health;
import game.GameContext;
import listeners.Event.AfterDamage;
import listeners.Event.BeforeDamage;
import unhappyEC.Entity;

public class DamageAction implements GameAction {
    private final Entity source;
    private final Entity target;
    private final int value;

    public DamageAction(Entity source, Entity target, int value) {
        this.source = source;
        this.target = target;
        this.value = value;
    }

    @Override
    public void execute(GameContext context) {
        context.getEventManager().post(new BeforeDamage(source, target, value));

        Health health = target.getComponent(Health.class);
        if (health == null) {
            throw new IllegalStateException("Damage target does not have Health component.");
        }

        health.downHealth(value);
        context.getEventManager().post(new AfterDamage(source, target, value, health.getHealth()));
    }
}
