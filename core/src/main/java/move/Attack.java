package move;


import component.Damage;
import component.Health;
import game.GameContext;
import game.action.DamageAction;
import unhappyEC.Entity;


public class Attack extends Move {

    public static void baseAttack(Entity target, Integer value) {
        target.getComponent(Health.class).downHealth(value);
    }

    public static void baseAttack(GameContext context, Entity source, Entity target, Integer value) {
        new DamageAction(source, target, value).execute(context);
    }

    public static void baseAttack(GameContext context, Entity source, Entity target) {
        Integer value = source.getComponent(Damage.class).getDamage();
        baseAttack(context, source, target, value);
    }





}
