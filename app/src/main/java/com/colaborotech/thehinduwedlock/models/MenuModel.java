package com.colaborotech.thehinduwedlock.models;

/**
 * Created by ubuntu on 24/8/17.
 */

public class MenuModel {
    private int id;
    private String data;
    private boolean selected;

    public MenuModel(int id, String data, boolean selected) {
        this.id = id;
        this.data = data;
        this.selected = selected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
