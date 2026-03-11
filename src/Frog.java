import java.awt.*;
public class Frog extends FroggerItem {
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int UP = 3;
    private Rectangle waterRect;
    public Frog(double x, double y) {
        super(x, y, UP, 50, 0);
    }
    public int getWidth() {
        return 40;
    }
    @Override
    public void updateRectangle() {
        waterRect = new Rectangle((int) getX(), (int) getY(), 24, 22);
    }
    public Rectangle getWaterRectangle() {
        return waterRect;
    }
}