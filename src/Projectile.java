import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Projectiles
 */

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
    private boolean mirrored;

    public Projectile(Image image, double x, double y, double angle, boolean mirrored) {
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
        if (this.getXc() > Main.CANVAS_WIDTH || this.getXc() < 0 ||
                this.getYc() > Main.CANVAS_HEIGHT || this.getYc() < 0 ||
                getYc() >= terrain.getY()[(int) Math.round(this.getXc())] ||
                distance < tank.getHitbox().getRadius() + this.getRadius()) {
            visible = false;
        }
        if (distance < tank.getHitbox().getRadius() + this.getRadius()) {
            tank.setHP(tank.getHP() - DAMAGE);
            System.out.println(tank.getHP());
            return true;
        }
        return false;
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
