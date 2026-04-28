package component;

import unhappyEC.Component;

public class BaseInfo extends Component {
    private String name;
    private String id;
    private String flavor;
    private String imagePath;

    public BaseInfo(String name, String id, String flavor, String imagePath) {
        this.name = name;
        this.id = id;
        this.flavor = flavor;
        this.imagePath = imagePath;
    }
    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public String getFlavor() { return flavor; }
    public String getImagePath() { return imagePath; }



}
