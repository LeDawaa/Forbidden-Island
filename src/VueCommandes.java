import java.util.*;
import java.util.List;
import javax.swing.*;
import java.awt.GridLayout;
import javax.swing.JPanel;

import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class VueCommandes extends JPanel {

    private CModele modele;

    static List<JLabel> labels = new ArrayList<JLabel>();

    static JPanel movementButtons = new JPanel();

    static JPanel dryButtons = new JPanel();

    static BufferedImage inventory;
    static BufferedImage None;
    static BufferedImage Middle_Tower;
    static BufferedImage Top_Tower;

    {
        try {
            inventory = ImageIO.read(new File("res\\images\\ptjgqon6k5j71.jpg"));
            None = ImageIO.read(new File("res\\images\\pion\\none2.png"));
            Middle_Tower = ImageIO.read(new File("res\\images\\Middle_tower.PNG"));
            Top_Tower = ImageIO.read(new File("res\\images\\Top_tower.PNG"));
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    static JTextField tradePlayer = new JTextField();
    static JTextField tradeKey = new JTextField();

    static JTextField sandCoordX = new JTextField();
    static JTextField sandCoordY = new JTextField();

    static JTextField heliCoordX = new JTextField();
    static JTextField heliCoordY = new JTextField();

    static JLabel WindWilder = new JLabel();
    static JLabel FireWilder = new JLabel();
    static JLabel WaterWilder = new JLabel();
    static JLabel EarthWilder = new JLabel();

    {
        WindWilder.setIcon(new ImageIcon(None.getScaledInstance(50, 73, Image.SCALE_DEFAULT)));
        FireWilder.setIcon(new ImageIcon(None.getScaledInstance(50, 73, Image.SCALE_DEFAULT)));
        WaterWilder.setIcon(new ImageIcon(None.getScaledInstance(50, 73, Image.SCALE_DEFAULT)));
        EarthWilder.setIcon(new ImageIcon(None.getScaledInstance(50, 73, Image.SCALE_DEFAULT)));
    }

    public VueCommandes(CModele modele) {
        this.modele = modele;

        Controleur ctrl = new Controleur(modele);

        JPanel pannel = new JPanel();

        JLabel background = new JLabel(); // new ImageIcon(inventory.getScaledInstance(840, 500, Image.SCALE_DEFAULT))

        background.setPreferredSize(new Dimension(840, 600));
        background.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(25, 25, 25, 25);

        pannel.add(background);

        pannel.setOpaque(false);

        //////////////////////////////////////////////////////////////// MOVEMENT

        movementButtons.setLayout(new GridLayout(3, 3));

        String[] keyMovement = { "↖", "↑", "↗", "←", "EOT", "→", "↙", "↓", "↘" };
        for (int i = 0; i < keyMovement.length; i++) {
            JButton button = new JButton(keyMovement[i]);
            if ("↖↗↙↘".contains(keyMovement[i]))
                button.setVisible(false);
            movementButtons.add(button);
            button.addActionListener(ctrl);
        }
        movementButtons.setOpaque(false);

        c.weightx = c.weighty = 1;
        c.gridwidth = c.gridheight = 1;

        c.gridx = 2;
        c.gridy = 0;
        background.add(movementButtons, c);

        //////////////////////////////////////////////////////////////// DRY PANNEL

        dryButtons.setLayout(new GridLayout(3, 3));

        String[] keyDry = { "T A", "DRY UP", "G O", "DRY LEFT", "DRY HERE", "DRY RIGHT", "WIN", "DRY DOWN", "TRADE" };
        for (int i = 0; i < keyDry.length; i++) {
            JButton button = new JButton(keyDry[i]);
            if (keyDry[i] == " ")
                button.setVisible(false);
            dryButtons.add(button);
            button.addActionListener(ctrl);
        }
        dryButtons.setOpaque(false);

        c.gridy = 1;
        background.add(dryButtons, c);

        ////////////////////////////////////////////////////////////////////////////// SPECIALS

        //////////////////////////////////////////////////////////////// HELI TP

        JPanel heliPanel = new JPanel();
        JPanel topHeliPanel = new JPanel();
        JPanel bottomHeliPanel = new JPanel();
        JButton heli = new JButton("Helicopter movement");
        heli.addActionListener(ctrl);

        topHeliPanel.setLayout(new GridLayout(1, 2));

        topHeliPanel.add(new JLabel("Coord X : ", SwingConstants.CENTER));
        topHeliPanel.add(heliCoordX);

        topHeliPanel.setBackground(new Color(255, 255, 255, 128));

        bottomHeliPanel.setLayout(new GridLayout(1, 2));

        bottomHeliPanel.add(new JLabel("Coord Y : ", SwingConstants.CENTER));
        bottomHeliPanel.add(heliCoordY);

        bottomHeliPanel.setBackground(new Color(255, 255, 255, 128));

        heliPanel.setLayout(new GridLayout(3, 1));

        heliPanel.add(topHeliPanel);
        heliPanel.add(bottomHeliPanel);
        heliPanel.add(heli);

        c.gridy = 2;
        c.gridx = 0;
        heliPanel.setBackground(new Color(255, 255, 255, 128));
        background.add(heliPanel, c);

        //////////////////////////////////////////////////////////////// SAND CANNON

        JPanel sandPanel = new JPanel();
        JPanel topSandPanel = new JPanel();
        JPanel bottomSandPanel = new JPanel();
        JButton sand = new JButton("Sand cannon");
        sand.addActionListener(ctrl);

        topSandPanel.setLayout(new GridLayout(1, 2));

        topSandPanel.add(new JLabel("Coord X : ", SwingConstants.CENTER));
        topSandPanel.add(sandCoordX);

        topSandPanel.setBackground(new Color(255, 255, 255, 128));

        bottomSandPanel.setLayout(new GridLayout(1, 2));

        bottomSandPanel.add(new JLabel("Coord Y : ", SwingConstants.CENTER));
        bottomSandPanel.add(sandCoordY);

        bottomSandPanel.setBackground(new Color(255, 255, 255, 128));

        sandPanel.setLayout(new GridLayout(3, 1));

        sandPanel.add(topSandPanel);
        sandPanel.add(bottomSandPanel);
        sandPanel.add(sand);

        c.gridx = 1;
        sandPanel.setBackground(new Color(255, 255, 255, 128));
        background.add(sandPanel, c);

        //////////////////////////////////////////////////////////////// TRADE

        JPanel tradePanel = new JPanel();
        JPanel topTradePanel = new JPanel();
        JPanel bottomTradePanel = new JPanel();
        JButton trade = new JButton("Trade key");
        trade.addActionListener(ctrl);

        topTradePanel.setLayout(new GridLayout(1, 2));

        topTradePanel.add(new JLabel("Select player :", SwingConstants.CENTER));
        topTradePanel.add(tradePlayer);

        topTradePanel.setBackground(new Color(255, 255, 255, 128));

        bottomTradePanel.setLayout(new GridLayout(1, 2));

        bottomTradePanel.add(new JLabel("Select key :", SwingConstants.CENTER));
        bottomTradePanel.add(tradeKey);

        bottomTradePanel.setBackground(new Color(255, 255, 255, 128));

        tradePanel.setLayout(new GridLayout(3, 1));

        tradePanel.add(topTradePanel);
        tradePanel.add(bottomTradePanel);
        tradePanel.add(trade);

        c.gridx = 2;
        tradePanel.setBackground(new Color(255, 255, 255, 128));
        background.add(tradePanel, c);

        ////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////// LABEL

        JLabel top = new JLabel(new ImageIcon(Top_Tower));
        top.setOpaque(false);
        top.setLayout(new GridLayout(CModele.PLAYER_COUNT + 1, 1));

        for (int i = 0; i < CModele.PLAYER_COUNT; i++) {
            JLabel label = new JLabel(((i == modele.currentPlayer) ? " \u2705" : "\u274C")
                    + " Player " + (i + 1) + " : "
                    + modele.getCurrentPlayer().keyList());
            label.setHorizontalAlignment(SwingConstants.CENTER);
            labels.add(label);
            top.add(label);
        }

        JLabel labelActionsLeft = new JLabel("Actions left : " + 3);
        labelActionsLeft.setFont(new Font("SansSerif", Font.PLAIN, 24));
        labelActionsLeft.setHorizontalAlignment(SwingConstants.CENTER);
        labels.add(labelActionsLeft);
        top.add(labelActionsLeft);

        c.weightx = 2;
        c.weighty = 1;
        c.gridwidth = 2;
        c.gridheight = 1;

        c.gridx = c.gridy = 0;

        top.setBackground(new Color(255, 255, 255, 128));
        background.add(top, c);

        JLabel playerInfos = new JLabel(new ImageIcon(Middle_Tower));
        playerInfos.setOpaque(false);
        playerInfos.setLayout(new GridLayout(2, 4));

        JLabel Earth = new JLabel(new ImageIcon(Artifacts.EarthArtifact.card_texture.getScaledInstance(64, 94, Image.SCALE_DEFAULT)));
        JLabel Fire = new JLabel(new ImageIcon(Artifacts.FireArtifact.card_texture.getScaledInstance(64, 94, Image.SCALE_DEFAULT)));
        JLabel Water = new JLabel(new ImageIcon(Artifacts.WaterArtifact.card_texture.getScaledInstance(64, 94, Image.SCALE_DEFAULT)));
        JLabel Wind = new JLabel(new ImageIcon(Artifacts.WindArtifact.card_texture.getScaledInstance(64, 94, Image.SCALE_DEFAULT)));

        playerInfos.add(Earth);
        playerInfos.add(Fire);
        playerInfos.add(Water);
        playerInfos.add(Wind);

        playerInfos.add(EarthWilder);
        playerInfos.add(FireWilder);
        playerInfos.add(WaterWilder);
        playerInfos.add(WindWilder);

        c.gridx = 0;
        c.gridy = 1;
        
        background.add(playerInfos, c);

        add(pannel);
        setOpaque(false);
    }
}