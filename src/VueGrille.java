import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

class VueGrille extends JPanel implements Observer {

    private CModele modele;

    private final static int TAILLE = 100;

    public VueGrille(CModele modele) {
        this.modele = modele;

        modele.addObserver(this);

        Dimension dim = new Dimension(TAILLE * CModele.HAUTEUR, TAILLE * CModele.HAUTEUR);
        this.setPreferredSize(dim);

        setOpaque(false);
    }

    public void update() {
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.repaint();

        for (int i = 1; i <= CModele.HAUTEUR; i++) {
            for (int j = 1; j <= CModele.HAUTEUR; j++) {
                if (!modele.getZone(i, j).c.equals(Case.TileMoat))
                    paint(g, modele.getZone(i, j), (i - 1) * TAILLE, (j - 1) * TAILLE);
            }
        }
    }

    private void paint(Graphics g, Zone z, int x, int y) {

        g.drawImage(z.texture.getScaledInstance(TAILLE, TAILLE, Image.SCALE_DEFAULT), x, y, null);

        if (z.artifact != null) {
            g.drawImage(z.artifact.texture.getScaledInstance(TAILLE / 2, TAILLE / 2, Image.SCALE_DEFAULT),
                    x + 2 * TAILLE / 5, y + 2 * TAILLE / 5, null);
        }

        if (!z.players.isEmpty()) {
            BufferedImage texture = z.players.get(0).id == modele.currentPlayer ? z.players.get(0).pawn.selected_texture
                    : z.players.get(0).pawn.texture;
            g.drawImage(texture.getScaledInstance(2 * TAILLE / 3, 2 * TAILLE / 3, Image.SCALE_DEFAULT), x, y, null);
        }
    }
}