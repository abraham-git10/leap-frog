public class Turtle extends FroggerItem {
    public static final int ONE_TURTLE = 0;
    public static final int TWO_TURTLE = 1;
    public static final int THREE_TURTLE = 2;
    public static final int UP = 0;
    public static final int HALF_DOWN = 1;
    public static final int DOWN = 2;
    public static final int HALF_UP = 3;
    public static final int ALWAYS_UP = 4;
    private int mode;
    private int timer;
    private long st;
    public Turtle(double x, double y, int direction, double speed, int type) {
        super(x, y, direction, speed, type);
        int m = (int) (Math.random() * 4);
        mode = m;
        timer = 3;
        st = System.nanoTime();
    }
    public void update() {
        super.update();
    }
    public int getMode() {
        return mode;
    }
    public int getWidth() {
        if(getType() == ONE_TURTLE) {
            return 40;
        }
        if(getType() == TWO_TURTLE) {
            return 80;
        }
        if(getType() == THREE_TURTLE) {
            return 120;
        }
        return 0;
    }
}