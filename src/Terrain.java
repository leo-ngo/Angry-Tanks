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
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Terrain Generator
 */

public class Terrain {
    private static Terrain terrain;
    private List<Double> x;
    private List<Double> y;
    private double deltaX;
    private double slope;
    private double y0;
    private int x0;
    private static final double MAX_HEIGHT = 600;
    private static final double MIN_HEIGHT = 300;
    private static final double WIDTH = 1280;
    private static final double HEIGHT = 720;
    private static final double DELTA_X_MAX = 100;
    private static final double DELTA_X_MIN = 10;
    private static final double SLOPE_MIN = -2;
    private static final double SLOPE_MAX = 2;

    public static Terrain getInstance() { // SINGLETON
        if (terrain == null) {
            terrain = new Terrain();
        }
        return terrain;
    }

    private Terrain() {
        x = new ArrayList<>();
        y = new ArrayList<>();
        deltaX = generateDeltaX();
        slope = generateSlope();
        y0 = generateY0();
        x0 = 0;
        for (int x = 0; x < WIDTH; x++) {
            this.x.add((double) x);
            double y = slope * x + y0;
            if (x - x0 == (int) deltaX) {
                createSegmentAt(x, y);
            }
            if (y > MAX_HEIGHT) {
                y = MAX_HEIGHT;
                createSegmentAt(x, y);
            }
            if (y < MIN_HEIGHT) {
                y = MIN_HEIGHT;
                createSegmentAt(x, y);
            }
            this.y.add(y);
        }
        this.x.add(WIDTH);
        this.y.add(HEIGHT);
        this.x.add(0.0);
        this.y.add(HEIGHT);
    }

    void draw(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setLineWidth(1);
        gc.fillPolygon(getX(), getY(), getX().length);
    }

    // Get coordinates of the terrain.
    public double[] getX() {
        double[] x = new double[this.x.size()];
        for (int i = 0; i < x.length; i++) {
            x[i] = this.x.get(i);
        }
        return x;
    }

    public double[] getY() {
        double[] y = new double[this.y.size()];
        for (int i = 0; i < y.length; i++) {
            y[i] = this.y.get(i);
        }
        return y;
    }

    // Helper methods.
    private double generateY0() {
        return Math.random() * (MAX_HEIGHT - MIN_HEIGHT) + MIN_HEIGHT;
    }

    private double generateSlope() {
        return (SLOPE_MAX - SLOPE_MIN) * Math.random() + SLOPE_MIN;
    }

    private double generateDeltaX() {
        return Math.random() * (DELTA_X_MAX - DELTA_X_MIN) + DELTA_X_MIN;
    }

    private void createSegmentAt(int x, double y) {
        slope = generateSlope();
        y0 = y - slope * x;
        deltaX = generateDeltaX();
        x0 = x;
    }

    /**
     * Get the slope at the ith point on the terrain (0 to canvas's width). This is important to orient/rotate the
     * tanks and its gun.
      * @param i the ith point in the list of coodinates: (x[i], y[i])
     * @return the slope at i
     */
    public double getSlopeAt(int i) {
        if (i + 1 < WIDTH && getX()[i] != getX()[i + 1]) {
            return (getY()[i] - getY()[i + 1]) / (getX()[i] - getX()[i + 1]);
        }
        return 100.0;
    }
}
