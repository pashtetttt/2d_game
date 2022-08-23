package Main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel  extends JPanel implements Runnable{

    // Screen settings
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 760
    public final int screenHeight = tileSize * maxScreenRow; // 576

    // World settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    // FPS
    int fps = 60;

    TileManager tileManager = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker checker = new CollisionChecker(this);
    public Player player = new Player(this, keyH);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // setting the size
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // rendering
        this.addKeyListener(keyH);
        this.setFocusable(true); // GamePanel can be focused to receive input
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() { // the method of Thread - it runs automatically

        double drawInterval = 1000000000/fps; // 0.016 seconds or 60 times per sec
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null){

            // here are the updates like positions
            update();
            // and it draws
            repaint();



            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000; // sleep method needs milliseconds

                if (remainingTime < 0) remainingTime = 0;

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        player.draw(g2);

        g2.dispose();
    }
}
