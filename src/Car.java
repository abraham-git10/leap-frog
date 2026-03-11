public class Car extends FroggerItem{
    public static final int CAR_1 = 0;
    public static final int CAR_2 = 1;
    public static final int LIMO = 2;
    public static final int SEMI = 3;
    public Car(double x, double y, int direction, double speed, int type) {
        super(x, y, direction, speed, type);
    }
    public int getWidth() {
        if(getType() == CAR_1 || getType() == CAR_2) {
            return 40;
        }
        if(getType() == LIMO) {
            return 80;
        }
        if(getType() == SEMI) {
            return 120;
        }
        return 0;
    }
}
