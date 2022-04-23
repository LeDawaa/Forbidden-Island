import java.util.*;
import java.awt.event.*;

class Controleur implements ActionListener {

    CModele modele;
    private int actions = 3;

    public Controleur(CModele modele) {
        this.modele = modele;
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case " ":
                break;
            case "T A":
                if (actions >= 1 && modele.getCurrentPlayer().takeArtifact()) {
                    actions--;
                    VueCommandes.labels.get(modele.currentPlayer).setText("Player : " + (modele.currentPlayer + 1) + Arrays.toString(modele.getCurrentPlayer().artifacts.toArray()) + " \u2705");
                }
                break;
            case "DRY UP":
                if (actions >= 1 && modele.dryZone(Direction.UP)) {
                    actions--;
                    VueCommandes.dryButtons.setVisible(false);
                }
                break;
            case "DRY DOWN":
                if (actions >= 1 && modele.dryZone(Direction.DOWN)) {
                    actions--;
                    VueCommandes.dryButtons.setVisible(false);
                }
                break;
            case "DRY LEFT":
                if (actions >= 1 && modele.dryZone(Direction.LEFT)) {
                    actions--;
                    VueCommandes.dryButtons.setVisible(false);
                }
                break;
            case "DRY RIGHT":
                if (actions >= 1 && modele.dryZone(Direction.RIGHT)) {
                    actions--;
                    VueCommandes.dryButtons.setVisible(false);
                }
                break;
            case "DRY HERE":
                if (actions >= 1 && modele.dryZone(Direction.STAY)) {
                    actions--;
                    VueCommandes.dryButtons.setVisible(false);
                }
                break;
            case "↑":
                if (actions >= 1 && modele.movePlayer(modele.currentPlayer, Direction.UP))
                    actions--;
                break;
            case "←":
                if (actions >= 1 && modele.movePlayer(modele.currentPlayer, Direction.LEFT))
                    actions--;
                break;
            case "→":
                if (actions >= 1 && modele.movePlayer(modele.currentPlayer, Direction.RIGHT))
                    actions--;
                break;
            case "↓":
                if (actions >= 1 && modele.movePlayer(modele.currentPlayer, Direction.DOWN))
                    actions--;
                break;
            case "EOT":
                modele.flooding();

                if (Math.random() < 0.6)
                    modele.getCurrentPlayer().keys.add(Keys.values()[new Random().nextInt(Keys.values().length)]);

                VueCommandes.labels.get(modele.currentPlayer).setText("Player : " + (modele.currentPlayer + 1) + Arrays.toString(modele.getCurrentPlayer().keys.toArray()) + Arrays.toString(modele.getCurrentPlayer().artifacts.toArray()));
                modele.currentPlayer = (modele.currentPlayer + 1) % CModele.PLAYER_COUNT;
                VueCommandes.labels.get(modele.currentPlayer).setText("Player : " + (modele.currentPlayer + 1) + Arrays.toString(modele.getCurrentPlayer().keys.toArray()) + Arrays.toString(modele.getCurrentPlayer().artifacts.toArray()) + " \u2705");

                actions = 3;
                VueCommandes.dryButtons.setVisible(true);

                break;
            default:
                System.err.println("Unknown Action " + e.getActionCommand());
        }
        VueCommandes.labels.get(VueCommandes.labels.size() - 1).setText("Actions left : " + actions);
    }
}