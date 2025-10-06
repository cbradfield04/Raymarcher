import java.awt.Color;
import java.awt.Graphics2D;

public class CircleObject extends CollisionObject {
    private int r;
    private Color c;

    public CircleObject(double x, double y, int radius, Color color) {
        super(x, y);
        this.r = radius;
        this.c = color;
    }

    public int getRadius() {
        return r;
    }

    public int getDiameter() {
        return 2 * r;
    }

    @Override
    public boolean collidesWith(CollisionObject other) {
        if (other instanceof CircleObject) {
            CircleObject c = (CircleObject) other;
            return (Math.pow(x - c.x, 2) + Math.pow(y - c.y, 2) < Math.pow(r + c.r, 2));
        } else {
            return false;
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(c);
        g2d.fillOval((int) x - r, (int) y - r, r * 2, r * 2);
    }

    @Override
    public double ComputeDistance(double CameraX, double CameraY) {
        return Math.sqrt(Math.pow(x - CameraX, 2) + Math.pow(y - CameraY, 2)) - r;
    }
}
