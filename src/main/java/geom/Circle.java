package geom;

import geom.Point;

public class Circle {

    private final geom.Point center;

    private final double centerX;

    private final double centerY;

    private final double radius;

    public Circle(geom.Point center, double r){

        this.center = center;

        centerX = this.center.getX();

        centerY = this.center.getY();

        radius = r;

    }

    public Circle(double x, double y, double r){

        centerX = x;

        centerY = y;

        this.center = new Point(centerX, centerY);

        radius = r;

    }

    public String toString(){

        return "Circle(" + center + ", " + radius + ")";

    }

    public Circle translate(double deltaX, double deltaY){

        Point otherPoint = new Point(this.centerX + deltaX, this.centerY + deltaY);

        return new Circle(otherPoint, radius);

    }
    
}
