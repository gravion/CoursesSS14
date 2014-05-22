import javax.swing.*;        

import java.awt.*;
import java.awt.event.*;


@SuppressWarnings("serial")
class TestPanel extends JPanel implements MouseListener {
    int x = 10;
    int y = 10;

    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.setColor(Color.gray);
      Dimension d=getSize();  // loesche die Anzeige
      g.fillRect(0,0,d.width,d.height);
      
      g.setColor(Color.white);
      g.fillRect(x,y,4,4);
    }

    public TestPanel() {
	addMouseListener(this);
    }

//////////////////////
//  Eingabe (Maus)  //
//////////////////////


    public void mouseReleased(MouseEvent e) {
    	repaint();
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    // mousePressed(): Mausknopf wurde betaetigt
    // Hier: neue Punktkoordinaten aufnehmen
    public void mousePressed(MouseEvent e) {
	  x = e.getX();
	  y = e.getY();
	  repaint();
    }

}
