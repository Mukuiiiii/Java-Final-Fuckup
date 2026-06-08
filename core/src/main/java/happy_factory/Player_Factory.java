package happy_factory;


import component.CardStack;
import unhappyEC.Entity;
import unhappyEC.ECManager;

import component.player;

public class Player_Factory {
    private final ECManager EC;
    public Player_Factory(ECManager EC) {
        this.EC = EC;
    }

    public Entity createPlayer() {
        Entity playerEntity = EC.newEntity();
        player playerComponent = new player();
        playerEntity.addComponent(playerComponent);

        addStack(playerEntity, playerComponent, "hand");
        addStack(playerEntity, playerComponent, "deck");
        addStack(playerEntity, playerComponent, "grave");

        return playerEntity;
    }

    private void addStack(Entity owner, player playerComponent, String name) {
        Entity stackEntity = EC.newEntity();
        stackEntity.addComponent(new CardStack(owner, stackEntity, name));
        playerComponent.addStack(stackEntity);
    }


}
