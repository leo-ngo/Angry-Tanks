import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Projectiles
 */

public class Projectile implements Weight {
    public static final double GRAVITY = 1;
    public static final double INITIAL_VELOCITY = 20;

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

    public void drawProjectile(GraphicsContext context) {
        context.drawImage(image, x, y);
        this.x = x0 + INITIAL_VELOCITY * Math.cos(angle) * t;
        this.falls();
    }

    public void checkCollision(Tank tank) {
        if (this.x > Main.CANVAS_WIDTH || this.x < 0 || this.y > Main.CANVAS_HEIGHT || this.y < 0 ||
                new ICollidableWithTerrain(this).collided() ||
                new ICollidableWithTank(tank, this).collided()) {
            visible = false;
            if (new ICollidableWithTank(tank, this).collided()) System.out.print("Hit!");
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getAngle() {
        return angle;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    @Override
    public void falls() { // simulate gravity
        this.y = y0 + GRAVITY * t * t / 2 + INITIAL_VELOCITY * Math.sin(angle) * t;
        t++;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
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
