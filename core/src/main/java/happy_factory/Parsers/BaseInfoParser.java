package happy_factory.Parsers;

import java.util.Map;
import unhappyEC.Entity;
import component.BaseInfo;

public class BaseInfoParser implements ComponentParser{
    @Override
    public void parseAndAdd(Entity entity, Object data) {
        Map<String, String> infoMap = (Map<String, String>) data;

        String name = infoMap.get("name");
        String id = infoMap.get("id");
        String flavor = infoMap.get("flavor");
        String imagePath = infoMap.get("imagePath");

        entity.addComponent(new BaseInfo(name, id, flavor, imagePath));
    }
}
