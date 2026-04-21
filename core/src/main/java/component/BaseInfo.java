package component;

import unhappyEC.Component;
public class BaseInfo {
   private String name;
    private String description;

    BaseInfo(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }



}
