package com.colaborotech.thehinduwedlock.models;

public class MultipleModel {

    private Object object;
    private boolean selected;

    public MultipleModel(Object object, boolean selected) {
        this.object = object;
        this.selected = selected;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}