import java.util.*;
import java.util.List;
import javax.swing.*;
import java.awt.*;

class Player {
    int id;
    Zone z;
    Pawns pawn;
    HashMap<Keys, Integer> keys = new HashMap<Keys, Integer>() {
        {
            for (Keys key : Keys.values())
                put(key, 0);
        }
    };
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
        if (this.z.artifact == null)
            return false;
        Keys key = this.z.artifact.associatedKey();
        if (keys.get(key) > 0) {
            artifacts.add(this.z.artifact);
            System.out.println(this.z.artifact);

            switch (this.z.artifact) {
                case EarthArtifact -> VueCommandes.EarthWilder.setIcon(new ImageIcon(this.pawn.texture.getScaledInstance(50, 73, Image.SCALE_DEFAULT)));
                case FireArtifact -> VueCommandes.FireWilder.setIcon(new ImageIcon(this.pawn.texture.getScaledInstance(50, 73, Image.SCALE_DEFAULT)));
                case WindArtifact -> VueCommandes.WindWilder.setIcon(new ImageIcon(this.pawn.texture.getScaledInstance(50, 73, Image.SCALE_DEFAULT)));
                case WaterArtifact -> VueCommandes.WaterWilder.setIcon(new ImageIcon(this.pawn.texture.getScaledInstance(50, 73, Image.SCALE_DEFAULT)));
            }




            keys.put(key, keys.get(key) - 1);
            this.z.artifact = null;

            
            return true;
        }
        System.out.println("You don't have the " + key.toString());
        return false;
    }

    public String keyList() {
        String text = "";
        for (Keys key : keys.keySet()) {
            text += keys.get(key) + " x " + key.toString() + "  ";
        } return text;
    }
}