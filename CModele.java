package ForbiddenIsland;

import java.util.*;
import java.util.List;

class CModele extends Observable {

    public static final int PLAYER_COUNT = 4;

    public Island island = new Island();

    private Player[] Players = new Player[PLAYER_COUNT];

    public int currentPlayer = 0;

    Random random = new Random();

    public CModele() {
        List<int[]> Lands = new ArrayList<int[]>();

        for (int i = 1; i < Island.HAUTEUR + 1; i++) {
            for (int j = 1; j < Island.HAUTEUR + 1; j++) {
                int[] coords = { i, j };
                Lands.add(coords);
            }
        }
        Collections.shuffle(Lands);

        for (int i = 0; i < PLAYER_COUNT; i++) {
            Players[i] = new Player(i, island.getZone(Lands.get(i)[0], Lands.get(i)[1]));
            Players[i].z.placePlayer(Players[i]);
        }
    }

    public void flooding() {
        island.flooding();
        notifyObservers();
    }

    public Zone getZone(int x, int y) {
        return island.getZone(x, y);
    }

    public List<Player> getPlayer(int x, int y) {
        return island.getZone(x, y).getPlayer();
    }

    public boolean movePlayer(int id, Direction direction) {
        Zone z = Players[id].z;
        switch (direction) {
            case UP:
                if (z.y - 1 >= 1) {
                    Players[id].movePlayer(getZone(z.x, z.y - 1));
                    return true;
                } break;
            case DOWN:
                if (z.y + 1 <= Island.HAUTEUR) {
                    Players[id].movePlayer(getZone(z.x, z.y + 1));
                    return true;
                } break;
            case RIGHT:
                if (z.x + 1 <= Island.HAUTEUR) {
                    Players[id].movePlayer(getZone(z.x + 1, z.y));
                    return true;
                } break;
            case LEFT:
                if (z.x - 1 >= 1) {
                    Players[id].movePlayer(getZone(z.x - 1, z.y));
                    return true;
                } break;
        }
        return false;
    }
}