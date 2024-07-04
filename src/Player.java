
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    Image image;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH,Image image) {
        this.gp = gp;
        this.keyH = keyH;
        this.image = image;
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(8,16, 32, 32);

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){

        worldX = gp.tileSize + 1060;
        worldY = gp.tileSize + 950;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){
        try{

            up1 = ImageIO.read(new File("res/player/boyup1.png"));
            up2 = ImageIO.read(new File("res/player/boyup2.png"));
            down1 = ImageIO.read(new File("res/player/boydown1.png"));
            down2 = ImageIO.read(new File("res/player/boydown2.png"));
            left1 = ImageIO.read(new File("res/player/boyleft1.png"));
            left2 = ImageIO.read(new File("res/player/boyleft2.png"));
            right1 = ImageIO.read(new File("res/player/boyright1.png"));
            right2 = ImageIO.read(new File("res/player/boyright2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update(){

        if(keyH.upPressed || keyH.downPressed ||
                keyH.leftPressed || keyH.rightPressed){
            if(keyH.upPressed){
                direction = "up";
            }
            else if(keyH.downPressed){
                direction = "down";
            }
            else if(keyH.leftPressed){
                direction = "left";
            }
            else {
                direction = "right";
            }

            collisionOn = false;                                           //Wände Check
            gp.cChecker.checkTile(this);

            if(!collisionOn) {

                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum ==2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }
    public void draw(Graphics g2){

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
