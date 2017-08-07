package com.example.lamng.material_animations.model;

import android.support.annotation.ColorRes;

import java.io.Serializable;

public class SampleData implements Serializable {

    private final int id;
    private final int color;
    private final String name;

    public SampleData(int id, @ColorRes int color, String name) {
        this.id = id;
        this.color = color;
        this.name = name;
    }

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

}
