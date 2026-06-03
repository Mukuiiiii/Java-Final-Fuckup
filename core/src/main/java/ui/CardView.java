package ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class CardView extends Group {
    public static final float WIDTH = 120;
    public static final float HEIGHT = 170;
    private boolean selected = false;
    private float baseY;
    private Runnable onClick;

    private static Texture cardTexture;

    public CardView(String name, int damage, int health, Label.LabelStyle style) {
        setSize(WIDTH, HEIGHT);
        setOrigin(WIDTH / 2f, HEIGHT / 2f);

        if (cardTexture == null) {
            cardTexture = createCardTexture();
        }

        Label nameLabel = new Label(name, style);
        nameLabel.setColor(Color.WHITE);
        nameLabel.setPosition(10, 135);

        Label damageLabel = new Label("ATK " + damage, style);
        damageLabel.setPosition(10, 12);

        Label healthLabel = new Label("HP " + health, style);
        healthLabel.setPosition(70, 12);

        addActor(nameLabel);
        addActor(damageLabel);
        addActor(healthLabel);

        addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!selected) {
                    baseY = getY();
                    setScale(1.1f);
                    setY(baseY + 20);
                }
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if (!selected) {
                    setScale(1f);
                    setY(baseY);
                }
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (onClick != null) {
                    onClick.run();
                }

                return true;
            }
        });


    }



    @Override
    protected void positionChanged() {
        super.positionChanged();

        if (!selected && getScaleY() == 1f) {
            baseY = getY();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(
            cardTexture,
            getX(),
            getY(),
            getOriginX(),
            getOriginY(),
            getWidth(),
            getHeight(),
            getScaleX(),
            getScaleY(),
            getRotation(),
            0,
            0,
            cardTexture.getWidth(),
            cardTexture.getHeight(),
            false,
            false
        );

        super.draw(batch, parentAlpha);
    }

    private static Texture createCardTexture() {
        Pixmap pixmap = new Pixmap((int) WIDTH, (int) HEIGHT, Pixmap.Format.RGBA8888);

        pixmap.setColor(0.18f, 0.2f, 0.24f, 1f);
        pixmap.fillRectangle(0, 0, (int) WIDTH, (int) HEIGHT);

        pixmap.setColor(0.85f, 0.78f, 0.55f, 1f);
        pixmap.drawRectangle(0, 0, (int) WIDTH, (int) HEIGHT);

        pixmap.setColor(0.1f, 0.12f, 0.15f, 1f);
        pixmap.fillRectangle(10, 35, 100, 85);

        Texture texture = new Texture(pixmap);
        pixmap.dispose();

        return texture;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;

        if (selected) {
            setScale(1.1f);
            setY(baseY + 30);
        } else {
            setScale(1f);
            setY(baseY);
        }
    }

    public boolean isSelected() {
        return selected;
    }

    public void setOnClick(Runnable onClick) {
        this.onClick = onClick;
    }
}
