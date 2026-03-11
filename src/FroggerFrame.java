import javax.swing.*;
import java.awt.*;
public class FroggerFrame extends JFrame {
    public FroggerFrame(String frameName, int panelWidth, int panelHeight) {
        super(frameName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        FroggerPanel p = new FroggerPanel(panelWidth, panelHeight);
        add(p);
        pack();
        Insets frameInsets = getInsets();
        int frameWidth = panelWidth + (frameInsets.left + frameInsets.right);
        int frameHeight = panelHeight + (frameInsets.top + frameInsets.bottom);
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        pack();
        setLayout(null);
        setSize(new Dimension(frameWidth, frameHeight));
        setVisible(true);
    }
}