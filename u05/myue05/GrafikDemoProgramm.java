package myue05;

import javax.swing.*;        

import java.awt.*;
import java.awt.event.*;
//import java.awt.geom.Point2D;

public class GrafikDemoProgramm {
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Grafik-Testprogramm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        JPanel panel = new TestPanel();

        frame.getContentPane().add(panel);
        //Display the window.
        frame.setVisible(true);
    }

    
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}


@SuppressWarnings("serial")
class TestPanel extends JPanel implements MouseListener {
    int x = 10;
    int y = 10;
    Point eins = new Point(); // linker mouse knopf
    Point zwei = new Point(); // rechter mouse knopf
    
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.setColor(Color.gray);
      Dimension d=getSize();  // loesche die Anzeige
      g.fillRect(0,0,d.width,d.height);
      g.setColor(Color.white);
      mypaint(eins, zwei,g);
      g.fillRect(eins.x,eins.y,4,4);
      g.fillRect(zwei.x, zwei.y, 4, 4);
    }

    public TestPanel() {
    addMouseListener(this);
    }
///////////////////////////
//Bresenham Algorithmus  //
///////////////////////////

    
    public void mypaint(Point p1, Point p2,Graphics g) {
        
        int x_start,x_end,y_start,y_end;
        
        
        
        
        for (int i = x_start; i < x_end ; i+=4) {
            int y;
            y = y_start + Math.round(i*(p2.y-p1.y)/(p2.x - p1.x));
            g.fillRect(i, y_start + y,4,4);
            
        }
        
    }
    

//////////////////////
//  Eingabe (Maus)  //
//////////////////////


    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
//      x = e.getX();
//      y = e.getY();
//      repaint();
        if(e.getButton() == MouseEvent.BUTTON1) {
            eins.setLocation(e.getX(), e.getY());
        }else {
            zwei.setLocation(e.getX(), e.getY());
        }
//        System.out.println("Punkt Eins = "+eins.x+" "+eins.y);
//        System.out.println("Punkt Zwei = "+zwei.x+" "+zwei.y);
//        mypaint();
        repaint();
    }

    
//    TODO linie zeichnen
    
}