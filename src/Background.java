import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Background {
    private double x, y;
    public void draw(GraphicsContext context){
        context.drawImage(new Image("file:resources/BlueSky.png"), 0, 0);

        context.drawImage(new Image("file:resources/clouds.png"), x, 0);
        x++;
        if (x == Main.CANVAS_WIDTH -1000){
            x= -1280;
        }
    }


    public Background() {
    }
}
