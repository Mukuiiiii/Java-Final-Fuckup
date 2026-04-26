package happy_factory;


import unhappyEC.Entity;
import unhappyEC.ECManager;

import component.player;

public class Player_Factory {
    private final ECManager EC;
    Player_Factory(ECManager EC) {
        this.EC = EC;
    }

    public void creatPlayer() {
        Entity player = EC.newEntity();
        player.addComponent(new player());

    }

//    public void


}
