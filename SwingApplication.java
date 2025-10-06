import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public abstract class SwingApplication {

    private static final int SECONDS_TO_MS = 1000;

    private final JFrame FRAME;

    private Timer timer;

    private int fps;

    private int ms;

    private boolean isRunning = false;

    public SwingApplication(int width, int height, int fps, String title) {
        System.setProperty("sun.java2d.opengl", "true");

        this.FRAME = new JFrame(title);
        this.FRAME.setSize(width, height);
        this.FRAME.setResizable(false);
        this.FRAME.setLocationRelativeTo(null);
        this.FRAME.setVisible(true);
        this.FRAME.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.FRAME.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                stop();
            }
        });

        this.fps = fps;
        this.ms = SECONDS_TO_MS / fps;

        SwingUtilities.invokeLater(() -> {
            this.start();
        });
    }

    public void setFrameLayout(LayoutManager layout) {
        this.FRAME.setLayout(layout);
    }

    public void addComponent(Component component) {
        this.FRAME.getContentPane().add(component);
    }

    public void addComponent(Component component, int index) {
        this.FRAME.getContentPane().add(component, index);
    }

    public void addComponent(Component component, Object constraints) {
        this.FRAME.getContentPane().add(component, constraints);
    }

    public void addComponent(Component component, Object constraints,
                             int index) {
        this.FRAME.getContentPane().add(component, constraints, index);
    }

    public void packComponents() {
        this.FRAME.pack();
        this.FRAME.setLocationRelativeTo(null);
    }

    public abstract void run();

    private synchronized void start() {
        if (this.isRunning) {
            return;
        }

        this.isRunning = true;
        this.update();
    }

    private synchronized void stop() {
        if (!this.isRunning) {
            return;
        }

        this.isRunning = false;
        this.timer.stop();
        this.FRAME.dispose();
        System.exit(0);
    }

    private void update() {
        this.setupAppTimer();
        this.timer.start();
    }

    private void setupAppTimer() {
        this.timer = new Timer(this.ms, timerListener -> {
            this.run();
            this.FRAME.repaint();
        });
    }

    public void setFPS(int fps) {
        this.fps = fps;
        this.ms = SECONDS_TO_MS / fps;
        this.timer.setDelay(this.ms);
    }

    public int getFPS() {
        return this.fps;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public void setRunning(boolean running) {
        this.isRunning = running;
    }

    public void isVisible() {
        this.FRAME.isVisible();
    }

    public void setVisible(boolean visible) {
        this.FRAME.setVisible(true);
    }

    public JFrame getFrame() {
        return this.FRAME;
    }
}