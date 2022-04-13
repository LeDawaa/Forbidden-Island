import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

interface Observer {

    public void update();
}

abstract class Observable {

    private ArrayList<Observer> observers;

    public Observable() {
        this.observers = new ArrayList<Observer>();
    }

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
}

public class Project {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            CModele modele = new CModele();
            CVue vue = new CVue(modele);
        });
    }
}

class CModele extends Observable {

    public static final int HAUTEUR = 40, LARGEUR = 60;

    private Cellule[][] cellules;

    public CModele() {
        cellules = new Cellule[LARGEUR + 2][HAUTEUR + 2];
        for (int i = 0; i < LARGEUR + 2; i++) {
            for (int j = 0; j < HAUTEUR + 2; j++) {
                cellules[i][j] = new Cellule(this, i, j);
            }
        }
        init();
    }

    public void init() {
        for (int i = 1; i <= LARGEUR; i++) {
            for (int j = 1; j <= HAUTEUR; j++) {
                if (Math.random() < .2) {
                    cellules[i][j].etat = true;
                }
            }
        }
    }

    public void avance() {

        for (int i = 1; i < LARGEUR + 1; i++) {
            for (int j = 1; j < HAUTEUR + 1; j++) {
                cellules[i][j].evalue();
            }
        }
        for (int i = 1; i < LARGEUR + 1; i++) {
            for (int j = 1; j < HAUTEUR + 1; j++) {
                cellules[i][j].evolue();
            }
        }
        notifyObservers();
    }

    protected int compteVoisines(int x, int y) {
        int res = 0;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (cellules[i][j].etat) {
                    res++;
                }
            }
        }
        return (res - ((cellules[x][y].etat) ? 1 : 0));
    }

    public Cellule getCellule(int x, int y) {
        return cellules[x][y];
    }

}

class Cellule {

    private CModele modele;

    protected boolean etat;

    private final int x, y;

    public Cellule(CModele modele, int x, int y) {
        this.modele = modele;
        this.etat = false;
        this.x = x;
        this.y = y;
    }

    private boolean prochainEtat;

    protected void evalue() {
        switch (this.modele.compteVoisines(x, y)) {
            case 2:
                prochainEtat = etat;
                break;
            case 3:
                prochainEtat = true;
                break;
            default:
                prochainEtat = false;
        }
    }

    protected void evolue() { etat = prochainEtat; }

    public boolean estVivante() { return etat; }
}

class CVue {

    private JFrame frame;

    private VueGrille grille;
    private VueCommandes commandes;

    public CVue(CModele modele) {

        frame = new JFrame();
        frame.setTitle("Jeu de la vie de Conway");

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

class VueGrille extends JPanel implements Observer {

    private CModele modele;

    private final static int TAILLE = 12;

    public VueGrille(CModele modele) {
        this.modele = modele;

        modele.addObserver(this);

        Dimension dim = new Dimension(TAILLE * CModele.LARGEUR,
                TAILLE * CModele.HAUTEUR);
        this.setPreferredSize(dim);
    }

    public void update() {
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.repaint();

        for (int i = 1; i <= CModele.LARGEUR; i++) {
            for (int j = 1; j <= CModele.HAUTEUR; j++) {

                paint(g, modele.getCellule(i, j), (i - 1) * TAILLE, (j - 1) * TAILLE);
            }
        }
    }

    private void paint(Graphics g, Cellule c, int x, int y) {

        if (c.estVivante()) {
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.WHITE);
        }

        g.fillRect(x, y, TAILLE, TAILLE);
    }
}

class VueCommandes extends JPanel {

    private CModele modele;

    public VueCommandes(CModele modele) {
        this.modele = modele;

        JButton boutonAvance = new JButton(">");
        this.add(boutonAvance);

        Controleur ctrl = new Controleur(modele);

        boutonAvance.addActionListener(ctrl);

    }
}

class Controleur implements ActionListener {

    CModele modele;

    public Controleur(CModele modele) {
        this.modele = modele;
    }

    public void actionPerformed(ActionEvent e) {
        modele.avance();
    }
}