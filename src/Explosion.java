import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Explosion {
    private Image[] images = new Image[34];
    private double x;
    private double y;
    private int imageNum;

    public Explosion(){ //constructor
        for (int i = 0; i < 34; i++){
            images[i] = new Image("file:resources/explosion/" + i +".png");
        }
    }

    public void setPos(double x, double y){ //sets position of explosion
        this.x = x - 50; //size of image is 100x100 pixel, this centers it
        this.y = y - 50;
        imageNum = 0;
    }

    public void draw(GraphicsContext context){ //draws image and cycles through array to next frame
        if (imageNum < 34) {
            context.drawImage(images[imageNum], x, y);
            imageNum++;
        }
    }




}
