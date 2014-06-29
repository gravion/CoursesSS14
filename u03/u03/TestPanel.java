package u03;

import javax.swing.*;        

import java.awt.*;
import java.awt.event.*;


@SuppressWarnings("serial")
class TestPanel extends JPanel implements MouseListener {
    int x = 10;
    int y = 10;

    @Override
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


    @Override
    public void mouseReleased(MouseEvent e) {
    	repaint();
    }
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    // mousePressed(): Mausknopf wurde betaetigt
    // Hier: neue Punktkoordinaten aufnehmen
    @Override
    public void mousePressed(MouseEvent e) {
	  x = e.getX();
	  y = e.getY();
	  repaint();
    }

}
