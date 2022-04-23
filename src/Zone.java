import java.util.*;
import java.util.List;
import java.awt.image.BufferedImage;

class Zone {

    protected WaterStage etat;
    protected Artifacts artifact = null;
    protected List<Player> players = new ArrayList<Player>();
    protected int x, y;
    protected Case c;
    protected BufferedImage texture;

    public Zone(int x, int y) {
        this.x = x;
        this.y = y;
        this.c = Case.TileMoat;
        etat = WaterStage.SUBMERGED;
        this.texture = etat.texture;
    }

    public Zone(int x, int y, Case c) {
        this(x, y);
        this.c = c;
        etat = WaterStage.DRY;
        this.texture = c.texture;
    }

    protected void flood() {
        switch (etat) {
            case DRY:
                etat = WaterStage.FLOODED;
                texture = c.flooded_texture;
                break;
            case FLOODED:
                etat = WaterStage.SUBMERGED;
                texture = WaterStage.SUBMERGED.texture;
                break;
            case SUBMERGED:
                break;
        }
    }

    public boolean dryZone() {
        switch (etat) {
            case FLOODED:
                etat = WaterStage.DRY;
                texture = c.texture;
                return true;
            case SUBMERGED:
            case DRY:
        }
        return false;
    }

    public boolean isSubmerged() {
        return etat.equals(WaterStage.SUBMERGED);
    }

    public boolean isFlooded() {
        return etat.equals(WaterStage.FLOODED);
    }

    public boolean isDry() {
        return etat.equals(WaterStage.DRY);
    }

    public List<Player> getPlayer() {
        return this.players;
    }

    public void placePlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
    }

    public void placeArtifact(Artifacts artifact) {
        this.artifact = artifact;
    }

    public boolean isAccessible() {
        return !(isSubmerged() || c.equals(Case.TileMoat));
    }
}