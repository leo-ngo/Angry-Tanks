import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class WinnerCelebration {

    Tank tank;
    private Image[] images = new Image[94];
    private int imageNum = 0;



    public WinnerCelebration(Tank tank) {

        for (int i = 0; i < 47; i++){
            this.images[i*2] = new Image("file:resources/firework/" + i +".png");
            this.images[i*2+1] = new Image("file:resources/firework/" + i +".png");
        }
        this.tank = tank;
    }

    public void draw(GraphicsContext context){
        context.setStroke(Color.BLACK);
        context.setLineWidth(1);
        context.setFont(new Font(15));

        String name = tank.getName();
        if (imageNum < 94) {
            context.drawImage(images[imageNum], 500, 300);
            System.out.println("exploded " + imageNum);
            imageNum++;
        }
        if (name.equals("tank1")){

            context.strokeText("Player 1 Won!!", 640, 360, 100);

        }else if (name.equals("tank2")){
            context.strokeText("Player 2 Won!!", 640, 360, 100);
        }
    }
}
