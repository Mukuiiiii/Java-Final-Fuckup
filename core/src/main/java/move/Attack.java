package move;


import component.Damage;
import component.Health;
import unhappyEC.Component;
import unhappyEC.Entity;


public class Attack extends Move {

    public static void baseAttack(Entity target, Integer value) {
        target.getComponent(Health.class).downHealth(value);
    }






}
