package ForbiddenIsland;
import java.util.*;
import java.util.List;

public class Island {
    public static final int HAUTEUR = 10;

    protected Zone[][] Zones = new Zone[HAUTEUR + 2][HAUTEUR + 2];;

    public Island () {
        for (int i = 0; i < HAUTEUR + 2; i++) {
            for (int j = 0; j < HAUTEUR + 2; j++) {
                Zones[i][j] = new Zone(i, j);
            }
        }
    }

    public void flooding () {
        List<int[]> dryLands = new ArrayList<int[]>();
        List<int[]> submergedLands = new ArrayList<int[]>();

        for (int i = 1; i < HAUTEUR + 1; i++) {
            for (int j = 1; j < HAUTEUR + 1; j++) {
                if (Zones[i][j].isDry()) {
                    int[] coords = { i, j };
                    dryLands.add(coords);
                } else if (Zones[i][j].isSubmerged()) {
                    int[] coords = { i, j };
                    submergedLands.add(coords);
                }
            }
        }

        Collections.shuffle(dryLands);
        Collections.shuffle(submergedLands);
        dryLands.addAll(submergedLands);

        if (dryLands.size() > 0) {
            for (int i = 0; i < Math.min(dryLands.size(), 3); i++)
                Zones[dryLands.get(i)[0]][dryLands.get(i)[1]].flood();
        }
    }

    public Zone getZone(int x, int y) {
        return Zones[x][y];
    }

    public void placePlayer(int x, int y, Player player) {
        Zones[x][y].placePlayer(player);
    }
}