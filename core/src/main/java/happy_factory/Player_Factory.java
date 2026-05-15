package happy_factory;


import component.Zone;
import unhappyEC.Component;
import unhappyEC.Entity;
import unhappyEC.ECManager;

import component.player;

public class Player_Factory {
    private final ECManager EC;
    Player_Factory(ECManager EC) {
        this.EC = EC;
    }

    public Entity createPlayer() {
        Entity player = EC.newEntity();
        player.addComponent(new player());
        player.addComponent(new Zone(player, "hand"));
        player.addComponent(new Zone(player, "deck"));
        player.addComponent(new Zone(player, "grave"));
        return player;
    }

//    public void


}
