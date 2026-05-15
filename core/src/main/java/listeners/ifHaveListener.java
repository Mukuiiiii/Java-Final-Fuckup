package listeners;

import listeners.Event.Drawcard;
import unhappyEC.ECManager;
import unhappyEC.Entity;
import java.util.function.Predicate;

public class ifHaveListener implements Listener<Drawcard> {

    private final ECManager ecManager;
    private final Predicate<Entity> condition; // 濾網規則
    private final Runnable action;             // 觸發動作

    /**
     * @param ecManager 傳入實體管理器
     * @param condition 定義「要檢查什麼」 (例如：e -> e.getName().equals("XXX"))
     * @param action    定義「符合就做什麼」 (例如：() -> player.addHp(5))
     */
    public ifHaveListener(ECManager ecManager, Predicate<Entity> condition, Runnable action) {
        this.ecManager = ecManager;
        this.condition = condition;
        this.action = action;
    }

    @Override
    public void onEvent(Drawcard event) {
        boolean match = ecManager.getEntity().stream()
            .anyMatch(condition);

        if (match) {
            action.run();
        }
    }
}
