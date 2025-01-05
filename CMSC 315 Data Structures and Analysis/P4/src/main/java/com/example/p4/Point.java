package com.example.p4;

public class Point {
    private double x;
    private double y;
    private String name;

    /* no-arg constructor */
    public Point() {

    }

    public Point(double x, double y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getName() {
        return name;
    }
}