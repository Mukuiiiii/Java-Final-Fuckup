package happy_factory.Parsers;

import unhappyEC.Entity;

public interface ComponentParser {
    void parseAndAdd(Entity entity, Object data);
}
