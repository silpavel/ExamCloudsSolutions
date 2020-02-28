package ru.mail.nn.pasha.first;
// demonstration abstract class
abstract public class Shape {
    public Shape(Color color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }
    abstract void drow();
    abstract public double square();
    protected int x,y;
    private Color color;
}
class Circle extends Shape{
    private int radius;
    public Circle(Color color, int x, int y, int radius) {
        super(color, x, y);
        this.radius = radius;
    }
    @Override
    void drow() {
        System.out.println("Drow circle.");
    }
    @Override
    public double square() {
        double PI= 3.14;
        return PI*radius*radius;
    }
}
class Rectangle extends Shape{
    private int xlength, ylength;
    public Rectangle(Color color, int x, int y,
                     int xlength, int ylength) {
        super(color, x, y);
        this.xlength = xlength;
        this.ylength = ylength;
    }
    @Override
    void drow() {
        System.out.println("Drow rectangle");
    }
    @Override
    public double square() {
        return xlength*ylength;
    }
}
enum Color{Red, Blue, Yellow}

