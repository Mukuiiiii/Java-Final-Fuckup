package component;

import unhappyEC.Component;

public class Health extends Component{
    private Integer Health;

    Health(Integer Health) {
        this.Health = Health;
    }

    public Integer getHealth() {
        return Health;
    }
    public void uppHealth(Integer value) {
        Health += value;
    }

    public void downHealth(Integer value) {
        Health -= value;
    }

}
