package com.app.quran.realm.model;

import java.io.Serializable;

import io.realm.RealmObject;

public class Quran extends RealmObject implements Serializable {
    int index;
    int sura;
    int aya;
    String text;

    public int getIndex() {
        return index;
    }

    public int getAya() {
        return aya;
    }

    public int getSura() {
        return sura;
    }

    public String getText() {
        return text != null ? text : "";
    }

    public void setAya(int aya) {
        this.aya = aya;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setSura(int sura) {
        this.sura = sura;
    }

    public void setText(String text) {
        this.text = text;
    }

}
