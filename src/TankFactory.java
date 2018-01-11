import javafx.scene.image.Image;

public class TankFactory {

    public static Tank createTank(String type) {
        Tank tank;
        if (type == "tank1") {
            tank = new Tank(new Image("file:resources/greentank_body.png"));
            tank.setLength(15);
            tank.setPosition(100, 3);
            tank.setGun(new Image("file:resources/greentank_gun.png"));
            tank.getGun().setOffsets(11, 11);
            tank.setName("tank1");
            return tank;
        }
        if (type == "tank2") {
            tank = new Tank(new Image("file:resources/graytank_body.png"));
            tank.setLength(15);
            tank.setPosition(595, 7);
            tank.setGun(new Image("file:resources/graytank_gun.png"));
            tank.getGun().setOffsets(11, -3);
            tank.setName("tank2");
            return tank;
        }
        return null;
    }

}
