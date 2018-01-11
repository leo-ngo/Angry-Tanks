import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Gun {
    private Image image;
    private double x;
    private double y;
    private double angle;
    private double offset1;
    private double offset2;

    public Gun(Image image, double x, double y, double angle) {
        this.image = image;
        setPosition(x, y, angle);
    }

    public void draw(GraphicsContext context) {
        double xp = x + offset1 * Math.cos(angle - Math.PI / 2) + offset2 * Math.cos(angle); // no idea how this works
        double yp = y + offset1 * Math.sin(angle - Math.PI / 2) + offset2 * Math.sin(angle);
        context.save();
        Transformations.rotate(context, angle / Math.PI * 180, xp, yp);
        context.drawImage(image, xp, yp);
        context.restore();
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
}
