package listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    private final Map<Class<? extends Events>, List<Listener<?>>> listenerMap = new HashMap<>();

    public <T extends Events> void register(Class<T> eventClass, Listener<T> listener) {
        listenerMap.computeIfAbsent(eventClass, k -> new ArrayList<>()).add(listener);
    }

    @SuppressWarnings("unchecke")
    public <T extends Events> void post(T event) {
        List<Listener<?>> listeners = listenerMap.get(event.getClass());
        if (listeners != null) {
            for (Listener<?> l : listeners) {
                // 將事件派發給所有註冊的人
                ((Listener<T>) l).onEvent(event);
            }
        }
    }

    public <T extends Events> void beforeEvent(T event) {
        this.post(event);
    }

    public <T extends Events> void afterEvent(T event) {
        this.post(event);
    }

    public <T extends Events> void execEvent(T event, Runnable run) {
        beforeEvent(event);
        run.run();
        afterEvent(event);
    }
}
