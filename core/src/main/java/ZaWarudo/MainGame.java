package ZaWarudo;

import com.badlogic.gdx.Game;
import screen.BattleScreen;

public class MainGame extends Game {
    @Override
    public void create() {
        setScreen(new BattleScreen(this));
    }
}
