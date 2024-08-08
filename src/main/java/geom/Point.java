package geom;

public class Point {

    private final double x;
    
    private final double y;

    public Point(double x, double y){

        this.x = x;

        this.y = y;

    }

    public String toString(){

        return "Point(" + x + ", " + y + ")";

    }

    public Point translate(double deltaX, double deltaY){

        Point otherPoint = new Point(this.x + deltaX, this.y + deltaY);

        return otherPoint;

    }

    public double getX(){

        return x;
        
    }

    public double getY(){

        return y;

    }

}
