import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Timer;

public class HPBar {
    private static final double P1_HP_FRAME_X = 10;
    private static final double P2_HP_FRAME_X = 1110;
    private static final double HP_FRAME_Y = 650;
    private static final double HP_FRAME_WIDTH = 162;
    private static final double HP_FRAME_HEIGHT = 22;
    private static final double HP_BAR_HEIGHT = 20;
    public static double P1HP = 160;
    public static double P2HP = 160;
    private static double p1VisibleHp = 160;
    public static double p1Damage = 0;
    public static double p2Damage = 0;
    public static boolean isHit = false;
    Timer timer = new Timer();
    public void draw(GraphicsContext context)
    {
        //Draw the frame
        context.setLineWidth(2);
        context.setStroke(Color.YELLOW);
        context.strokeRect(P1_HP_FRAME_X, HP_FRAME_Y, HP_FRAME_WIDTH, HP_FRAME_HEIGHT);
        context.strokeRect(P2_HP_FRAME_X, HP_FRAME_Y, HP_FRAME_WIDTH, HP_FRAME_HEIGHT);

        context.setStroke(Color.BLACK);
        context.setLineWidth(1);
        context.setFont(new Font(15));
        context.strokeText("Player 1", P1_HP_FRAME_X, HP_FRAME_Y - 5, 100);
        context.strokeText("Player 2", P2_HP_FRAME_X + 108, HP_FRAME_Y - 5, 100);


        context.setFill(Color.RED);
        //context.fillRect(P1_HP_FRAME_X + 1,HP_FRAME_Y + 1, p1HpBar, HP_BAR_HEIGHT);
        context.fillRect(P2_HP_FRAME_X + 1, HP_FRAME_Y + 1, P2HP, HP_BAR_HEIGHT);

        if (!isHit){       //If not hit, initialize the full HP
            context.fillRect(P1_HP_FRAME_X + 1, HP_FRAME_Y + 1, P1HP, HP_BAR_HEIGHT);
        }

        if (p1Damage != 0) {    //Took damage, decrease HP
            HPBar.P1HP -= p1Damage;
            System.out.println("Current Hp" + P1HP);
            System.out.println("Dam" + p1Damage);
            HPBar.p1Damage = 0;   //Reset damage to 0
        }
        if (P1HP < p1VisibleHp){ //Gradually decrease displayed HP by comparing with real HP
            p1VisibleHp -= 1;
            System.out.println(p1VisibleHp);
            context.fillRect(P1_HP_FRAME_X + 1, HP_FRAME_Y + 1, p1VisibleHp, HP_BAR_HEIGHT);
        }else if (P1HP != p1VisibleHp){ //avoid go overboard
            p1VisibleHp = P1HP;
            context.fillRect(P1_HP_FRAME_X + 1, HP_FRAME_Y + 1, p1VisibleHp, HP_BAR_HEIGHT);
        }else{
            context.fillRect(P1_HP_FRAME_X + 1, HP_FRAME_Y + 1, p1VisibleHp, HP_BAR_HEIGHT);
        }

        /*if (p1Damage != 0){

            context.fillRect(P1_HP_FRAME_X + 1,HP_FRAME_Y + 1, P1HP, HP_BAR_HEIGHT);
        }else{
            if (p1Damage !=0){
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                            context.fillRect(P1_HP_FRAME_X + 1,HP_FRAME_Y + 1, 160 - 1, HP_BAR_HEIGHT);
                            p1Damage--;
                    }
                }, 16);
            }
        }*/


    }


}
