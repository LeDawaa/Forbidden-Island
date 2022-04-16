package ForbiddenIsland;
class Player {
    int id;
    Zone z;

    protected Player(int id, Zone z) {
        this.id = id;
        this.z = z;
    }

    public void movePlayer(Zone z) {
        this.z.removePlayer(this);
        this.z = z;
        this.z.placePlayer(this);
    }
}