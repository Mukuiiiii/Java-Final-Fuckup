package move;

import component.Damage;
import component.Health;
import unhappyEC.Entity;

public class BaseAttack {
    public static void pureAttack(Entity attacker, Entity defender) {
        if (!attacker.hasComponent(Damage.class) || !defender.hasComponent(Health.class)) {
            return;
        }

        int damage = attacker.getComponent(Damage.class).getDamage();
        defender.getComponent(Health.class).downHealth(damage);
    }

    public static void allDamage(Entity attacker, Entity[] defenders) {
        if (!attacker.hasComponent(Damage.class)) {
            return;
        }

        int damage = attacker.getComponent(Damage.class).getDamage();
        for (Entity defender : defenders) {
            if (defender.hasComponent(Health.class)) {
                defender.getComponent(Health.class).downHealth(damage);
            }
        }
    }
}
