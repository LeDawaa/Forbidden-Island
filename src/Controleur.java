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
            case "Trade key":
                if (actions >= 1) {
                    Player p = modele.Players[Integer.parseInt(VueCommandes.tradePlayer.getText()) - 1];
                    Keys k;
                    switch (VueCommandes.tradeKey.getText().toLowerCase()) {
                        case "fire" -> k = Keys.FireKey;
                        case "water" -> k = Keys.WaterKey;
                        case "wind" -> k = Keys.WindKey;
                        case "earth" -> k = Keys.EarthKey;
                        default -> k = null;
                    }
                    if (p.z.equals(modele.getCurrentPlayer().z) && k != null
                            && modele.getCurrentPlayer().keys.get(k) > 0) {
                        modele.getCurrentPlayer().keys.put(k, modele.getCurrentPlayer().keys.get(k) - 1);
                        p.keys.put(k, modele.getCurrentPlayer().keys.get(k) + 1);
                        VueCommandes.labels.get(p.id)
                                .setText("\u274C"
                                        + " Player " + (p.id + 1) + " : "
                                        + modele.getCurrentPlayer().keyList());
                        VueCommandes.labels.get(modele.currentPlayer)
                                .setText("\u2705"
                                        + " Player " + (modele.currentPlayer + 1) + " : "
                                        + modele.getCurrentPlayer().keyList());
                        actions--;
                    }
                }
                break;
            case "Sand cannon":
                if (actions >= 1) {
                    int x = Integer.parseInt(VueCommandes.sandCoordX.getText()) + 1;
                    int y = Integer.parseInt(VueCommandes.sandCoordX.getText()) + 1;
                    if (modele.getZone(x, y).dryZone()) {
                        actions--;
                    }
                }
                break;
            case "Helicopter movement":
                if (actions >= 1) {
                    int x = Integer.parseInt(VueCommandes.heliCoordX.getText()) + 1;
                    int y = Integer.parseInt(VueCommandes.heliCoordX.getText()) + 1;
                    if (modele.getZone(x, y).isAccessible()) {
                        for (int i = 0; i < modele.getCurrentPlayer().z.players.size(); i++) {
                            modele.getCurrentPlayer().z.players.get(i).movePlayer(modele.getZone(x, y));
                        }
                        actions--;
                    }
                }
                break;
            case "G O":
                CVue.gameOver();
                break;
            case "WIN":
                CVue.win();
                break;
            case "T A":
                if (actions >= 1 && modele.getCurrentPlayer().takeArtifact()) {
                    actions--;
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

                if (Math.random() < 0.6) {
                    Keys key = Keys.values()[new Random().nextInt(Keys.values().length)];
                    modele.getCurrentPlayer().keys.put(key, modele.getCurrentPlayer().keys.get(key) + 1);
                }

                VueCommandes.labels.get(modele.currentPlayer)
                        .setText("\u274C"
                                + " Player " + (modele.currentPlayer + 1) + " : "
                                + modele.getCurrentPlayer().keyList());
                modele.currentPlayer = (modele.currentPlayer + 1) % CModele.PLAYER_COUNT;
                VueCommandes.labels.get(modele.currentPlayer)
                        .setText("\u2705"
                                + " Player " + (modele.currentPlayer + 1) + " : "
                                + modele.getCurrentPlayer().keyList());

                actions = 3;
                VueCommandes.dryButtons.setVisible(true);

                break;
            default:
                System.err.println("Unknown Action " + e.getActionCommand());
        }
        VueCommandes.labels.get(VueCommandes.labels.size() - 1).setText("Actions left : " + actions);

        boolean onHeliport = true;
        List<Artifacts> totalArtifacts = new ArrayList<Artifacts>();
        List<Artifacts> allArtifacts = new ArrayList<Artifacts>(Arrays.asList(Artifacts.values()));

        outer: for (Player player : modele.Players) {
            if (!(player.z.c.equals(Case.FoolsLanding))) {
                onHeliport = false;
                break outer;
            }
            totalArtifacts.addAll(player.artifacts);
        }
        if (onHeliport) {
            Collections.sort(totalArtifacts);
            Collections.sort(allArtifacts);
            if (totalArtifacts.equals(allArtifacts))
                CVue.win();
        }
    }
}