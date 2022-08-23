package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

// This stores variables that will be used in player, monsters, etc.
public class Entity {

    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    // it describes an image with an accessible buffer of image data
    // (store image files)
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 2;
    public Rectangle solidArea;
    public boolean collisionOn = false;
}
