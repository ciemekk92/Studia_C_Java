package com.company;

abstract class Figure {
    private String label;

    public String getLabel() {
        if (label != null) {
            return label;
        }
        return "Brak etykiety";
    }
    public void setLabel(String label) {
        this.label = label;
    }
}

class Point extends Figure {
    private double x,y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        x = 0;
        y = 0;
    }

    @Override
    public String toString() {
        return "(" + x + " , " + y + ") Etykieta:" + getLabel();
    }
}

class Circle extends Figure {
    private Point p1;
    private double r;

    public Circle(Point p1, double r) {
        this.p1 = p1;
        this.r = r;
    }

    public Circle() {}

    public String toString() {
        return p1.toString() + " r = " + r + " Etykieta: " + getLabel();
    }
}