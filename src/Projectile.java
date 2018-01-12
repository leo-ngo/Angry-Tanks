/**
 RMIT University Vietnam
 Course: INTE2512 Object-Oriented Programming
 Semester: 2017C
 Assignment: 3
 Team: ALV
 Author: Vuong Hung Ngo, Long Hoang Tran, Arofando Hadi
 ID: s3610887, s3635165, s3618954
 Created date: 12/01/2018
 Acknowledgement:
 https://stackoverflow.com/questions/18260421/how-to-draw-image-rotated-on-javafx-canvas
 https://giphy.com/stickers/fireworks-transparency-NxpMNq17Y2Khq
 https://www.freesoundeffects.com/free-sounds/explosion-10070/
 */
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;



public class Projectile implements Weight {
    public static final double GRAVITY = 1;
    public static final double INITIAL_VELOCITY = 30;
    public static final int DAMAGE = 10;

    private Image image;
    private double x, x0;
    private double y, y0;
    private double angle;
    private double t; // time
    private boolean visible;


    public Projectile(Image image, double x, double y, double angle) {
        this.image = image;
        this.x0 = x;
        this.y0 = y;
        this.angle = angle;
        this.t = 0;
        visible = true;
    }

    public void draw(GraphicsContext context) {
        context.drawImage(image, x, y);
        this.x = x0 + INITIAL_VELOCITY * Math.cos(angle) * t;
        this.falls();
    }

    public boolean checkCollision(Tank tank) {
        Terrain terrain = Terrain.getInstance();
        double dx = tank.getHitbox().getXc() - x;
        double dy = tank.getHitbox().getYc() - y;
        double distance = Math.sqrt(dx * dx + dy * dy);
        if (this.getXc() > Main.CANVAS_WIDTH || this.getXc() < 0 || // out of screen
                this.getYc() > Main.CANVAS_HEIGHT ||
                getYc() >= terrain.getY()[(int) Math.round(this.getXc())] || // hit terrain
                distance < tank.getHitbox().getRadius() + this.getRadius()) { // hit tank
            visible = false;
        }
        if (distance < tank.getHitbox().getRadius() + this.getRadius()) {
            tank.setHP(tank.getHP() - DAMAGE); // decrease tank's hp if hit by this projectile
            return true;
        }
        return false;
    }

    @Override
    public void falls() { // simulate gravity
        this.y = y0 + GRAVITY * t * t / 2 + INITIAL_VELOCITY * Math.sin(angle) * t;
        t++;
    }

    public boolean isVisible() {
        return visible;
    }


    public double getRadius() {
        return image.getHeight() / 2;
    }

    public double getYc() {
        return y + getRadius();
    }

    public double getXc() {
        return x + getRadius();
    }
}
