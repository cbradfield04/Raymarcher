import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

public class RaymarcherPanel extends JPanel {

    private final RaymarcherRunner raymarcherRunner;

    private List<CollisionObject> objects;
    Camera camera = new Camera(100, 100);

    public RaymarcherPanel(RaymarcherRunner raymarcherRunner) {
        this.raymarcherRunner = raymarcherRunner;
        this.setPreferredSize(new Dimension(this.raymarcherRunner.getFrame().getWidth(),
                this.raymarcherRunner.getFrame().getHeight()));

        objects = new ArrayList<CollisionObject>();
        Random rand = new Random();
        for (int i = 0; i < 7; i++) {
            int x = rand.nextInt(this.getPreferredSize().width);
            int y = rand.nextInt(this.getPreferredSize().height);
            int width = rand.nextInt(100);
            int height = rand.nextInt(100);
            Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            objects.add(new RectangleObject(x, y, width, height, color));
        }
        for (int i = 0; i < 7; i++) {
            int x = rand.nextInt(this.getPreferredSize().width);
            int y = rand.nextInt(this.getPreferredSize().height);
            int radius = rand.nextInt(100);
            Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            objects.add(new CircleObject(x, y, radius, color));
        }
        addMouseMotionListener(camera);
        addMouseListener(camera);
    }

    public ArrayList<March> marchs() {
        double x = camera.getX();
        double y = camera.getY();
        double angle = camera.getAngle();
        ArrayList<March> marches = new ArrayList<March>();
        while (x >= 0 && x <= this.getPreferredSize().width && y >= 0 && y <= this.getPreferredSize().height) {
            double closeDist = Double.MAX_VALUE;
            for (CollisionObject obj : objects) {
                double dist = obj.ComputeDistance(x, y);
                if (dist < closeDist) {
                    closeDist = dist;
                }
            }

            if (closeDist < 0.1) {
                break;
            }

            double newX = x + closeDist * Math.cos(angle);
            double newY = y + closeDist * Math.sin(angle);
            March march = new March(x, y, newX, newY);
            marches.add(march);
            x = newX;
            y = newY;
        }

        return marches;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        double minDist = Double.MAX_VALUE;
        for (CollisionObject obj : objects) {
            double dist = obj.ComputeDistance(camera.getX(), camera.getY());
            if (dist < minDist) {
                minDist = dist;
            }
        }

        ArrayList<March> marches = marchs();
        for (March march : marches) {
            march.draw(g2d);

        }

        for (CollisionObject obj : objects) {
            obj.draw(g2d);
        }

        camera.draw(g2d);
    }
}