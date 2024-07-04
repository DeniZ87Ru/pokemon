import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NPC {
    private int npcX;
    private int npcY;
    private NPC npc;

    private BufferedImage image; // NPC-Bild

    public NPC(BufferedImage image, int npcX, int npcY, NPC npc) {
        this.image = image;
        this.npcX = npcX;
        this.npcY = npcY;
        this.npc = npc;
    }

    public void update() {
        // Hier wird die KI-Logik des NPCs implementiert
        // Der NPC kann seine Position aktualisieren, Entscheidungen treffen, usw.
    }

    public void draw(Graphics2D g2) {
        // Hier wird der NPC auf dem Bildschirm gezeichnet
        // Verwende die aktuellen Koordinaten x und y
        g2.drawImage(image, npcX, npcY, null);
        NPC npc = new NPC(image, 200, 200, null);
    }
}
