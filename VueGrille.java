import java.awt.*;
import javax.swing.*;

class VueGrille extends JPanel implements Observer {

    private CModele modele;

    private final static int TAILLE = 40;

    public VueGrille(CModele modele) {
        this.modele = modele;

        modele.addObserver(this);

        Dimension dim = new Dimension(TAILLE * CModele.HAUTEUR, TAILLE * CModele.HAUTEUR);
        this.setPreferredSize(dim);
    }

    public void update() {
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.repaint();

        for (int i = 1; i <= CModele.HAUTEUR; i++) {
            for (int j = 1; j <= CModele.HAUTEUR; j++) {
                paint(g, modele.getZone(i, j), modele.getPlayer(i, j), (i - 1) * TAILLE, (j - 1) * TAILLE);
            }
        }
    }

    private void paint(Graphics g, Zone z, int PlayerId, int x, int y) {

        if (z.isSubmerged())
            g.setColor(Color.CYAN);
        else if (z.isFlooded())
            g.setColor(Color.BLUE);
        else
            g.setColor(Color.WHITE);

        g.fillRect(x, y, TAILLE, TAILLE);

        switch (PlayerId) {
            case 0:
                g.setColor(Color.YELLOW);

        }
        g.fillOval(x, y, TAILLE, TAILLE);
    }
}