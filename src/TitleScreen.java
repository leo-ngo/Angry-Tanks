import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;



public class TitleScreen {

    private Image start, exit;
    private boolean ok;

    TitleScreen(){
        start = new Image("file:resources/start.png");
        exit= new Image("file:resources/exit.png");
        ok = true;
    }

    public void draw(GraphicsContext context){
        //context.clearRect(0, 0, 1280, 720);
        if (ok)
            context.drawImage(start, 0, 0);
        else
            context.drawImage(exit, 0, 0);
        }

    public void changeOK(){
        ok = !ok;
    }

    public boolean isOK() {
        return ok;
    }
}
