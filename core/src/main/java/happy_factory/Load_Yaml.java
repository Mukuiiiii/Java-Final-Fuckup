package happy_factory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import org.yaml.snakeyaml.Yaml;
import java.util.Map;

public class Load_Yaml {
    private final Yaml yaml = new Yaml();

    public Map<String, Object> load(String folder, String filename) {
        FileHandle file = Gdx.files.internal(folder + "/" + filename + ".yaml");

        if (!file.exists()) {
            Gdx.app.error("YamlLoader", "找不到檔案: " + folder + "/" + filename);
            return null;
        }
        return yaml.load(file.read());
    }
}
