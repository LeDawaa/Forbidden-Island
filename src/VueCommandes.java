import java.util.*;
import java.util.List;
import javax.swing.*;
import java.awt.GridLayout;
import javax.swing.JPanel;

class VueCommandes extends JPanel {

    private CModele modele;

    static List<JLabel> labels = new ArrayList<JLabel>();

    static JPanel movementButtons = new JPanel();

    static JPanel dryButtons = new JPanel();

    public VueCommandes(CModele modele) {
        this.modele = modele;

        Controleur ctrl = new Controleur(modele);

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

        movementButtons.setLayout(new GridLayout(3, 3));

        String[] keyMovement = { "↖", "↑", "↗", "←", "EOT", "→", "↙", "↓", "↘" };
        for (int i = 0; i < keyMovement.length; i++) {
            JButton button = new JButton(keyMovement[i]);
            if ("↖↗↙↘".contains(keyMovement[i]))
                button.setEnabled(false);
            movementButtons.add(button);
            button.addActionListener(ctrl);
        }
        pannel.add(movementButtons);

        ////////////////////////////////////////////////////////////////
        
        dryButtons.setLayout(new GridLayout(3, 3));

        String[] keyDry = { "T A", "DRY UP", " ", "DRY LEFT", "DRY HERE", "DRY RIGHT", " ", "DRY DOWN", " " };
        for (int i = 0; i < keyDry.length; i++) {
            JButton button = new JButton(keyDry[i]);
            if (keyDry[i] == " ")
                button.setVisible(false);
            dryButtons.add(button);
            button.addActionListener(ctrl);
        }
        pannel.add(dryButtons);

        ////////////////////////////////////////////////////////////////

        add(pannel);
    }
}