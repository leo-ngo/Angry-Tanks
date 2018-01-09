import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * Tank class: responsible for drawing tanks and their movements.
 */
public class Tank {
    GraphicsContext context;
    private Image image;
    private int HP;
    private int maxHP;
    private Terrain terrain;
    private double length;
    private double x1, x2; // Coordinates of two poles of the line represent the tank's base (touching terrain).
    private double y1, y2;
    private double orientation; // Orientation (angle) of the tank is angle of this line.
    private double offset; // Where the line is relative to the tank image's top left.
    private Gun gun;

    private List<Projectile> projectiles;

    public Tank(Image image) {
        this.image = image;
        this.maxHP = 100;
        this.HP = maxHP;
        this.terrain = Terrain.getInstance();
    }

    public void setPosition(double position, double offset) {
        this.x1 = position;
        this.y1 = terrain.getY()[(int)x1];
        this.offset = offset;
        setX2Y2(x1, y1);
        this.orientation = Math.atan((y1 - y2) / (x1 - x2));
    }

    /**
     * To simulate the tank's movement, we need to fine the other pole of the line and the line's orientation.
     * The line (base of the tank) is constant. We will slide it on the terrain.
     * Finding the other pole enables finding tank's angle (orientation).
     */
    void setX2Y2(double x1, double y1) {
        x2 = x1 + 1;
        y2 = y1 + 1;
        double length = getDistance(x1, y1, x2, y2);
        while (length < this.length) {
            x2++;
            y2 = terrain.getY()[(int) x2];
            length = getDistance(x1, y1, x2, y2);
        }
        if (terrain.getSlopeAt((int) x1) < terrain.getSlopeAt((int) x2)) {
            x2 = x1 + length * Math.cos(Math.atan(terrain.getSlopeAt((int) x1)));
            y2 = terrain.getSlopeAt((int) x1) * (x2 - x1) + y1;
        }
    }

    public Gun getGun() {
        return gun;
    }

    public void setGun(Image image) {
       this.gun = new Gun(image, x1, y1, orientation);
    }

    public void draw(GraphicsContext context) {
        double xp = x1 + image.getHeight() * Math.cos(orientation - Math.PI / 2) - offset * Math.cos(orientation); // wrong formulas
        double yp = y1 + image.getHeight() * Math.sin(orientation - Math.PI / 2) - offset * Math.sin(orientation);
        context.save();
        Transformations.rotate(context, orientation / Math.PI * 180, xp, yp);
        context.drawImage(image, xp, yp);
        context.restore();
        gun.draw(context);
    }

    public int getHP() {
        return HP;
    }
    public void setHP(int HP) {
        this.HP = HP;
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }
    public void fires(Projectile projectile) {
        projectiles.add(projectile);
    }

    void move(double distance) { // Distance > 0: move right, left otherwise.
        x1 += distance * Math.cos(Math.atan(terrain.getSlopeAt((int) x1)));
        if (x1 > 1279 - 25) {
            x1 = 1279 - 25;
        }
        if (x1 < 0) {
            x1 = 0;
        }
        y1 = terrain.getY()[(int) x1];
        if (y1 > 719) {
            y1 = 719;
        }
        if (y1 < 0) {
            y1 = 0;
        }
        setX2Y2(x1, y1);
        this.orientation = Math.atan((y1 - y2) / (x1 - x2));
        gun.setPosition(x1, y1, orientation);
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public void setLength(double length) {
        this.length = length;
    }

}
