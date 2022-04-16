import java.util.*;
import java.util.List;

class Zone {

    protected WaterStage etat = WaterStage.DRY;
    List<Player> players = new ArrayList<Player>();
    protected int x, y;

    public Zone (int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected void flood() {
        switch (etat) {
            case DRY:
                etat = WaterStage.SUBMERGED;
                break;
            case SUBMERGED:
                etat = WaterStage.FLOODED;
                break;
            case FLOODED:
                break;
        }
    }

    public boolean isFlooded() {
        return etat.equals(WaterStage.FLOODED);
    }

    public boolean isSubmerged() {
        return etat.equals(WaterStage.SUBMERGED);
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
}