package game;

import java.util.ArrayList;
import java.util.List;
import component.EffectComponent;
import listeners.EffectListener;
import listeners.EventManager;
import listeners.Event.TurnEnded;
import listeners.Event.TurnStarted;
import unhappyEC.ECManager;
import unhappyEC.Entity;

public class GameContext {
    private final ECManager ecManager;
    private final EventManager eventManager;
    private final List<Entity> players = new ArrayList<>();
    private int activePlayerIndex = 0;
    private int turnNumber = 1;
    private boolean gameOver = false;

    public GameContext() {
        this(new ECManager());
    }

    public GameContext(ECManager ecManager) {
        this.ecManager = ecManager;
        this.eventManager = new EventManager(ecManager);
    }

    public ECManager getECManager() {
        return ecManager;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public void addPlayer(Entity player) {
        players.add(player);
        connectEffects(player);
    }

    public List<Entity> getPlayers() {
        return new ArrayList<>(players);
    }

    public Entity getActivePlayer() {
        if (players.isEmpty()) {
            return null;
        }

        return players.get(activePlayerIndex);
    }

    public void nextTurn() {
        endTurn();
        if (!players.isEmpty()) {
            activePlayerIndex = (activePlayerIndex + 1) % players.size();
        }
        turnNumber++;
        startTurn();
    }

    public void startTurn() {
        eventManager.post(new TurnStarted(getActivePlayer(), turnNumber));
    }

    public void endTurn() {
        eventManager.post(new TurnEnded(getActivePlayer(), turnNumber));
    }

    public void connectEffects(Entity entity) {
        if (entity == null) {
            return;
        }

        EffectComponent effect = entity.getComponent(EffectComponent.class);
        if (effect == null || entity.getComponent(EffectListener.class) != null) {
            return;
        }

        entity.addComponent(new EffectListener(effect));
    }

    public void connectAllEffects() {
        for (Entity entity : ecManager.EntitiesWithComponents(EffectComponent.class)) {
            connectEffects(entity);
        }
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
