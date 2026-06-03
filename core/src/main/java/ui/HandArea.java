package ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class HandArea extends Group {

    private CardView selectedCard;

    public HandArea() {
        Label.LabelStyle style = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        for (int i = 0; i < 5; i++) {
            CardView card = new CardView("Test Card", 3, 5, style);
            card.setOnClick(() -> selectCard(card));
            card.setPosition(i * 135, 0);
            addActor(card);
        }

        setSize(700, CardView.HEIGHT);
    }

    private void selectCard(CardView card) {
        if (selectedCard == card) {
            card.setSelected(false);
            selectedCard = null;
            return;
        }

        if (selectedCard != null) {
            selectedCard.setSelected(false);
        }

        selectedCard = card;
        selectedCard.setSelected(true);
    }

}
