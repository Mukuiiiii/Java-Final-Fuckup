package move;


import component.CardStack;
import component.player;
import unhappyEC.Entity;

// TODO:
//  rename the class
public class draw extends  Move {
    public static void drawCard(Entity player, String from, String to) {
        CardStack fromStack = player.getComponent(player.class).getStack(from).getComponent(CardStack.class);
        CardStack toStack = player.getComponent(player.class).getStack(to).getComponent(CardStack.class);

        if (fromStack.size() == 0) {
//         pass
//         TODO:
//            add exeception
        }
        else {
            Entity card = fromStack.Top();
            fromStack.popFromTop();
            toStack.insertCard(card);
        }
    }





}
