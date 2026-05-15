package listeners;

public interface Listener <T extends Events>{
    void onEvent(T event);
}
