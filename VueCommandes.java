import javax.swing.*;

class VueCommandes extends JPanel {

    private CModele modele;

    public VueCommandes(CModele modele) {
        this.modele = modele;

        JButton boutonEndOfTurn = new JButton("EOT");
        this.add(boutonEndOfTurn);

        Controleur ctrl = new Controleur(modele);

        boutonEndOfTurn.addActionListener(ctrl);

    }
}