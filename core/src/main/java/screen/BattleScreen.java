package screen;

import ZaWarudo.MainGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ui.HandArea;

public class BattleScreen implements Screen {
    private final MainGame game;
    private Stage stage;

    public BattleScreen(MainGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        HandArea handArea = new HandArea();
        handArea.setPosition(80, 30);
        stage.addActor(handArea);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.08f, 0.1f, 0.13f, 1f);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
    }
}
