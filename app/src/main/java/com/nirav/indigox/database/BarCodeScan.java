package com.nirav.indigox.database;

public class BarCodeScan {
    private int _id;
    private String _barCodeText;

    public BarCodeScan() {
        // To-DO
    }

    public BarCodeScan(String barCodeText) {
        this._barCodeText = barCodeText;
    }

    public int getID() {
        return _id;
    }

    public void setID(int _id) {
        this._id = _id;
    }

    public String getBarCodeText() {
        return _barCodeText;
    }

    public void setBarCodeText(String barCodeText) {
        this._barCodeText = barCodeText;
    }

    @Override
    public String toString() {
        return "BarCodeScan{" +
                "_id=" + _id +
                ", _barCodeText='" + _barCodeText + '\'' +
                '}';
    }
}
