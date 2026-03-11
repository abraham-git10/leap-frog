import java.awt.*;
abstract class FroggerItem {
    private double x;
    private double y;
    private int direction;
    private int type;
    private double speed;
    private Rectangle rect;
    public FroggerItem(double x, double y, int direction, double speed, int type) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.speed = speed;
        this.type = type;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    abstract int getWidth();
    public int getHeight() {
        return 40;
    }
    public double getSpeed() {
        return speed;
    }
    public int getType() {
        return type;
    }
    public int getDirection() {
        return direction;
    }
    public Rectangle getRectangle() {
        return new Rectangle((int) x, (int) y, getWidth(), getHeight());
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }
    public void setRectangle(Rectangle rect) {
        this.rect = rect;
    }
    public void updateRectangle() {
        rect = new Rectangle((int) x, (int) y, getWidth(), getHeight());
    }
    public void update() {
        if (direction == Lane.LEFT) {
            x -= speed;
        } else if (direction == Lane.RIGHT) {
            x += speed;
        }
        updateRectangle();
    }
}
