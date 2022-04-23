import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class CVue {

    private JFrame frame;

    private VueGrille grille;
    private VueCommandes commandes;
    BufferedImage img;
    
    {
        try {
            img = ImageIO.read(new File("res\\images\\background.png"));
        } catch(IOException ie) {
            ie.printStackTrace();
        }
    }

    public CVue(CModele modele) {

        frame = new JFrame();
        frame.setTitle("Forbidden Island");

        frame.setLayout(new BorderLayout());
        JLabel background=new JLabel(new ImageIcon(img.getScaledInstance(1280, 720, Image.SCALE_DEFAULT)));

        // Image newImage = yourImage.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);

        frame.add(background);


        background.setLayout(new FlowLayout(FlowLayout.LEFT, 75, 75));

        grille = new VueGrille(modele);
        background.add(grille);
        commandes = new VueCommandes(modele);
        background.add(commandes);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}