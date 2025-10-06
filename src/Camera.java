import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class Camera implements Drawable, MouseMotionListener, MouseListener {
    private int x;
    private int y;
    private int r = 10;
    private double angle;

    public Camera(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getRadius() {
        return r;
    }

    public void setRadius(double distance) {
        this.r = (int) distance;
    }

    public double getDiameter() {
        return 2 * r;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void rotate(int deltaAngle) {
        this.angle += deltaAngle;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.black);
        g2d.fillOval(x - r, y - r, 2 * r, 2 * r);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point p = e.getPoint();
        p.translate(-Panel.WIDTH / 2, -Panel.HEIGHT / 2);
        x = p.x;
        y = p.y;

    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent event) {
        int button = event.getButton();
        if (button == MouseEvent.BUTTON1) {
            angle += 1;
        } else if (button == MouseEvent.BUTTON3) {
            angle -= 1;
        }
    }
}
