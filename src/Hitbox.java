public class Hitbox {

    public static final double HITBOX_RADIUS = 12;

    double xc, yc, radius;

    public Hitbox(double x, double y, double x2, double y2) {
        this.xc = (x + x2) / 2;
        this.yc = (y + y2) / 2;
        this.radius = HITBOX_RADIUS;
    }

    public void setCenter(double x, double y, double x2, double y2) {
        this.xc = (x + x2) / 2;
        this.yc = (y + y2) / 2;
    }

    public double getXc() {
        return xc;
    }

    public double getYc() {
        return yc;
    }

    public double getRadius() {
        return radius;
    }
}
