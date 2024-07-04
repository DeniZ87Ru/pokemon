
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadMap("res/maps/world01.txt");
        getTileImage();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile(ImageIO.read(new File("res/tiles/grass.png")), false);
            tile[1] = new Tile(ImageIO.read(new File("res/tiles/wall.png")), true);
            tile[2] = new Tile(ImageIO.read(new File("res/tiles/water.png")), true);
            tile[3] = new Tile(ImageIO.read(new File("res/tiles/earth.png")), false);
            tile[4] = new Tile(ImageIO.read(new File("res/tiles/tree.png")), true);
            tile[5] = new Tile(ImageIO.read(new File("res/tiles/sand.png")), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {

            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            int row = 0;
            String line;
            while ((line = reader.readLine()) != null && row < gp.maxWorldRow) {
                String[] numbers = line.split(" ");

                for (int col = 0; col < gp.maxWorldCol && col < numbers.length; col++) {
                    mapTileNum[col][row] = Integer.parseInt(numbers[col]);
                }
                row++;
            }
            reader.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Konnte Karte nicht laden.");
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX - gp.worldWidth / 2 &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX + gp.worldWidth / 2 &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY - gp.worldHeight / 2 &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY + gp.worldHeight / 2) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
