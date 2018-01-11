import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;
import java.util.List;


public class Main extends Application {
    public static final double CANVAS_WIDTH = 1280;
    public static final double CANVAS_HEIGHT = 720;
    public static final double SPEED = 2;

    public enum STATE{
        MENU,
        GAME
    }

    public STATE state = STATE.MENU;


    @Override
    public void start(Stage primaryStage) throws Exception {

        // Set up stage, scene, root node, and an element (canvas)
        primaryStage.setTitle("Angry Tanks");
        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        root.getChildren().add(canvas);
        GraphicsContext context = canvas.getGraphicsContext2D(); // context is where we draw

        // Detect key events and store in keyPress list
        List<String> keyPress = new ArrayList<>();
        List<String> keyRelease = new ArrayList<>();
        scene.setOnKeyPressed(
                e -> {
                    String key = e.getCode().toString();
                    if (!keyPress.contains(key)) { // add once to prevent dupes
                        keyPress.add(key);
                    }
                });
        scene.setOnKeyReleased(
                e -> {
                    String key = e.getCode().toString();
                    keyPress.remove(key); // remove on release
                    keyRelease.add(key);
                });

        // Load resources
        TitleScreen menu = new TitleScreen();
        Background background = new Background();
        Terrain terrain = Terrain.getInstance();
        Tank tank1 = TankFactory.createTank("tank1"); // Player 1
        Tank tank2 = TankFactory.createTank("tank2"); // Player 2
        HPBar hpBar1 = new HPBar(tank1);
        HPBar hpBar2 = new HPBar(tank2);
        Image projectileImage = new Image("file:resources/projectile.png");
        WinnerCelebration winnerCelebration = new WinnerCelebration();
        Image[] explosionImages = new Image[34];
        for (int i = 0; i < 34; i++) {
            explosionImages[i] = new Image("file:resources/explosion/" + i + ".png");
        }
        List<Explosion> explosions = new ArrayList<>();

        new AnimationTimer() { // Now start the game loop (animation)
            @Override
            public void handle(long now) {
                context.clearRect(0, 0, 1280, 720); // Clear the context from previous drawings
                if (state == STATE.GAME) { // Start Game
                    background.draw(context);
                    terrain.draw(context); // draw terrain first
                    processInput();
                    tank1.draw(context); // then tanks (meaning tanks are drawn on top of terrain if same coordinates
                    tank2.draw(context);
                    for (Projectile projectile : tank1.getProjectiles()) {
                        if (projectile.checkCollision(tank2)) {
                            hpBar2.takesDamage(Projectile.DAMAGE);
                            explosions.add(new Explosion(explosionImages, tank2.getHitbox().getXc(), tank2.getHitbox().getYc()));
                        }
                        projectile.draw(context);
                    }
                    for (Projectile projectile : tank2.getProjectiles()) {
                        if (projectile.checkCollision(tank1)) {
                            hpBar1.takesDamage(Projectile.DAMAGE);
                            explosions.add(new Explosion(explosionImages, tank1.getHitbox().getXc(), tank1.getHitbox().getYc()));
                        }
                        projectile.draw(context);
                    }
                    for (Explosion explosion: explosions) {
                        explosion.draw(context);

                    }
                    explosions.removeIf(explosion -> explosion.getImageNum() == 34);
                    hpBar1.draw(context);
                    hpBar2.draw(context);
                    checkVictory();
                } else { // Start Menu
                    menu.draw(context);
                    processInputMenu();
                }
            }

            void checkVictory() {
                if (tank1.getHP() <= 0 && tank2.getHP() <= 0) {
                    winnerCelebration.setTank(null);
                    winnerCelebration.draw(context);
                } else if (tank1.getHP() <= 0) {
                    winnerCelebration.setTank("tank2");
                    winnerCelebration.draw(context);
                } else if (tank2.getHP() <=0) {
                    winnerCelebration.setTank("tank1");
                    winnerCelebration.draw(context);
                }
            }

            void processInput() {
                if (keyPress.contains("A")) {
                    tank1.move(-SPEED);
                }
                if (keyPress.contains("D")) {
                    if (!collisionBetweenTanks()) tank1.move(+SPEED);
                }
                if (keyPress.contains("W")) {
                    tank1.getGun().moveUp();
                }
                if (keyPress.contains("S")) {
                    tank1.getGun().moveDown();
                }
                if (keyRelease.contains("SPACE")) {
                    tank1.fires(new Projectile(projectileImage,
                            tank1.getGun().getXMuzzle(), tank1.getGun().getYMuzzle(), tank1.getGun().getTotalAngle(), false));
                }
                if (keyPress.contains("UP")) {
                    tank2.getGun().moveUp();
                }
                if (keyPress.contains("DOWN")) {
                    tank2.getGun().moveDown();
                }
                if (keyPress.contains("LEFT")) {
                    if (!collisionBetweenTanks()) tank2.move(-SPEED);
                }
                if (keyPress.contains("RIGHT")) {
                    tank2.move(+SPEED);
                }
                if (keyRelease.contains("NUMPAD0")) {
                    tank2.fires(new Projectile(projectileImage,
                            tank2.getGun().getXMuzzle(), tank2.getGun().getYMuzzle(), tank2.getGun().getTotalAngle(), true));
                }
                if (keyRelease.contains("ESCAPE")) {
                    state = STATE.MENU;
                }
                keyRelease.clear();
            }

            void processInputMenu() {
                if (keyRelease.contains("A") || keyRelease.contains("D") ||
                        keyRelease.contains("LEFT") || keyRelease.contains("RIGHT")) {
                    menu.changeOK();
                }
                if (keyRelease.contains("SPACE")) {
                    if (menu.isOK()) {
                        state = STATE.GAME;
                    } else {
                        primaryStage.close();
                    }
                }
                keyRelease.clear();
            }

            boolean collisionBetweenTanks() {
                return tank1.getX2() >= tank2.getX1();
            }
        }.start();
        primaryStage.show();
    }

}
