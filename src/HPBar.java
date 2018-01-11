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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HPBar {
    private static final int P1_HP_FRAME_X = 10;
    private static final int P2_HP_FRAME_X = 1110;
    private static final int HP_FRAME_Y = 650;
    private static final int HP_FRAME_WIDTH = 162;
    private static final int HP_FRAME_HEIGHT = 22;
    private static final int HP_BAR_HEIGHT = 20;
    public static final double MAX_SCALED_HP = 160;
    private double scaledHP;

    private double scaledVisibleHP;

    public double scaledDamage = 0;
    public boolean isHit = false;

    private Tank tank;

    public HPBar(Tank tank) {
        this.tank = tank;
        this.scaledHP = tank.getHP() * MAX_SCALED_HP / tank.MAX_HP;
        scaledVisibleHP = this.scaledHP;
    }

    public void draw(GraphicsContext context) {
        //Draw the frame

        context.setLineWidth(2);
        context.setStroke(Color.YELLOW);
        context.strokeRect(P1_HP_FRAME_X, HP_FRAME_Y, HP_FRAME_WIDTH, HP_FRAME_HEIGHT);
        context.strokeRect(P2_HP_FRAME_X, HP_FRAME_Y, HP_FRAME_WIDTH, HP_FRAME_HEIGHT);

        context.setStroke(Color.BLACK);
        context.setLineWidth(1);
        context.setFont(new Font(15));
        context.strokeText("Player 1", P1_HP_FRAME_X, HP_FRAME_Y - 5, 100);
        context.strokeText("Player 2", P2_HP_FRAME_X + 105, HP_FRAME_Y - 5, 100);

        //Get coordinate to draw the health bar based on the name of the tank
        int x = 0;
        String name = tank.getName();
        if (name.equals("tank1")){
            x =  P1_HP_FRAME_X + 1;

        }else if (name.equals("tank2")){
            x =  P2_HP_FRAME_X + 1;

        }

        context.setFill(Color.RED);

        context.fillRect(x, HP_FRAME_Y + 1, scaledHP, HP_BAR_HEIGHT);
        if (!isHit) {       //If not hit, initialize the full scaledHP
            context.fillRect(x, HP_FRAME_Y + 1, scaledHP, HP_BAR_HEIGHT);
        }
        if (scaledDamage != 0) {    //Took scaledDamage, decrease scaledHP
            scaledHP -= scaledDamage;
            scaledDamage = 0;
        }
        context.setFill(Color.BLACK);
        decreaseHP(context, x);
        context.setFill(Color.RED);

        context.fillRect(x, HP_FRAME_Y + 1, scaledHP, HP_BAR_HEIGHT);
    }


    private void decreaseHP(GraphicsContext context, int x) {
        //Gradually decrease displayed scaledHP by comparing with real scaledHP
        //The HP will be decreased frame by frame

        if (scaledHP < scaledVisibleHP) { //Gradually decrease displayed scaledHP by comparing with real scaledHP
            scaledVisibleHP -= 0.5;
            context.fillRect(x, HP_FRAME_Y + 1, scaledVisibleHP, HP_BAR_HEIGHT);
        } else if (scaledHP != scaledVisibleHP) { //avoid go overboard
            scaledVisibleHP = scaledHP;
            context.fillRect(x, HP_FRAME_Y + 1, scaledVisibleHP, HP_BAR_HEIGHT);
        }
        else {
            context.fillRect(x, HP_FRAME_Y + 1, scaledVisibleHP, HP_BAR_HEIGHT);
        }


    }

    public void takesDamage(int damage) {
        this.scaledDamage = damage * MAX_SCALED_HP / Tank.MAX_HP;
        if (!isHit) isHit = true;
    }
}
