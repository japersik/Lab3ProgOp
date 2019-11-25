package City;

public abstract class Point {
    private final static double maxPointXY = 100.0;
    protected double pointX;
    protected double pointY;

    Point() {
        this(Math.random() * 2 * maxPointXY - maxPointXY, Math.random() * 2 * maxPointXY - maxPointXY);
    }

    Point(double pointX, double pointY) {
        this.pointX = pointX;
        this.pointY = pointY;
    }

    public final double getPointX() {
        return pointX;
    }

    public final void setPointX(double pointX) {
        this.pointX = pointX;
    }

    public final double getPointY() {
        return pointY;
    }

    public final void setPointY(double pointY) {
        this.pointY = pointY;
    }

    public final void setPosition(double pointX, double pointY) {
        this.pointX = pointX;
        this.pointY = pointY;
    }

    public String PointToString() {
        String x = String.format("%.2f", pointX);
        String y = String.format("%.2f", pointY);
        return "(" + x + ";" + y + ")";
    }
}
