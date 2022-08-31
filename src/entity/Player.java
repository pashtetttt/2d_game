package entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final  int screenY;
    public Player(GamePanel gp, KeyHandler keyH){

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
        setDefaultValues();
        getPlayerImage();
        int hasKey = 0;
    }
    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update(){

        if (keyH.upPressed){
            direction = "up";

        }
        else if (keyH.downPressed){
            direction = "down";

        }
        else if (keyH.leftPressed){
            direction = "left";

        }
        else if (keyH.rightPressed){
            direction = "right";

        }
        // checking tile's collision
        collisionOn = false;
        gp.checker.checkTile(this);

        // check object collision
        int objIndex = gp.checker.checkObject(this, true);
        pickUpObject(objIndex);
        // if collision is false, player can move
        if (!collisionOn){
            switch (direction) {
                case "up" -> {
                    if (keyH.upPressed) {
                        worldY -= speed; // the up left corner is 0:0
                        switchSprites();
                    }
                }
                case "down" -> {
                    if (keyH.downPressed) {
                        worldY += speed;
                        switchSprites();
                    }
                }
                case "left" -> {
                    if (keyH.leftPressed) {
                        worldX -= speed;
                        switchSprites();
                    }
                }
                case "right" -> {
                    if (keyH.rightPressed) {
                        worldX += speed;
                        switchSprites();
                    }
                }
            }
        }

    }

    private void switchSprites() {
        spriteCounter++;

        if (spriteCounter > 10){
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void pickUpObject(int i){
        if(i != 999){
            String objectName = gp.obj[i].name;

            switch (objectName){
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    break;
                case "Door":
                    if (hasKey > 0){
                        gp.obj[i] = null;
                        hasKey--;
                    }
                    break;
            }
        }
    }
    public void draw(Graphics2D g2){
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
