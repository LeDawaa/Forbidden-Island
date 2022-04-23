import java.awt.*;

public class Project {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            CModele modele = new CModele();
            CVue vue = new CVue(modele);
        });
    }
}