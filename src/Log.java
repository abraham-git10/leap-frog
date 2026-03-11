public class Log extends FroggerItem {
    public static final int SHORT = 0;
    public static final int MEDIUM = 1;
    public static final int LONG = 2;
    public Log(double x, double y, int direction, double speed, int type) {
        super(x, y, direction, speed, type);
    }
    public int getWidth() {
        if(getType() == SHORT) {
            return 80;
        }
        if(getType() == MEDIUM) {
            return 120;
        }
        if(getType() == LONG) {
            return 200;
        }
        return 0;
    }
}
