import java.util.ArrayList;
public class LogLane extends Lane {
    public LogLane(int y, int direction, double speed) {
        super(y, direction, speed);
    }
    public void update() {
        super.update();
        ArrayList<FroggerItem> it = getItems();
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 0;i < it.size();i++) {
            if(it.get(i).getX() < -40) {
                it.remove(i);
                arr.add(i);
            }
            else if(it.get(i).getX() > 1000) {
                it.remove(i);
                arr.add(i);
            }
        }
        for(int i = 0;i < arr.size();i++) {
            int x = 0;
            if(getDirection() == LEFT) {
                x = 960;
            }
            it.add(arr.get(i), new Log(x, getY(), getDirection(), getSpeed(), (int) (Math.random() * 3)));
        }
    }
}
