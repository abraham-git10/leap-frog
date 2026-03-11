public class FroggerGame {
    public static final int PLAYING = 0;
    public static final int DEAD = 1;
    public static final int PLAYER_WINS = 2;
    public static final int MAX_LIFE_TIME = 30;
    private int status;
    private long startLifeTime;
    private boolean reachedMiddle;
    private int lives;
    private Frog player;
    private LogLane[] logLanes;
    private TurtleLane[] turtleLanes;
    private CarLane[] carLanes;
    private LilyPad[] lilyPads;
    private int current;
    public FroggerGame() {
        startLifeTime = System.nanoTime();
        status = PLAYING;
        player = new Frog(480, 655);
        lives = 3;
        reachedMiddle = false;
        logLanes = new LogLane[3];
        turtleLanes = new TurtleLane[2];
        carLanes = new CarLane[5];
        lilyPads = new LilyPad[4];
        for(int i = 0;i < 5;i++) {
            int d = 0;
            if(i % 2 == 0) {
                d = Lane.LEFT;
            }
            else {
                d = Lane.RIGHT;
            }
            double s = Math.random() * 2.5 + 1;
            carLanes[i] = new CarLane(410 + i * 50, d, s);
            int o = 0;
            if(i > 0) {
                o = i + 1;
            }
            if(i < 3) {
                logLanes[i] = new LogLane(110 + o * 50, Lane.RIGHT, 1.2);
            }
            o = 1;
            if(i > 0) {
                o = i + 3;
            }
            if(i < turtleLanes.length) {
                turtleLanes[i] = new TurtleLane(110 + o * 50, Lane.LEFT, 1.2);
            }
            if(i < 4) {
                if(i == 0) {
                    lilyPads[i] = new LilyPad(238, 25);
                }
                if(i == 1) {
                    lilyPads[i] = new LilyPad(388, 25);
                }
                if(i == 2) {
                    lilyPads[i] = new LilyPad(538, 25);
                }
                if(i == 3) {
                    lilyPads[i] = new LilyPad(688, 25);
                }
            }
        }
        for(int i = 0; i < 5;i++) {
            for(int c = 1;c <= 3;c++) {
                ((Lane) carLanes[i]).getItems().add(c - 1, new Car(c * 250, carLanes[i].getY(), carLanes[i].getDirection(), carLanes[i].getSpeed(), (int) (Math.random() * 4)));
            }
        }
        for(int i = 0; i < 2;i++) {
            for(int c = 1;c <= 3;c++) {
                ((Lane) turtleLanes[i]).getItems().add(c - 1, new Turtle(c * 300, turtleLanes[i].getY(), turtleLanes[i].getDirection(), turtleLanes[i].getSpeed(), (int) (Math.random() * 3)));
            }
        }
        for(int i = 0; i < 3;i++) {
            for(int c = 1;c <= 3;c++) {
                ((Lane) logLanes[i]).getItems().add(c - 1, new Log(c * 200, logLanes[i].getY(), logLanes[i].getDirection(), logLanes[i].getSpeed(), (int) (Math.random() * 3)));
            }
        }
    }
    public void update() {
        for(int i = 0;i < logLanes.length;i++) {
            logLanes[i].update();
        }
        for(int i = 0;i < carLanes.length;i++) {
            carLanes[i].update();
        }
        for(int i = 0;i < turtleLanes.length;i++) {
            turtleLanes[i].update();
        }
        if(player.getY() < 370) {
            reachedMiddle = true;
        }
        runChecks();
    }
    public int status() {
        return status;
    }
    public Frog getPlayer() {
        return player;
    }
    public LogLane[] getLogLanes() {
        return logLanes;
    }
    public TurtleLane[] getTurtleLanes() {
        return turtleLanes;
    }
    public CarLane[] getCarLanes() {
        return carLanes;
    }
    public LilyPad[] getLilyPads() {
        return lilyPads;
    }
    public int getLives() {
        return lives;
    }
    public void playerDeath() {
        lives--;
        if(lives == 0) {
            status = DEAD;
        }
        startLifeTime = System.nanoTime();
        if(reachedMiddle) {
            player.setY(360);
            player.setX(480);
        }
        else {
            player.setY(655);
            player.setX(480);
        }
        startLifeTime = System.nanoTime();
    }
    public int getTimeLeft() {
        if(status != PLAYING) {
            return 0;
        }
        return MAX_LIFE_TIME - (int) ((System.nanoTime() - startLifeTime)/1000000000);
    }
    public void carCheck() {
        for(int i = 0;i < carLanes.length;i++) {
            for(int a = 0; a < ((Lane) carLanes[i]).getItems().size();a++) {
                if(((Lane) carLanes[i]).getItems().get(a).getRectangle().intersects(player.getRectangle())) {
                    playerDeath();
                }
            }
        }
    }
    public void logCheck() {
        boolean onLog = false;
        for (LogLane logLane : logLanes) {
            for (FroggerItem log : logLane.getItems()) {
                if (log.getRectangle().intersects(player.getRectangle())) {
                    onLog = true;
                    player.setX(log.getX() + 15);
                    break;
                }
            }
            if (onLog) {
                break;
            }
        }
        if (!onLog &&
                (player.getY() > 70 && player.getY() < 120 || player.getY() > 170 && player.getY() < 270)) {
            playerDeath();
        }
    }
    public void turtleCheck() {
        boolean onTurtle = false;
        for (TurtleLane turtleLane : turtleLanes) {
            for (FroggerItem turtle : turtleLane.getItems()) {
                if (turtle.getRectangle().intersects(player.getRectangle())) {
                    onTurtle = true;
                    player.setX(turtle.getX() + 15);
                    break;
                }
            }
            if (onTurtle) {
                break;
            }
        }
        if (!onTurtle &&
                (player.getY() > 120 && player.getY() < 170 || player.getY() > 270 && player.getY() < 320)) {
            playerDeath();
        }
    }
    public void lilyCheck() {
        for (LilyPad lilyPad : lilyPads) {
            if (player.getY() < 100) {
                for (int i = 0; i < lilyPads.length; i++) {
                    if(lilyPads[i].getY() == 0) {
                        player.setY(75);
                    }
                    else if (lilyPads[i].getX() < player.getX() + 20 && lilyPads[i].getX() > player.getX() - 20) {
                        lilyPads[i].setY(0);
                        player.setX(480);
                        player.setY(655);
                        reachedMiddle = false;
                        startLifeTime = System.nanoTime();
                        break;
                    }
                }
            }
        }
        int p = 0;
        for(int i = 0;i < lilyPads.length;i++) {
            if(lilyPads[i].getY() == 0) {
                p++;
            }
        }
        if(p == 4) {
            status = PLAYER_WINS;
        }
    }
    public void runChecks() {
        if(player.getY() < 650 && player.getY() > 320) {
            carCheck();
        }
        if(player.getY() > 120 && player.getY() < 170 || player.getY() > 270 && player.getY() < 320) {
            turtleCheck();
        }
        else if(player.getY() > 70 && player.getY() < 120 || player.getY() > 170 && player.getY() < 270) {
            logCheck();
        }
        lilyCheck();
    }
}
