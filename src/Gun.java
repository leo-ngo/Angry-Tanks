import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Gun {
    private Image image;
    private double x;
    private double y;
    private double angle;
    private double rotatingAngle;
    private double offset1;
    private double offset2;
    private double x0, y0; // where to draw
    private double xp, yp; // rotate about this point
    private boolean mirrored;

    public Gun(Image image, double x, double y, double angle, boolean mirrored) {
        this.image = image;
        setPosition(x, y, angle);
        rotatingAngle = 0;
        this.mirrored = mirrored;
    }

    public void draw(GraphicsContext context) {
        double x = this.x + offset1 * Math.cos(angle - Math.PI / 2) + offset2 * Math.cos(angle);
        double y = this.y + offset1 * Math.sin(angle - Math.PI / 2) + offset2 * Math.sin(angle);
        if (mirrored) {
            x0 = x + getBarrelLength() * Math.cos(angle);
            y0 = y + getBarrelLength() * Math.sin(angle);
            setOrigin(x0, y0);
            setRotatingCenter(x0, y0);
        } else {
            setOrigin(x, y);
            setRotatingCenter(x, y);
        }
        context.save();
        Transformations.rotate(context, Math.toDegrees(getTotalAngle()), xp, yp);
        context.drawImage(image, x0, y0);
        context.restore();
    }

    public void setRotatingCenter(double x, double y) {
        xp = x;
        yp = y;
    }
    public void setOrigin(double x, double y) {
        x0 = x;
        y0 = y;
    }
    public void setOffsets(double offset1, double offset2) {
        this.offset1 = offset1;
        this.offset2 = offset2;
    }

    public void setPosition(double x, double y, double angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public double getAngle() {
        return angle;
    }

    public double getXMuzzle() {
        return x0 + getBarrelLength() * Math.cos(getTotalAngle());
    }

    public double getYMuzzle() {
        return y0 + getBarrelLength() * Math.sin(getTotalAngle());
    }

    public double getBarrelLength() {
        return image.getWidth();
    }

    public void moveUp() {
        if (mirrored) {
            rotatingAngle = Math.toRadians(Math.toDegrees(rotatingAngle) + 1);
            if (rotatingAngle > Math.PI / 2) rotatingAngle = Math.PI / 2;
        } else {
            rotatingAngle = Math.toRadians(Math.toDegrees(rotatingAngle) - 1);
            if (rotatingAngle < -Math.PI / 2) rotatingAngle = -Math.PI/2;
        }

    }
    public void moveDown() {
        if (mirrored) {
            rotatingAngle = Math.toRadians(Math.toDegrees(rotatingAngle) - 1);
            if (rotatingAngle < 0) rotatingAngle = 0;
        } else {
            rotatingAngle = Math.toRadians(Math.toDegrees(rotatingAngle) + 1);
            if (rotatingAngle > 0) rotatingAngle = 0;
        }
    }

    public double getX() {
        return x;
    }

    public double getOffset1() {
        return offset1;
    }

    public double getOffset2() {
        return offset2;
    }

    public double getY() {
        return y;
    }

    public double getTotalAngle() {
        return angle + rotatingAngle + (mirrored ? Math.PI : 0);
    }
}
