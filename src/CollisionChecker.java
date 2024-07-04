public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        entity.collisionOn = false; // Reset the collision flag

        switch (entity.direction) {
            case "up" -> {
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                for (int col = entityLeftCol; col <= entityRightCol; col++) {
                    int tileNum = gp.tileM.mapTileNum[col][entityTopRow];
                    if (gp.tileM.tile[tileNum].collision) {
                        entity.collisionOn = true;
                        break; // Stop checking if collision is detected
                    }
                }
            }
            case "down" -> {
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                for (int col = entityLeftCol; col <= entityRightCol; col++) {
                    int tileNum = gp.tileM.mapTileNum[col][entityBottomRow];
                    if (gp.tileM.tile[tileNum].collision) {
                        entity.collisionOn = true;
                        break; // Stop checking if collision is detected
                    }
                }
            }
            case "left" -> {
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                for (int row = entityTopRow; row <= entityBottomRow; row++) {
                    int tileNum = gp.tileM.mapTileNum[entityLeftCol][row];
                    if (gp.tileM.tile[tileNum].collision) {
                        entity.collisionOn = true;
                        break; // Stop checking if collision is detected
                    }
                }
            }
            case "right" -> {
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                for (int row = entityTopRow; row <= entityBottomRow; row++) {
                    int tileNum = gp.tileM.mapTileNum[entityRightCol][row];
                    if (gp.tileM.tile[tileNum].collision) {
                        entity.collisionOn = true;
                        break; // Stop checking if collision is detected
                    }
                }
            }
        }
    }
}
