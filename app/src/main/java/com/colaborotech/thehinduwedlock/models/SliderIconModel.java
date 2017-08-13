package com.colaborotech.thehinduwedlock.models;

/**
 * Created by him on 21-Jun-17.
 */

public class SliderIconModel {
    int id;
    int icon;
    String name;

    public SliderIconModel(int id, int icon, String name) {
        this.id = id;
        this.icon = icon;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
