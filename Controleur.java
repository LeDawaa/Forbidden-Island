package ForbiddenIsland;

import java.util.*;
import java.util.List;
import javax.swing.JLabel;

import java.awt.event.*;

class Controleur implements ActionListener {

    CModele modele;
    List<JLabel> labels = new ArrayList<JLabel>();
    private int actions = 3;

    public Controleur(CModele modele, List<JLabel> labels) {
        this.modele = modele;
        this.labels = labels;
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case " ":
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
                modele.currentPlayer = (modele.currentPlayer + 1) % 4;
                labels.get(modele.currentPlayer).setText("Player : " + (modele.currentPlayer + 1) + " \u2705");

                actions = 3;

                break;
            default:
                System.err.println("Unknown Action " + e.getActionCommand());
        }
        labels.get(labels.size() - 1).setText("Actions left : " + actions);
    }
}