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
    private int HP = 160;

    private int VisibleHp = 160;

    public int damage = 0;
    public boolean isHit = false;
    private Tank tank;


    public HPBar(Tank tank) {
        this.tank = tank;
    }

    public void draw(GraphicsContext context) {
        //Draw the frame
        int x = 0;
        String name = tank.getName();
        if (name.equals("tank1")){
            x =  P1_HP_FRAME_X + 1;

        }else if (name.equals("tank2")){
            x =  P2_HP_FRAME_X + 1;

        }
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
        context.fillRect(x, HP_FRAME_Y + 1, HP, HP_BAR_HEIGHT);
        if (!isHit) {       //If not hit, initialize the full HP
            context.fillRect(x, HP_FRAME_Y + 1, HP, HP_BAR_HEIGHT);
        }
        if (damage != 0) {    //Took damage, decrease HP
            HP -= damage;
            damage = 0;
            System.out.println("Current Hp" + HP);
            System.out.println("Dam" + damage);

        }

        decreaseHP(context, x);

    }


    private void decreaseHP(GraphicsContext context, int x) {


        if (HP < VisibleHp) { //Gradually decrease displayed HP by comparing with real HP
            VisibleHp -= 1;
            context.fillRect(x, HP_FRAME_Y + 1, VisibleHp, HP_BAR_HEIGHT);
        } else if (HP != VisibleHp) { //avoid go overboard
            VisibleHp = HP;
            context.fillRect(x, HP_FRAME_Y + 1, VisibleHp, HP_BAR_HEIGHT);
        }
        else {
            context.fillRect(x, HP_FRAME_Y + 1, VisibleHp, HP_BAR_HEIGHT);
        }


    }


}
