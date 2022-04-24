import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

class CVue {

    static private JFrame frame = new JFrame();

    {
        frame.setTitle("Forbidden Island");
    }

    static private JPanel game = new JPanel();
    static private JPanel win = new JPanel();
    static private JPanel end = new JPanel();

    private VueGrille grille;
    private VueCommandes commandes;
    
    static BufferedImage img;
    static BufferedImage lose;
    static BufferedImage victory;

    {
        try {
            img = ImageIO.read(new File("res\\images\\background.png"));
            lose = ImageIO.read(new File("res\\images\\GameOver.png"));
            victory = ImageIO.read(new File("res\\images\\win.jpg"));
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    public CVue(CModele modele) {

        game.setLayout(new BorderLayout());
        JLabel background = new JLabel(new ImageIcon(img.getScaledInstance(1650, 720, Image.SCALE_DEFAULT)));

        game.add(background);

        background.setLayout(new FlowLayout(FlowLayout.LEFT, 75, 75));

        grille = new VueGrille(modele);
        background.add(grille);
        commandes = new VueCommandes(modele);
        background.add(commandes);

        frame.setContentPane(game);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    static void gameOver() {

        frame.remove(game);

        end.setLayout(new BorderLayout());
        JLabel background = new JLabel(new ImageIcon(lose.getScaledInstance(1280, 720, Image.SCALE_DEFAULT)));

        end.add(background);
        
        frame.setContentPane(end);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        try {
            String soundName = "res\\images\\YouDied.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ie) {
        }
    }

    static void win() {

        frame.remove(game);

        win.setLayout(new BorderLayout());
        JLabel background = new JLabel(new ImageIcon(victory.getScaledInstance(1280, 720, Image.SCALE_DEFAULT)));

        win.add(background);
        
        frame.setContentPane(win);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        try {
            String soundName = "res\\images\\YouWin2.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ie) {
        }
    }
}