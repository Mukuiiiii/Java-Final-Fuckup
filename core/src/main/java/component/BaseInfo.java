package component;

import unhappyEC.Component;

public class BaseInfo extends Component {
    private String name;
    private String id;

    public BaseInfo(String name, String id) {
        this.name = name;
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }



}
