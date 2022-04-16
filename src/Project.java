import java.awt.*;

enum WaterStage {
    DRY, SUBMERGED, FLOODED
};

enum Direction {
    UP, DOWN, STAY, LEFT, RIGHT
};

public class Project {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            CModele modele = new CModele();
            CVue vue = new CVue(modele);
        });
    }
}