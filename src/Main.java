import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main extends Application {
    public static final double CANVAS_WIDTH = 1280;
    public static final double CANVAS_HEIGHT = 720;
    Group root = new Group();
    Scene scene = new Scene(root);
    @Override
    public void start(Stage primaryStage) throws Exception {

        // Set up stage, scene, root node, and an element (canvas)
        primaryStage.setTitle("Angry Tanks");


        primaryStage.setScene(scene);
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        root.getChildren().add(canvas);
        GraphicsContext context = canvas.getGraphicsContext2D(); // context is where we draw

        // Set up key events.
        List<String> input = new ArrayList<>(); // keyboard input is stored here
        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        if (!input.contains(code)) { // add once to prevent dupes
                            input.add(code);
                        }
                    }
                });
        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        input.remove(code); // remove on release
                    }
                });

        // Set up terrain, tanks
        Terrain terrain = Terrain.getInstance();
        Tank tank1 = TankFactory.createTank("tank1"); // Player 1
        Tank tank2 = TankFactory.createTank("tank2"); // Player 2
        Background background = new Background();
        Explosion explosion1 = new Explosion();
        //Explosion explosion2 = new Explosion();

        new AnimationTimer() { // Now start the game loop (animation)
            @Override
            public void handle(long now) {
                context.clearRect(0, 0, 1280, 720); // Clear the context from previous drawings
                background.draw(context);
                terrain.draw(context); // draw terrain first
                tank1.draw(context); // then tanks (meaning tanks are drawn on top of terrain if same coordinates
                tank2.draw(context);
                explosion1.draw(context);
                //explosion2.draw(context);
                processInput();
            }
            void processInput() {
                if (input.contains("A")) {
                    tank1.move(-2);
                }
                if (input.contains("D")) {
                    tank1.move(+2);
                }
                if (input.contains("SPACE")) {
                    //tank1.fires(new Projectile(new Image("file:resources/projectile.png"),
                            //tank1.getX2(), tank1.getY2()));
                    explosion1.setPos(tank1.getX1(), tank2.getY1());
                }
                if (input.contains("LEFT")) {
                    tank2.move(-2);
                }
                if (input.contains("RIGHT")) {
                    tank2.move(+2);
                }
                if (input.contains("NUMPAD0")) {
                    tank2.fires(new Projectile(new Image("file:resources/projectile.png"),
                            tank2.getX2(), tank2.getY2()));
                }
            }
        }.start();
        primaryStage.show();
    }

}
