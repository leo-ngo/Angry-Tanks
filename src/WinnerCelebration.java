import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Random;

public class WinnerCelebration {

    private String tank;
    private Image[] images = new Image[94];
    private int imageNum = 0;
    private Random random;

    public WinnerCelebration() {
        random = new Random();
        x = random.nextInt(680);
        y = random.nextInt(360);
        for (int i = 0; i < 47; i++){
            this.images[i*2] = new Image("file:resources/firework/" + i +".png");
            this.images[i*2+1] = new Image("file:resources/firework/" + i +".png");
        }
    }

    private double x;
    private double y;
    public void draw(GraphicsContext context){
        context.setStroke(Color.BLACK);
        context.setLineWidth(1);
        context.setFont(new Font(15));

        String name = tank;
        if (imageNum < 94) {
            context.drawImage(images[imageNum], x, y);
            imageNum++;
            if (imageNum == 94) {
                imageNum = 0;
                x = random.nextInt(680);
                y = random.nextInt(360);
            }
        }
        if (name == null) {
            context.strokeText("It's a draw!!", 600, 340, 100);
        } else if (name.equals("tank1")){
            context.strokeText("Player 1 Won!!", 600, 340, 100);
        } else if (name.equals("tank2")){
            context.strokeText("Player 2 Won!!", 600, 340, 100);
        }
    }

    public void setTank(String tank) {
        this.tank = tank;
    }
}
