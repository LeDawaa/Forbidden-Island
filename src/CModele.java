import java.util.*;
import java.util.List;

class CModele extends Observable {

    public static final int PLAYER_COUNT = 4;

    public static final int HAUTEUR = 6;

    protected Player[] Players = new Player[PLAYER_COUNT];

    protected Zone[][] Zones = new Zone[HAUTEUR + 2][HAUTEUR + 2];;

    public int currentPlayer = 0;

    public CModele() {

        List<Zone> freeLands = new ArrayList<Zone>();
        List<Case> freeCases = new ArrayList<Case>(Arrays.asList(Case.values()));
        freeCases.remove(Case.TileMoat);

        for (int i = 0; i < HAUTEUR + 2; i++) {
            for (int j = 0; j < HAUTEUR + 2; j++) {
                if (i == 0 || j == 0 || i == HAUTEUR + 1 || j == HAUTEUR + 1 ||
                        i == 1 && (i + j == 2 || i + j == 3 || i + j == 6 || i + j == 7)
                        || i == 2 && (i + j == 3 || i + j == 8)
                        || i == 5 && (i + j == 6 || i + j == 11)
                        || i == 6 && (i + j == 7 || i + j == 8 || i + j == 11 || i + j == 12)) {
                    Zones[i][j] = new Zone(i, j);
                } else {
                    Zones[i][j] = new Zone(i, j, freeCases.get(new Random().nextInt(freeCases.size())));
                    freeCases.remove(Zones[i][j].c);
                    if (!(Zones[i][j].c.equals(Case.FoolsLanding)))
                        freeLands.add(Zones[i][j]);
                }
            }
        }

        Collections.shuffle(freeLands);

        List<Pawns> freePawns = new ArrayList<Pawns>(Arrays.asList(Pawns.values()));
        for (int i = 0; i < PLAYER_COUNT; i++) {
            Players[i] = new Player(i, freeLands.get(i), freePawns.get(new Random().nextInt(freePawns.size())));
            Players[i].z.placePlayer(Players[i]);
            freeLands.remove(freeLands.get(i));
            freePawns.remove(Players[i].pawn);
        }

        List<Artifacts> freeArtifacts = new ArrayList<Artifacts>(Arrays.asList(Artifacts.values()));
        for (int i = 0; i < Artifacts.values().length; i++) {
            freeLands.get(i).placeArtifact(freeArtifacts.get(new Random().nextInt(freeArtifacts.size())));
            freeArtifacts.remove(freeLands.get(i).artifact);
            freeLands.remove(freeLands.get(i));
        }
    }

    public void flooding() {
        List<int[]> dryLands = new ArrayList<int[]>();
        List<int[]> submergedLands = new ArrayList<int[]>();

        for (int i = 1; i < HAUTEUR + 1; i++) {
            for (int j = 1; j < HAUTEUR + 1; j++) {
                int[] coords = { i, j };
                if (Zones[i][j].isDry())
                    dryLands.add(coords);
                else if (Zones[i][j].isFlooded())
                    submergedLands.add(coords);
            }
        }

        Collections.shuffle(dryLands);
        Collections.shuffle(submergedLands);
        dryLands.addAll(submergedLands);

        if (dryLands.size() > 0) {
            for (int i = 0; i < Math.min(dryLands.size(), 3); i++)
                Zones[dryLands.get(i)[0]][dryLands.get(i)[1]].flood();
        }

        for (Player player : Players) {
            Zone z = player.z;
            if (!Zones[z.x][z.y - 1].isAccessible() &&
                    !Zones[z.x][z.y + 1].isAccessible() &&
                    !Zones[z.x - 1][z.y].isAccessible() &&
                    !Zones[z.x + 1][z.y].isAccessible())
                CVue.gameOver();
        }
    }

    public boolean movePlayer(int id, Direction direction) {
        boolean changed = false;
        switch (direction) {
            case UP ->
                changed = ((Players[id].z.y - 1 >= 1 && Zones[Players[id].z.x][Players[id].z.y - 1].isAccessible())
                        ? Players[id].movePlayer(getZone(Players[id].z.x, Players[id].z.y - 1))
                        : false);
            case DOWN -> changed = ((Players[id].z.y + 1 <= HAUTEUR
                    && Zones[Players[id].z.x][Players[id].z.y + 1].isAccessible())
                            ? Players[id].movePlayer(getZone(Players[id].z.x, Players[id].z.y + 1))
                            : false);
            case RIGHT -> changed = ((Players[id].z.x + 1 <= HAUTEUR
                    && Zones[Players[id].z.x + 1][Players[id].z.y].isAccessible())
                            ? Players[id].movePlayer(getZone(Players[id].z.x + 1, Players[id].z.y))
                            : false);
            case LEFT ->
                changed = ((Players[id].z.x - 1 >= 1 && Zones[Players[id].z.x - 1][Players[id].z.y].isAccessible())
                        ? Players[id].movePlayer(getZone(Players[id].z.x - 1, Players[id].z.y))
                        : false);
            case STAY -> changed = false;
        }
        return changed;
    }

    public boolean dryZone(Direction direction) {
        boolean changed = false;
        Zone z = Players[currentPlayer].z;
        switch (direction) {
            case UP -> changed = ((z.y - 1 >= 1) ? getZone(z.x, z.y - 1).dryZone() : false);
            case DOWN -> changed = ((z.y + 1 <= HAUTEUR) ? getZone(z.x, z.y + 1).dryZone() : false);
            case RIGHT -> changed = ((z.x + 1 <= HAUTEUR) ? getZone(z.x + 1, z.y).dryZone() : false);
            case LEFT -> changed = ((z.x - 1 >= 1) ? getZone(z.x - 1, z.y).dryZone() : false);
            case STAY -> changed = z.dryZone();
        }
        return changed;
    }

    public void placePlayer(int x, int y, Player player) {
        Zones[x][y].placePlayer(player);
    }

    public Zone getZone(int x, int y) {
        return Zones[x][y];
    }

    public List<Player> getPlayer(int x, int y) {
        return Zones[x][y].getPlayer();
    }

    public Player getCurrentPlayer() {
        return Players[currentPlayer];
    }
}