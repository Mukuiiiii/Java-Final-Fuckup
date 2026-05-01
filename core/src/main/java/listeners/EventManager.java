package listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    private final Map<Class<? extends Event>, List<Listener<?>>> listenerMap = new HashMap<>();

    public <T extends Event> void register(Class<T> eventClass, Listener<T> listener) {
        listenerMap.computeIfAbsent(eventClass, k -> new ArrayList<>()).add(listener);
    }

    @SuppressWarnings("unchecked")
    public <T extends Event> void post(T event) {
        List<Listener<?>> listeners = listenerMap.get(event.getClass());
        if (listeners != null) {
            for (Listener<?> l : listeners) {
                // 將事件派發給所有註冊的人
                ((Listener<T>) l).onEvent(event);
            }
        }
    }
}
