package cn.dumboj.bean;

import java.util.Arrays;

public class User {
    private long id;
    private String name;
    private String[] locations;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getLocations() {
        return locations;
    }

    public void setLocations(String[] locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", locations=" + Arrays.toString(locations) +
                '}';
    }
}
