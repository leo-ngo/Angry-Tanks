import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Background {
    private Image sky;
    private Image cloud;
    private double x, x1;
    public void draw(GraphicsContext context){
        context.drawImage(sky, 0, 0);

        context.drawImage(cloud, x, 0);
        context.drawImage(cloud, x1, 0);
        x++;
        x1++;
        if (x == Main.CANVAS_WIDTH){
            x = -Main.CANVAS_WIDTH;
        }
        if (x1 == Main.CANVAS_WIDTH){
            x1 = -Main.CANVAS_WIDTH;
        }

    }


    public Background() {
        this.sky = new Image("file:resources/bluesky.png");
        this.cloud = new Image("file:resources/clouds.png");
        x1 = -Main.CANVAS_WIDTH;
    }
}
