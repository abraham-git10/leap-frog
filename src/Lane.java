import java.util.*;
public class Lane {
    private int y;
    private int direction;
    private double speed;
    private ArrayList<FroggerItem> items;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public Lane(double y, int direction, double speed) {
        this.y = (int) y;
        this.direction = direction;
        this.speed = speed;
        items = new ArrayList<>();
    }
    public int getY() {
        return y;
    }
    public double getSpeed() {
        return speed;
    }
    public int getDirection() {
        return direction;
    }
    public void update() {
        for(int i = 0;i < items.size();i++) {
            if (direction == LEFT) {
                items.get(i).setX(items.get(i).getX() - speed);
            }
            else if(direction == RIGHT) {
                items.get(i).setX(items.get(i).getX() + speed);
            }
        }
    }
    public ArrayList<FroggerItem> getItems() {
        return items;
    }
}
