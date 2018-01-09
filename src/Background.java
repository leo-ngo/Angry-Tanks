import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Background {
    private Image sky;
    private Image cloud;
    private double x, y;
    public void draw(GraphicsContext context){
        context.drawImage(sky, 0, 0);

        context.drawImage(cloud, x, 0);
        x++;
        if (x == Main.CANVAS_WIDTH -1000){
            x= -1280;
        }
    }


    public Background() {
        this.sky = new Image("file:resources/bluesky.png");
        this.cloud = new Image("file:resources/clouds.png");
    }
}
