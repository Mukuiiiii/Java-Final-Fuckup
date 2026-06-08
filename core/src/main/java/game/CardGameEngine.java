package game;

import component.CardStack;
import component.player;
import game.action.DamageAction;
import game.action.DrawCardAction;
import game.action.MoveCardAction;
import game.action.TransferCardAction;
import happy_factory.Card_Factory;
import happy_factory.Player_Factory;
import unhappyEC.ECManager;
import unhappyEC.Entity;

public class CardGameEngine {
    private final GameContext context;
    private final Player_Factory playerFactory;
    private final Card_Factory cardFactory;

    public CardGameEngine() {
        this(new GameContext());
    }

    public CardGameEngine(GameContext context) {
        this.context = context;
        ECManager ecManager = context.getECManager();
        this.playerFactory = new Player_Factory(ecManager);
        this.cardFactory = new Card_Factory(ecManager);
    }

    public GameContext getContext() {
        return context;
    }

    public Entity createPlayer() {
        Entity player = playerFactory.createPlayer();
        context.addPlayer(player);
        return player;
    }

    public Entity createDetachedPlayer() {
        return playerFactory.createPlayer();
    }

    public Entity createStack(Entity owner, String stackName) {
        Entity stack = context.getECManager().newEntity();
        stack.addComponent(new CardStack(owner, stack, stackName));
        owner.getComponent(player.class).addStack(stack);
        return stack;
    }

    public Entity createCard(String cardId) {
        Entity card = cardFactory.terp(cardId);
        if (card != null) {
            context.connectEffects(card);
        }
        return card;
    }

    public void addCardToStack(Entity owner, Entity card, String stackName) {
        CardStack stack = owner.getComponent(player.class)
            .getStack(stackName)
            .getComponent(CardStack.class);
        stack.insertCard(card);
        context.connectEffects(card);
    }

    public void drawCard(Entity player) {
        new DrawCardAction(player).execute(context);
    }

    public void drawCard(Entity player, String from, String to) {
        new DrawCardAction(player, from, to).execute(context);
    }

    public void moveCard(Entity owner, Entity card, String from, String to) {
        new MoveCardAction(owner, card, from, to).execute(context);
    }

    public void transferCard(Entity fromOwner, Entity toOwner, Entity card, String from, String to) {
        new TransferCardAction(fromOwner, toOwner, card, from, to).execute(context);
    }

    public void shuffleStack(Entity owner, String stackName) {
        owner.getComponent(player.class)
            .getStack(stackName)
            .getComponent(CardStack.class)
            .shuffle();
    }

    public void dealDamage(Entity source, Entity target, int value) {
        new DamageAction(source, target, value).execute(context);
    }

    public void startTurn() {
        context.startTurn();
    }

    public void nextTurn() {
        context.nextTurn();
    }
}
