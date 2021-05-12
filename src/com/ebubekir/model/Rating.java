package com.ebubekir.model;

public class Rating {

    private String item;
    private double value;

    public Rating(String item, double value) {
        this.item = item;
        this.value = value;
    }

    public String getItem() {
        return item;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "[" + getItem() + ", " + getValue() + "]";
    }

    public int compareTo(Rating other){
        if(value < other.value) return -1;
        if(value > other.value) return 1;
        return 0;
    }

}
