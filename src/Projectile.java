import javafx.scene.image.Image;

/**
 * Projectiles
 */

public class Projectile {
    private Image image;
    private double x;
    private double y;

    public Projectile(Image image, double x, double y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
