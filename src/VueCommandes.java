import java.util.*;
import java.util.List;

import javax.swing.*;

import java.awt.GridLayout;
import javax.swing.JPanel;

class VueCommandes extends JPanel {

    private CModele modele;

    List<JLabel> labels = new ArrayList<JLabel>();

    public VueCommandes(CModele modele) {
        this.modele = modele;

        JPanel pannel = new JPanel();
        pannel.setLayout(new GridLayout(2, 1));

        JPanel top = new JPanel();
        top.setLayout(new GridLayout(CModele.PLAYER_COUNT + 4, 1));

        for (int i = 0; i < CModele.PLAYER_COUNT; i++) {
            JLabel label = new JLabel("Player : " + (i + 1) + ((i == modele.currentPlayer) ? " \u2705" : ""));
            labels.add(label);
            top.add(label);
        }

        JLabel labelEmpty = new JLabel("\n");
        labels.add(labelEmpty);
        top.add(labelEmpty);

        JLabel labelActionsLeft = new JLabel("Actions left : " + 3);
        labels.add(labelActionsLeft);
        top.add(labelActionsLeft);

        Controleur ctrl = new Controleur(modele, labels);

        JPanel middle = new JPanel();
        middle.setLayout(new GridLayout(3, 3));

        String[] key = { " ", "↑", " ", "←", "EOT", "→", " ", "↓", " " };
        for (int i = 0; i < key.length; i++) {
            JButton button = new JButton(key[i]);
            button.addActionListener(ctrl);
            middle.add(button);
        }

        pannel.add(top);
        pannel.add(middle);

        add(pannel);
    }
}