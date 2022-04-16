import java.awt.*;
import javax.swing.*;

class CVue {

    private JFrame frame;

    private VueGrille grille;
    private VueCommandes commandes;

    public CVue(CModele modele) {

        frame = new JFrame();
        frame.setTitle("Forbidden Island");

        frame.setLayout(new FlowLayout());

        grille = new VueGrille(modele);
        frame.add(grille);
        commandes = new VueCommandes(modele);
        frame.add(commandes);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}