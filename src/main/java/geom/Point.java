package geom;

public class Point {
    private final double x;
    private final double y;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "Point(" + x + ", " + y + ")";
    }

    public Point translate(double tx, double ty) {
        return new Point(x + tx, y+ty);
    }
}
