package ForbiddenIsland;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.util.List;

class VueGrille extends JPanel implements Observer {

    private CModele modele;

    private final static int TAILLE = 40;

    public VueGrille(CModele modele) {
        this.modele = modele;

        modele.addObserver(this);

        Dimension dim = new Dimension(TAILLE * Island.HAUTEUR, TAILLE * Island.HAUTEUR);
        this.setPreferredSize(dim);
    }

    public void update() {
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.repaint();

        for (int i = 1; i <= Island.HAUTEUR; i++) {
            for (int j = 1; j <= Island.HAUTEUR; j++) {
                paint(g, modele.getZone(i, j), modele.getPlayer(i, j), (i - 1) * TAILLE, (j - 1) * TAILLE);
            }
        }
    }

    private void paint(Graphics g, Zone z, List<Player> players, int x, int y) {

        if (z.isSubmerged())
            g.setColor(Color.CYAN);
        else if (z.isFlooded())
            g.setColor(Color.BLUE);
        else
            g.setColor(Color.WHITE);

        g.fillRect(x, y, TAILLE, TAILLE);

        if (!players.isEmpty()) {
            switch (players.get(0).id) {
                case 0:
                    g.setColor(Color.YELLOW);
                    g.fillOval(x, y, TAILLE, TAILLE);
                    break;
                case 1:
                    g.setColor(Color.RED);
                    g.fillOval(x, y, TAILLE, TAILLE);
                    break;
                case 2:
                    g.setColor(Color.ORANGE);
                    g.fillOval(x, y, TAILLE, TAILLE);
                    break;
                case 3:
                    g.setColor(Color.GREEN);
                    g.fillOval(x, y, TAILLE, TAILLE);
                    break;
            }
        }
    }
}