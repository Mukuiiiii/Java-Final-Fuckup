package happy_interpreter.Parsers;

import unhappyEC.Entity;

public interface ComponentParser {
    void parseAndAdd(Entity entity, Object data);
}
