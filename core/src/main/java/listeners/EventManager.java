package listeners;

import java.util.List;
import unhappyEC.ECManager;
import unhappyEC.Entity;

public class EventManager {
    private final ECManager ecManager;

    public EventManager(ECManager ecManager) {
        this.ecManager = ecManager;
    }

    public void register(Listener<?> listener) {
        Entity listenerEntity = ecManager.newEntity();
        listenerEntity.addComponent(listener);
    }

    public void post(Events event) {
        for (Entity entity : ecManager.EntitiesWithComponents(Listener.class)) {
            List<Listener> listeners = entity.getComponents(Listener.class);
            for (Listener listener : listeners) {
                if (listener.listensTo(event)) {
                    listener.dispatch(event);
                }
            }
        }
    }

    public <T extends Events> void beforeEvent(T event) {
        post(event);
    }

    public <T extends Events> void afterEvent(T event) {
        post(event);
    }

    public <T extends Events> void execEvent(T event, Runnable run) {
        beforeEvent(event);
        run.run();
        afterEvent(event);
    }
}
