/**
 RMIT University Vietnam
 Course: INTE2512 Object-Oriented Programming
 Semester: 2017C
 Assignment: 3
 Team: ALV
 Author: Vuong Hung Ngo, Long Hoang Tran, Arofando Hadi
 ID: s3610887, s3635165, s3618954
 Created date: 12/01/2018
 Acknowledgement:
 https://stackoverflow.com/questions/18260421/how-to-draw-image-rotated-on-javafx-canvas
 https://giphy.com/stickers/fireworks-transparency-NxpMNq17Y2Khq
 https://www.freesoundeffects.com/free-sounds/explosion-10070/
 */
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
