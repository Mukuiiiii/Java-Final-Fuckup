package solitaire;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class KlondikeSolitaireGameSmokeTest {
    @Test
    void dealsKlondikeLayout() {
        KlondikeSolitaireGame game = new KlondikeSolitaireGame();

        assertEquals(24, game.stackCards("deck").size());
        assertEquals(0, game.stackCards("waste").size());
        for (int i = 1; i <= 7; i++) {
            assertEquals(i, game.stackCards("tableau" + i).size());
        }
    }

    @Test
    void drawsFromStockToWaste() {
        KlondikeSolitaireGame game = new KlondikeSolitaireGame();

        game.drawFromStock();

        assertEquals(23, game.stackCards("deck").size());
        assertEquals(1, game.stackCards("waste").size());
    }
}
