import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class FroggerPanel extends JPanel implements KeyListener, Runnable {
    private FroggerGame game;
    private BufferedImage buffer;
    private int updatesPerSecond;
    private long startTime;
    private long updateCount;
    private BufferedImage car1_Left;
    private BufferedImage car1_Right;
    private BufferedImage car2_Left;
    private BufferedImage car2_Right;
    private BufferedImage limo_Left;
    private BufferedImage limo_Right;
    private BufferedImage semi_Left;
    private BufferedImage semi_Right;
    private BufferedImage frogUp;
    private BufferedImage frogDown;
    private BufferedImage frogLeft;
    private BufferedImage frogRight;
    private BufferedImage hsTurtle;
    private BufferedImage hmTurtle;
    private BufferedImage hlTurtle;
    private BufferedImage sTurtle;
    private BufferedImage mTurtle;
    private BufferedImage lTurtle;
    private BufferedImage sLog;
    private BufferedImage mLog;
    private BufferedImage lLog;
    private BufferedImage lilyPad;
    private BufferedImage frogLife;
    public FroggerPanel(int w, int h) {
        super();
        setPreferredSize(new Dimension(1000, 800));
        reset();
        Thread tg = new Thread(this);
        tg.start();
        addKeyListener(this);
        try {
            car1_Left = ImageIO.read(FroggerPanel.class.getResource("/ImagePackage/Car 1 - Left.png"));
            car1_Right = ImageIO.read(FroggerPanel.class.getResource("/ImagePackage/Car 1 - Right.png"));
            car2_Left = ImageIO.read(FroggerPanel.class.getResource("/ImagePackage/Car 2 - Left.png"));
            car2_Right = ImageIO.read(FroggerPanel.class.getResource("/ImagePackage/Car 2 - Right.png"));
            frogDown = ImageIO.read(FroggerPanel.class.getResource("/ImagePackage/Frog Down.png"));
            frogLeft = ImageIO.read(FroggerPanel.class.getResource("/ImagePackage/Frog Left.png"));
            frogLife = ImageIO.read(FroggerPanel.class.getResource("/ImagePackage/Frog Life.png"));
            frogRight = ImageIO.read(FroggerPanel.class.getResource("/ImagePackage/Frog Right.png"));
            frogUp = ImageIO.read(FroggerPanel.class.getResource("/ImagePackage/Frog Up.png"));
            hlTurtle = ImageIO.read(FroggerPanel.class.getResource("/ImagePackage/HL-Turtle.png"));
            hmTurtle = ImageIO.read(FroggerPanel.class.getResource("/ImagePackage/HM-Turtle.png"));
            hsTurtle = ImageIO.read(FroggerPanel.class.getResource("/ImagePackage/HS-Turtle.png"));
            lLog = ImageIO.read(FroggerPanel.class.getResource("/ImagePackage/L-Log.png"));
            lTurtle = ImageIO.read(FroggerPanel.class.getResource("/ImagePackage/L-Turtle.png"));
            lilyPad = ImageIO.read(FroggerPanel.class.getResource("/ImagePackage/lilyPad.png"));
            limo_Left = ImageIO.read(FroggerPanel.class.getResource("/ImagePackage/Limo - Left.png"));
            limo_Right = ImageIO.read(FroggerPanel.class.getResource("/ImagePackage/Limo - Right.png"));
            mLog = ImageIO.read(FroggerPanel.class.getResource("/ImagePackage/M-Log.png"));
            mTurtle = ImageIO.read(FroggerPanel.class.getResource("/ImagePackage/M-Turtle.png"));
            sLog = ImageIO.read(FroggerPanel.class.getResource("/ImagePackage/S-Log.png"));
            sTurtle = ImageIO.read(FroggerPanel.class.getResource("/ImagePackage/S-Turtle.png"));
            semi_Left = ImageIO.read(FroggerPanel.class.getResource("/ImagePackage/Semi - Left.png"));
            semi_Right = ImageIO.read(FroggerPanel.class.getResource("/ImagePackage/Semi - Right.png"));
        }
        catch(Exception e) {
            System.out.println("Error");
            return;
        }
    }
    public void reset() {
        startTime = System.nanoTime();
        updatesPerSecond = 40;
        game = new FroggerGame();
        buffer = new BufferedImage(1000, 800, BufferedImage.TYPE_4BYTE_ABGR);
    }
    public void paint(Graphics y) {
        super.paintComponent(y);
        Graphics g = buffer.getGraphics();
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, 1000, 750);
        g.setColor(Color.BLUE);
        g.fillRect(0, 70, 1000, 250);
        g.fillRect(220, 15, 75, 55);
        g.fillRect(370, 15, 75, 55);
        g.fillRect(520, 15, 75, 55);
        g.fillRect(670, 15, 75, 55);
        g.setColor(Color.GRAY);
        g.fillRect(0, 370, 1000, 250);
        g.setColor(Color.YELLOW);
        int u = 450;
        for(int p = 0; p < 4; p++) {
            g.fillRect(10, u - 30, 80, 5);
            for (int i = 1; i <= 10; i++) {
                g.fillRect(i * 10 + 100 * i, u - 30, 80, 5);
            }
            u += 50;
        }
        g.setColor(Color.BLACK);
        g.fillRect(0, getHeight() - 30, 1000, 30);
        g.setFont(new Font("Calibre", Font.BOLD, 15));
        g.setColor(Color.RED);
        g.drawString("Lives:", 20, getHeight() - 15);
        for(int i = 1;i <= game.getLives();i++) {
            g.drawImage(frogLife, 40 + i * 30, getHeight() - 27, null);
        }
        if(game.getTimeLeft() <= 0) {
            game.playerDeath();
        }
        g.drawString("Time Left:", 720, getHeight() - 15);
        if(game.getTimeLeft() > 15) {
            g.setColor(Color.GREEN);
        }
        if(game.getTimeLeft() <= 15) {
            g.setColor(Color.YELLOW);
        }
        if(game.getTimeLeft() <= 10) {
            g.setColor(Color.RED);
        }
        g.fillRect(800, getHeight() - 25, game.getTimeLeft() * 4, 15);
        CarLane[] cl = game.getCarLanes();
        for (CarLane carLane : cl) {
            for (FroggerItem car : carLane.getItems()) {
                if (car.getType() == Car.CAR_1 && carLane.getDirection() == Lane.LEFT) {
                    g.drawImage(car1_Left, (int) car.getX(), (int) car.getY() - 30, null);
                } else if (car.getType() == Car.CAR_1 && carLane.getDirection() == Lane.RIGHT) {
                    g.drawImage(car1_Right, (int) car.getX(), (int) car.getY() - 30, null);
                } else if (car.getType() == Car.CAR_2 && carLane.getDirection() == Lane.LEFT) {
                    g.drawImage(car2_Left, (int) car.getX(), (int) car.getY() - 30, null);
                } else if (car.getType() == Car.CAR_2 && carLane.getDirection() == Lane.RIGHT) {
                    g.drawImage(car2_Right, (int) car.getX(), (int) car.getY() - 30, null);
                } else if (car.getType() == Car.LIMO && carLane.getDirection() == Lane.LEFT) {
                    g.drawImage(limo_Left, (int) car.getX(), (int) car.getY() - 30, null);
                } else if (car.getType() == Car.LIMO && carLane.getDirection() == Lane.RIGHT) {
                    g.drawImage(limo_Right, (int) car.getX(), (int) car.getY() - 30, null);
                } else if (car.getType() == Car.SEMI && carLane.getDirection() == Lane.LEFT) {
                    g.drawImage(semi_Left, (int) car.getX(), (int) car.getY() - 30, null);
                } else if (car.getType() == Car.SEMI && carLane.getDirection() == Lane.RIGHT) {
                    g.drawImage(semi_Right, (int) car.getX(), (int) car.getY() - 30, null);
                }
            }
        }
        LogLane[] ll = game.getLogLanes();
        for (LogLane logLane : ll) {
            for (FroggerItem log : logLane.getItems()) {
                if (log.getType() == Log.SHORT && logLane.getDirection() == Lane.LEFT) {
                    g.drawImage(sLog, (int) log.getX(), (int) log.getY() - 30, null);
                } else if (log.getType() == Log.SHORT && logLane.getDirection() == Lane.RIGHT) {
                    g.drawImage(sLog, (int) log.getX(), (int) log.getY() - 30, null);
                } else if (log.getType() == Log.MEDIUM && logLane.getDirection() == Lane.LEFT) {
                    g.drawImage(mLog, (int) log.getX(), (int) log.getY() - 30, null);
                } else if (log.getType() == Log.MEDIUM && logLane.getDirection() == Lane.RIGHT) {
                    g.drawImage(mLog, (int) log.getX(), (int) log.getY() - 30, null);
                } else if (log.getType() == Log.LONG && logLane.getDirection() == Lane.LEFT) {
                    g.drawImage(lLog, (int) log.getX(), (int) log.getY() - 30, null);
                } else if (log.getType() == Log.LONG && logLane.getDirection() == Lane.RIGHT) {
                    g.drawImage(lLog, (int) log.getX(), (int) log.getY() - 30, null);
                }
            }
        }
        TurtleLane[] tl = game.getTurtleLanes();
        for (TurtleLane turtleLane : tl) {
            for (FroggerItem turtle : turtleLane.getItems()) {
                if (turtle.getType() == Turtle.ONE_TURTLE && turtleLane.getDirection() == Lane.LEFT) {
                    g.drawImage(sTurtle, (int) turtle.getX(), (int) turtle.getY() - 30, null);
                } else if (turtle.getType() == Turtle.ONE_TURTLE && turtleLane.getDirection() == Lane.RIGHT) {
                    g.drawImage(sTurtle, (int) turtle.getX(), (int) turtle.getY() - 30, null);
                } else if (turtle.getType() == Turtle.TWO_TURTLE && turtleLane.getDirection() == Lane.LEFT) {
                    g.drawImage(mTurtle, (int) turtle.getX(), (int) turtle.getY() - 30, null);
                } else if (turtle.getType() == Turtle.TWO_TURTLE && turtleLane.getDirection() == Lane.RIGHT) {
                    g.drawImage(mTurtle, (int) turtle.getX(), (int) turtle.getY() - 30, null);
                } else if (turtle.getType() == Turtle.THREE_TURTLE && turtleLane.getDirection() == Lane.LEFT) {
                    g.drawImage(lTurtle, (int) turtle.getX(), (int) turtle.getY() - 30, null);
                } else if (turtle.getType() == Turtle.THREE_TURTLE && turtleLane.getDirection() == Lane.RIGHT) {
                    g.drawImage(lTurtle, (int) turtle.getX(), (int) turtle.getY() - 30, null);
                }
            }
        }
        if(game.getPlayer().getDirection() == Frog.UP) {
            g.drawImage(frogUp, (int) game.getPlayer().getX(), (int) game.getPlayer().getY() - 30, null);
        }
        if(game.getPlayer().getDirection() == Frog.LEFT) {
            g.drawImage(frogLeft, (int) game.getPlayer().getX(), (int) game.getPlayer().getY() - 30, null);
        }
        if(game.getPlayer().getDirection() == Frog.RIGHT) {
            g.drawImage(frogRight, (int) game.getPlayer().getX(), (int) game.getPlayer().getY() - 30, null);
        }
        if(game.getPlayer().getDirection() == Frog.DOWN) {
            g.drawImage(frogDown, (int) game.getPlayer().getX(), (int) game.getPlayer().getY() - 30, null);
        }
        g.drawImage(lilyPad, 238, 25, null);
        g.drawImage(lilyPad, 388, 25, null);
        g.drawImage(lilyPad, 538, 25, null);
        g.drawImage(lilyPad, 688, 25, null);
        for(int i = 0; i < game.getLilyPads().length;i++) {
            if(game.getLilyPads()[i].getY() == 0) {
                g.drawImage(frogUp, (int) game.getLilyPads()[i].getX(), 25, null);
            }
        }
        if(game.status() == FroggerGame.DEAD) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Calibre", Font.BOLD, 70));
            g.drawString("You lost. ('n' for new game)", 50, 195);
        }
        if(game.status() == FroggerGame.PLAYER_WINS) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Calibre", Font.BOLD, 70));
            g.drawString("You win!! ('n' for new game)", 50, 195);
        }
        g.dispose();
        y.drawImage(buffer, 0, 0, this);
    }
    public void update() {
        game.update();
    }
    public void keyPressed(KeyEvent e) {

    }
    public void keyTyped(KeyEvent e) {
        char k = e.getKeyChar();
        if(k == 'w' && game.status() != FroggerGame.DEAD) {
            game.getPlayer().setDirection(Frog.UP);
            if(game.getPlayer().getY() > 50) {
                game.getPlayer().setY(game.getPlayer().getY() - 50.0);
            }
        }
        else if(k == 'a' && game.status() != FroggerGame.DEAD) {
            game.getPlayer().setDirection(Frog.LEFT);
            if(game.getPlayer().getX() > 50) {
                game.getPlayer().setX(game.getPlayer().getX() - 50);
            }
        }
        else if(k == 's' && game.status() != FroggerGame.DEAD) {
            game.getPlayer().setDirection(Frog.DOWN);
            if(game.getPlayer().getY() < 710) {
                game.getPlayer().setY(game.getPlayer().getY() + 50);
            }
        }
        else if(k == 'd' && game.status() != FroggerGame.DEAD) {
            game.getPlayer().setDirection(Frog.RIGHT);
            if(game.getPlayer().getX() < 920) {
                game.getPlayer().setX(game.getPlayer().getX() + 50);
            }
        }
        else if(k == 'n') {
            reset();
        }
    }
    public void keyReleased(KeyEvent e) {

    }
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
    public void run() {
        double nsPerUpdate = 1000000000.0 / updatesPerSecond;
        double delta = 0;
        long lastTime = System.nanoTime();

        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerUpdate;
            lastTime = now;

            while (delta >= 1) {
                update();
                delta--;
                updateCount++;
            }

            repaint();
        }
    }
}