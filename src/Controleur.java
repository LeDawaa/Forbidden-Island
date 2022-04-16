import java.util.*;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.*;

class Controleur implements ActionListener {

    CModele modele;
    List<JLabel> labels = new ArrayList<JLabel>();
    private int actions = 3;
    JPanel dryButtons;

    public Controleur(CModele modele, List<JLabel> labels, JPanel dryButtons) {
        this.modele = modele;
        this.labels = labels;
        this.dryButtons = dryButtons;
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case " ":
                break;
            case "DRY UP":
                if (actions >= 1 && modele.dryZone(Direction.UP)) {
                    actions--;
                    dryButtons.setVisible(false);
                }
                break;
            case "DRY DOWN":
                if (actions >= 1 && modele.dryZone(Direction.DOWN)) {
                    actions--;
                    dryButtons.setVisible(false);
                }
                break;
            case "DRY LEFT":
                if (actions >= 1 && modele.dryZone(Direction.LEFT)) {
                    actions--;
                    dryButtons.setVisible(false);
                }
                break;
            case "DRY RIGHT":
                if (actions >= 1 && modele.dryZone(Direction.RIGHT)) {
                    actions--;
                    dryButtons.setVisible(false);
                }
                break;
            case "DRY HERE":
                if (actions >= 1 && modele.dryZone(Direction.STAY)) {
                    actions--;
                    dryButtons.setVisible(false);
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

                labels.get(modele.currentPlayer).setText("Player : " + (modele.currentPlayer + 1));
                modele.currentPlayer = (modele.currentPlayer + 1) % CModele.PLAYER_COUNT;
                labels.get(modele.currentPlayer).setText("Player : " + (modele.currentPlayer + 1) + " \u2705");

                actions = 3;
                dryButtons.setVisible(true);

                break;
            default:
                System.err.println("Unknown Action " + e.getActionCommand());
        }
        labels.get(labels.size() - 1).setText("Actions left : " + actions);
    }
}