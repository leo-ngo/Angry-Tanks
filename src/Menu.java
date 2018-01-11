import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Menu extends Application
{
    Stage window;
    Button start, close;
    Group root = new Group();
    Scene menu;
    Scene game = new Scene(root, 1280, 720);
    Boolean press = true;
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        window = primaryStage;
        start = new Button("Start");
        close = new Button("Close");
        start.setOnAction(e -> primaryStage.setScene(game));

        close.setOnAction(e -> closeProgram());

        window.setOnCloseRequest(e -> closeProgram());

        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(start, close);
        menu = new Scene(layout1, 200, 200);
        primaryStage.setScene(menu);
        primaryStage.setTitle("Angry Tanks");



        Canvas canvas = new Canvas(1280, 720);
        root.getChildren().add(canvas);
        GraphicsContext context = canvas.getGraphicsContext2D(); // context is where we draw

        // Set up key events.
        List<String> input = new ArrayList<>(); // keyboard input is stored here
        game.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        if (!input.contains(code)) { // add once to prevent dupes
                            input.add(code);
                        }
                    }
                });
        game.setOnKeyReleased(
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
        //HPBar hp = new HPBar();
        HPBar tank1Hp = new HPBar(tank1);
        HPBar tank2Hp = new HPBar(tank2);
        Explosion explosion1 = new Explosion();
        WinnerCelebration winner = new WinnerCelebration(tank1);

        new AnimationTimer() { // Now start the game loop (animation)
            @Override
            public void handle(long now) {
                context.clearRect(0, 0, 1280, 720); // Clear the context from previous drawings
                background.draw(context);
                terrain.draw(context); // draw terrain first
                tank1Hp.draw(context);
                tank2Hp.draw(context);
                tank1.draw(context); // then tanks (meaning tanks are drawn on top of terrain if same coordinates
                tank2.draw(context);
                explosion1.draw(context);
                if (tank1Hp.getHP() == 0){
                    winner.draw(context);
                }
                processInput();
            }
            void processInput() {
                if (input.contains("Q") && press){ //Test button
                    System.out.println("Hit");
                    tank1Hp.damage = 160;
                    tank1Hp.isHit = true;
                    press = false;
                }
                if (input.contains("A")) {
                    tank1.move(-2);
                }
                if (input.contains("D")) {
                    tank1.move(+2);
                }
                if (input.contains("SPACE")) {
                    //tank1.fires(new Projectile(new Image("file:resources/projectile.png"),
                            //tank1.getX2(), tank1.getY2()));
                    explosion1.setPos(tank1.getX1(), tank1.getY1());
                }
                if (input.contains("LEFT")) {
                    tank2.move(-2);
                }
                if (input.contains("RIGHT")) {
                    tank2.move(+2);
                }
                if (input.contains("NUMPAD0")) {
                   // tank2.fires(new Projectile(new Image("file:resources/projectile.png"),
                       //     tank2.getX2(), tank2.getY2()));
                }
            }
        }.start();
        primaryStage.show();
    }

    private void closeProgram(){
        window.close();
    }
}
