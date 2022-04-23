import java.util.*;
import java.util.List;

class Player {
    int id;
    Zone z;
    Pawns pawn;
    List<Keys> keys = new ArrayList<Keys>();
    List<Artifacts> artifacts = new ArrayList<Artifacts>();

    protected Player(int id, Zone z, Pawns pawn) {
        this.id = id;
        this.z = z;
        this.pawn = pawn;
    }

    public boolean movePlayer(Zone z) {
        this.z.removePlayer(this);
        this.z = z;
        this.z.placePlayer(this);
        return true;
    }

    public boolean takeArtifact() {
        if (this.z.artifact == null) return false;
        Keys key = this.z.artifact.associatedKey();
        if (keys.contains(key)) {
            artifacts.add(this.z.artifact);
            System.out.println(this.z.artifact);
            keys.remove(key);
            this.z.artifact = null;
            return true;
        }
        System.out.println("You don't have the " + key.toString());
        return false;
    }
}