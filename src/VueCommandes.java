import java.util.*;
import java.util.List;

import javax.swing.*;

import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JPanel;

class VueCommandes extends JPanel {

    private CModele modele;

    List<JLabel> labels = new ArrayList<JLabel>();

    public VueCommandes(CModele modele) {
        this.modele = modele;

        JPanel pannel = new JPanel();
        pannel.setLayout(new GridLayout(3, 1));

         ////////////////////////////////////////////////////////////////

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

        pannel.add(top);

        ////////////////////////////////////////////////////////////////

        JPanel movementButtons = new JPanel();
        movementButtons.setLayout(new GridLayout(3, 3));

        String[] keyMovement = { " ", "↑", " ", "←", "EOT", "→", " ", "↓", " " };
        for (int i = 0; i < keyMovement.length; i++) {
            JButton button = new JButton(keyMovement[i]);
            if (keyMovement[i] == " ")
                button.setVisible(false);
            movementButtons.add(button);
        }
        pannel.add(movementButtons);

        ////////////////////////////////////////////////////////////////

        JPanel dryButtons = new JPanel();
        dryButtons.setLayout(new GridLayout(3, 3));

        String[] keyDry = { " ", "DRY UP", " ", "DRY LEFT", "DRY HERE", "DRY RIGHT", " ", "DRY DOWN", " " };
        for (int i = 0; i < keyDry.length; i++) {
            JButton button = new JButton(keyDry[i]);
            if (keyDry[i] == " ")
                button.setVisible(false);
            dryButtons.add(button);
        }
        pannel.add(dryButtons);

        ////////////////////////////////////////////////////////////////

        Controleur ctrl = new Controleur(modele, labels, dryButtons);

        ////////////////////////////////////////////////////////////////

        List<Component> buttonsList = Arrays.asList(movementButtons.getComponents());

        for (int i = 0; i < buttonsList.size(); i++) {
            if (buttonsList.get(i) instanceof JButton) {
                ((JButton) buttonsList.get(i)).addActionListener(ctrl);
            }
        }

        buttonsList = Arrays.asList(dryButtons.getComponents());

        for (int i = 0; i < buttonsList.size(); i++) {
            if (buttonsList.get(i) instanceof JButton) {
                ((JButton) buttonsList.get(i)).addActionListener(ctrl);
            }
        }

        ////////////////////////////////////////////////////////////////

        add(pannel);
    }
}