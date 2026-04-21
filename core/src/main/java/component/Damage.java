package component;

import unhappyEC.Component;

public class Damage extends Component {
    private Integer Damage;

    public Damage(Integer Damage) { this.Damage = Damage; }

    public Integer getDamage() { return Damage; }
    public void strength(Integer value) { Damage += value; }

    public void weak(Integer value) { Damage -= value; }


}
