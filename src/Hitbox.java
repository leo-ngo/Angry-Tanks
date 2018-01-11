/**
 RMIT University Vietnam
 Course: INTE2512 Object-Oriented Programming
 Semester: 2017C
 Assignment: 3
 Team: ALV
 Author: Vuong Hung Ngo, Long Hoang Tran, Arofando Hadi
 ID: s3610887, s3635165, s3618954
 Created date: 12/01/2018
 Acknowledgement:  https://stackoverflow.com/questions/18260421/how-to-draw-image-rotated-on-javafx-canvas
 https://giphy.com/stickers/fireworks-transparency-NxpMNq17Y2Khq
 */
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
