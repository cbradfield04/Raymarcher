import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class March implements Drawable {
    private Point2D start;
    private Point2D end;
    private double angle;

    public March(double x1, double y1, double x2, double y2) {
        this.start = new Point2D.Double(x1, y1);
        this.end = new Point2D.Double(x2, y2);
    }

    public Point2D getStart() {
        return start;
    }

    public Point2D getEnd() {
        return end;
    }

    public void setStart(Point2D start) {
        this.start = start;
    }

    public void setEnd(Point2D end) {
        this.end = end;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    @Override
    public void draw(Graphics2D g2d) {
        angle = Math.toDegrees(Math.atan2(end.getY() - start.getY(), end.getX() - start.getX()));
        double distance = start.distance(end);

        Point2D.Double endPoint = new Point2D.Double(
                start.getX() + distance * Math.cos(Math.toRadians(angle)),
                start.getY() + distance * Math.sin(Math.toRadians(angle)));

        int radius = (int) Math.sqrt(
                Math.pow(start.getX() - endPoint.getX(), 2) +
                        Math.pow(start.getY() - endPoint.getY(), 2));

        int x = (int) start.getX() - radius;
        int y = (int) start.getY() - radius;

        g2d.setColor(Color.BLACK);
        g2d.drawLine((int) start.getX(), (int) start.getY(), (int) endPoint.getX(),
                (int) endPoint.getY());
        g2d.drawOval(x, y, radius * 2, radius * 2);
    }
}
