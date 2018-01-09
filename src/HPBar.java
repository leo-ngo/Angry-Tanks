import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class HPBar {
    private static final double P1_HP_FRAME_X = 10;
    private static final double P1_HP_FRAME_Y = 650;
    private static final double P2_HP_FRAME_X = 1110;
    private static final double P2_HP_FRAME_Y = 650;
    private static final double HP_FRAME_WIDTH = 162;
    private static final double HP_FRAME_HEIGHT = 22;
    private static final double HP_BAR_WIDTH = 160;
    private static final double HP_BAR_HEIGHT = 20;
    public void draw(GraphicsContext context)
    {
        context.setLineWidth(2);
        context.setStroke(Color.YELLOW);
        context.strokeRect(P1_HP_FRAME_X, P1_HP_FRAME_Y, HP_FRAME_WIDTH, HP_FRAME_HEIGHT);
        context.strokeRect(P2_HP_FRAME_X, P2_HP_FRAME_Y, HP_FRAME_WIDTH, HP_FRAME_HEIGHT);

        context.setFill(Color.RED);
        context.fillRect(P1_HP_FRAME_X + 1,P1_HP_FRAME_Y + 1, HP_BAR_WIDTH, HP_BAR_HEIGHT);
        context.fillRect(P2_HP_FRAME_X + 1,P2_HP_FRAME_Y + 1, HP_BAR_WIDTH, HP_BAR_HEIGHT);



    }
}
