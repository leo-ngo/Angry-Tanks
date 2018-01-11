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
import javafx.scene.image.Image;

public class TankFactory {

    public static Tank createTank(String type) {
        Tank tank;
        if (type == "tank1") {
            tank = new Tank(new Image("file:resources/greentank_body.png"));
            tank.setName("tank1");
            tank.setLength(15);
            tank.setPosition(100, 3);
            tank.setMirrored(false);
            tank.setGun(new Image("file:resources/greentank_gun.png"));
            Gun gun = tank.getGun();
            gun.setOffsets(11, 11);
            return tank;
        }
        if (type == "tank2") {
            tank = new Tank(new Image("file:resources/graytank_body.png"));
            tank.setName("tank2");
            tank.setLength(15);
            tank.setPosition(595, 7);
            tank.setMirrored(true);
            tank.setGun(new Image("file:resources/graytank_gun.png"));
            Gun gun = tank.getGun();
            gun.setOffsets(11, -3);
            return tank;
        }
        return null;
    }

}
