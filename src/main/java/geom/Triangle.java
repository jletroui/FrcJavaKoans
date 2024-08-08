package geom;

import geom.Point;

public class Triangle {
    private final geom.Point a;

    private final double xa;

    private final double ya;
    
    private final geom.Point b;

    private final double xb;

    private final double yb;

    private final geom.Point c;

    private final double xc;

    private final double yc;

    public Triangle(geom.Point a, geom.Point b, geom.Point c){

        this.a = a;

        xa = this.a.getX();

        ya = this.a.getY();

        this.b = b;

        xb = this.b.getX();

        yb = this.b.getY();

        this.c = c;

        xc = this.c.getX();

        yc = this.c.getY();

    }

    public Triangle(double ax, double ay, double bx, double by, double cx, double cy){

        a = new Point(ax, ay);
        
        xa = ax;

        ya = ay;

        b = new Point(bx, by);

        xb = bx;

        yb = by;

        c = new Point(cx, cy);

        xc = cx;

        yc = cy;

    }

    public String toString(){

        return "Triangle(" + a + ", " + b + ", " + c + ")";

    }

    public Triangle translate(double deltaX, double deltaY){

        Triangle otherTriangle = new Triangle(new Point(xa + deltaX, ya + deltaY), new Point(xb + deltaX, yb + deltaY), new Point(xc + deltaX, yc + deltaY));

        return otherTriangle;

    }

    
}
