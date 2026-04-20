package happy_interpreter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import org.yaml.snakeyaml.Yaml;
import java.util.Map;

public class Load_Yaml {
    private final Yaml yaml = new Yaml();

    public Map<String, Object> load(String folder, String id) {
        FileHandle file = Gdx.files.internal(folder + "/" + id + ".yaml");
        if (!file.exists()) {
            Gdx.app.error("YamlLoader", "找不到檔案: " + folder + "/" + id);
            return null;
        }
        return yaml.load(file.read());
    }
}
