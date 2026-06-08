package listeners;

import unhappyEC.Component;

public abstract class Listener<T extends Events> extends Component {
    private final Class<T> eventClass;

    protected Listener(Class<T> eventClass) {
        this.eventClass = eventClass;
    }

    public boolean listensTo(Events event) {
        return eventClass.isInstance(event);
    }

    public void dispatch(Events event) {
        onEvent(eventClass.cast(event));
    }

    public abstract void onEvent(T event);
}
