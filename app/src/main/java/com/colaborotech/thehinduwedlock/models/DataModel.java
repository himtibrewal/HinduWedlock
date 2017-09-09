package com.colaborotech.thehinduwedlock.models;

/**
 * Created by him on 27-May-17.
 */

public class DataModel {
    private int _id;
    private String _dataName;
    private String _parents;

    public DataModel(int _id, String _dataName) {
        this._id = _id;
        this._dataName = _dataName;
    }

    public DataModel(int _id, String _dataName, String _parents) {
        this._id = _id;
        this._dataName = _dataName;
        this._parents = _parents;
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_dataName() {
        return _dataName;
    }

    public void set_dataName(String _dataName) {
        this._dataName = _dataName;
    }

    public String get_parents() {
        return _parents;
    }

    public void set_parents(String _parents) {
        this._parents = _parents;
    }
}

