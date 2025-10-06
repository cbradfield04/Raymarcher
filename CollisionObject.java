import java.awt.Graphics2D;

public abstract class CollisionObject implements Drawable {
    protected double x;
    protected double y;

    public CollisionObject(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public abstract boolean collidesWith(CollisionObject other);

    public abstract void draw(Graphics2D g2d);

    public abstract double ComputeDistance(double CameraX, double CameraY);
}