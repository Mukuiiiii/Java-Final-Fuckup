package happy_interpreter;


import unhappyEC.Entity;
import unhappyEC.ECManager;

public class Player_Factory extends Entity{
    private ECManager EC;
    Player_Factory(ECManager EC) {
        super();
        this.EC = EC;
    }
}
