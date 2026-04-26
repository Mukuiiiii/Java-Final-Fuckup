package move;

import unhappyEC.Entity;

public class BaseAttack extends Base {

    /**
     *  簡單得攻擊演示，指是Entity A 對 Entity B 的攻擊作用
     * @param attacker 開始攻擊的實體
     * @param defender 受到攻擊的實體
     */
    public static void pureAttack(Entity attacker, Entity defender) {
        Integer tmpHP = attacker.getAttribute("")-defender.getAttribute("HorsePower");
        defender.setAttribute("HorsePower",tmpHP);
        return;
    }
    


    /**
     *  簡單的群攻演示，指是Entity A 對一群敵人的攻擊作用
     * @param attacker 開始攻擊的實體
     * @param defender 受到攻擊的實體們
     */
    public static void AllDamage(Entity attacker, Entity[] defender) {
        for (var it: defender) {
            Integer tmpHP = it.getAttribute("HorsePower") - attacker.getAttribute("HorsePower");
            it.setAttribute("HorsePower",tmpHP);
        }
    }


}
