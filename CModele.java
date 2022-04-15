import java.util.*;
import java.util.List;

class CModele extends Observable {

    public static final int HAUTEUR = 9;

    public static final int PLAYER_COUNT = 1;

    private Zone[][] Zones = new Zone[HAUTEUR + 2][HAUTEUR + 2];;

    private Player[] Players = new Player[PLAYER_COUNT];

    Random random = new Random();

    public CModele() {
        for (int i = 0; i < HAUTEUR + 2; i++) {
            for (int j = 0; j < HAUTEUR + 2; j++) {
                Zones[i][j] = new Zone();
            }
        }
        for (int i = 0; i < PLAYER_COUNT; i++) {
            Players[i] = new Player(random.nextInt(HAUTEUR) + 1, random.nextInt(HAUTEUR) + 1, i);
        }
    }

    public void flooding() {

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
            for (int i = 0; i < Math.min(3, dryLands.size()); i++)
                Zones[dryLands.get(i)[0]][dryLands.get(i)[1]].flood();
        }
        notifyObservers();
    }

    public Zone getZone(int x, int y) {
        return Zones[x][y];
    }

    public int getPlayer(int x, int y) {
        for (int i = 0; i < Players.length; i++) {
            if (Players[i].x == x && Players[i].y == y)
                return i;
        }
        return -1;
    }
}