/**
 RMIT University Vietnam
 Course: INTE2512 Object-Oriented Programming
 Semester: 2017C
 Assignment: 3
 Team: ALV
 Author: Vuong Hung Ngo, Long Hoang Tran, Arofando Hadi
 ID: s3610887, s3635165, s3618954
 Created date: 12/01/2018
 Acknowledgement:  https://stackoverflow.com/questions/18260421/how-to-draw-image-rotated-on-javafx-canvas
 https://giphy.com/stickers/fireworks-transparency-NxpMNq17Y2Khq
 */
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Background {
    private Image sky;
    private Image cloud;
    private double x, x1;
    public void draw(GraphicsContext context){
        context.drawImage(sky, 0, 0); //Draw base background

        context.drawImage(cloud, x, 0); //Draw the first cloud background, then the second one on the right side of the
        //first one, hidden from the scene
        context.drawImage(cloud, x1, 0);
        x++; //Gradually move the cloud to the right
        x1++;
        if (x == Main.CANVAS_WIDTH){    //When the image is outside of the scene, put it back to the left side of the scene
            x = -Main.CANVAS_WIDTH;
        }
        if (x1 == Main.CANVAS_WIDTH){
            x1 = -Main.CANVAS_WIDTH;
        }

    }


    public Background() {   //Initialize the images
        this.sky = new Image("file:resources/SkyBackground.png");
        this.cloud = new Image("file:resources/clouds.png");
        x1 = -Main.CANVAS_WIDTH;
    }
}
