package ForbiddenIsland;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class ZoneCliquable extends JPanel implements MouseListener {

    public ZoneCliquable(int x, int y) {
        setPreferredSize(new Dimension(x, y));
        addMouseListener(this);
        setBackground(Color.WHITE);
    }

    public abstract void clicGauche();

    public abstract void clicDroit();

    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            this.clicDroit();
        } else {
            this.clicGauche();
        }
    }
}