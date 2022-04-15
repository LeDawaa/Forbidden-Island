import java.awt.event.*;

class Controleur implements ActionListener {

    CModele modele;

    public Controleur(CModele modele) {
        this.modele = modele;
    }

    public void actionPerformed(ActionEvent e) {
        modele.flooding();
    }
}