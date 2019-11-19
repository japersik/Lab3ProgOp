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

    public double getPointX() {
        return pointX;
    }

    public void setPointX(double pointX) {
        this.pointX = pointX;
    }

    public double getPointY() {
        return pointY;
    }

    public void setPointY(double pointY) {
        this.pointY = pointY;
    }

    public void setPosition(double pointX, double pointY) {
        this.pointX = pointX;
        this.pointY = pointY;
    }

    public String PointToString() {
        String x = String.format("%.2f", pointX);
        String y = String.format("%.2f", pointY);
        return "(" + x + ";" + y + ")";
    }
}
