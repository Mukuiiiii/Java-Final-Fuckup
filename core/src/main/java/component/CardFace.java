package component;

import unhappyEC.Component;

public class CardFace extends Component {
    private boolean faceUp;

    public CardFace(boolean faceUp) {
        this.faceUp = faceUp;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void flipUp() {
        faceUp = true;
    }

    public void flipDown() {
        faceUp = false;
    }
}
