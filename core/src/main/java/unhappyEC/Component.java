package unhappyEC;

public abstract class Component {

    private Entity entity;

    void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

}
