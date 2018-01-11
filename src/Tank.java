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
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Tank class: responsible for drawing tanks and their movements.
 */
public class Tank {
    public static final int MAX_HP = 100;
    GraphicsContext context;
    private Image image;
    private int HP;
    private int maxHP;
    private Terrain terrain;
    private double length;
    private double x, x1, x2; // Coordinates of two poles of the line represent the tank's base (touching terrain).
    private double y, y1, y2;
    private double orientation; // Orientation (angle) of the tank is angle of this line.
    private double offset; // Where the line is relative to the tank image's top left.
    private Gun gun;
    private Hitbox hitbox;
    private boolean mirrored;

    private List<Projectile> projectiles;
    private String name;

    public Tank(Image image) {
        this.image = image;
        this.maxHP = MAX_HP;
        this.HP = maxHP;
        this.terrain = Terrain.getInstance();
        projectiles = new ArrayList<>();
    }

    public void setPosition(double position, double offset) {
        this.x1 = position;
        this.y1 = terrain.getY()[(int)x1];
        this.offset = offset;
        setX2Y2();
        this.orientation = Math.atan((y1 - y2) / (x1 - x2));
        setXY();
        this.hitbox = new Hitbox(x, y, x2, y2);
    }

    /**
     * To simulate the tank's movement, we need to fine the other pole of the line and the line's orientation.
     * The line (base of the tank) is constant. We will slide it on the terrain.
     * Finding the other pole enables finding tank's angle (orientation).
     */
    void setX2Y2() {
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
    void setXY() {
        this.x = x1 + image.getHeight() * Math.cos(orientation - Math.PI / 2);
        this.y = y1 + image.getHeight() * Math.sin(orientation - Math.PI / 2);
    }

    public Gun getGun() {
        return gun;
    }

    public void setGun(Image image) {
       this.gun = new Gun(image, x1, y1, orientation, mirrored);
    }

    public void draw(GraphicsContext context) {
        double xp = x - offset * Math.cos(orientation);
        double yp = y - offset * Math.sin(orientation);
        context.save();
        Transformations.rotate(context, orientation / Math.PI * 180, xp, yp);
        context.drawImage(image, xp, yp);
        context.restore();
        gun.draw(context);
        projectiles.removeIf(projectile -> (!projectile.isVisible()));
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
        if (x1 > Main.CANVAS_WIDTH - image.getWidth()) {
            x1 = Main.CANVAS_WIDTH - image.getWidth();
        }
        if (x1 < 0) {
            x1 = 0;
        }
        y1 = terrain.getY()[(int) x1];
        if (y1 > Main.CANVAS_HEIGHT) {
            y1 = Main.CANVAS_HEIGHT;
        }
        if (y1 < 0) {
            y1 = 0;
        }
        setX2Y2();
        this.orientation = Math.atan((y1 - y2) / (x1 - x2));
        setXY();
        gun.setPosition(x1, y1, orientation);
        hitbox.setCenter(x, y, x2, y2);
    }

    public double getX1() {
        return x1;
    }



    public double getX2() {
        return x2;
    }



    double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public void setLength(double length) {
        this.length = length;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public void setMirrored(boolean mirrored) {
        this.mirrored = mirrored;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
