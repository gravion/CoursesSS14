package myue05;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GrafikDemoProgramm {
    private static void createAndShowGUI() {
        // Create and set up the window.
        JFrame frame = new JFrame("Grafik-Testprogramm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        JPanel panel = new TestPanel();

        frame.getContentPane().add(panel);
        // Display the window.
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
    int weight = 1; //hier die Rechteckgröße ändern
    AlgorithmXp a;
    Point eins = new Point(); // linker mouse knopf
    Point zwei = new Point(); // rechter mouse knopf

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.gray);
        Dimension d = getSize(); // loesche die Anzeige
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.white);
        mypaint(eins, zwei, g);
        g.fillRect(eins.x-1, eins.y-1, weight+2, weight+2);
        g.fillRect(zwei.x-1, zwei.y-1, weight+2, weight+2);
    }

    public TestPanel() {
        a = new AlgorithmXp(weight);
        addMouseListener(this);
    }

    // /////////////////////////
    // Bresenham Algorithmus  //
    // /////////////////////////

    // soll mit übergabe die punkte entsprechend einzeichnen
    public void mypaint(Point p1, Point p2, Graphics g) {
        double m;
        
        if (p1.x != p2.x) {
            m = (double)(p2.y - p1.y) / (double)(p2.x - p1.x);
        }else {
            if(p1.y > p2.y){
                g.fillRect(p1.x, p2.y, 1, p1.y - p2.y);
                //g.fillRect(p1.x, p2.y, 4, p1.y - p2.y);
            }else{
                g.fillRect(p1.x, p1.y, 1, p2.y - p1.y);
                //g.fillRect(p1.x, p2.y, 4, p1.y - p2.y);
            }
            return;
        }
        m = -m;

        // rechts vonscheidung nach der Steigung
        if (m > 0 && m <= 1) {
            if (p1.x < p2.x) {
                // p1 links von p2
                a.mk1(p2, p1, g);
            } else {
                // p1 rechts von p2
                a.mk1(p1, p2, g);
            }
        } else if (m >= 1) {
            if (p1.x < p2.x) {
                // p1 links von p2
                a.mg1(p1, p2, g);
            } else {
                // p1 rechts von p2
                a.mg1(p2, p1, g);
            }
        } else if (m <= -1) {
            if (p1.x < p2.x) {
                // p1 links von p2
                a.mkm1(p1, p2, g);
            } else {
                // p1 rechts von p2
                a.mkm1(p2, p1, g);
            }
        } else if (m > -1) {
            if (p1.x < p2.x) {
                // p1 links von p2
                a.mgm1(p1, p2, g);
            } else {
                // p1 rechts von p2
                a.mgm1(p2, p1, g);
            }
        }
    }

    ////////////////////
    // Eingabe (Maus) //
    ////////////////////

    public void mouseReleased(MouseEvent e) { ; }
    public void mouseEntered(MouseEvent e) { ; }
    public void mouseExited(MouseEvent e) { ; }
    public void mouseClicked(MouseEvent e) { ; }
    public void mouseMoved(MouseEvent e) { ; }
    public void mouseDragged(MouseEvent e) { ; }
    
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            eins.setLocation(e.getX(), e.getY());
        } else {
            zwei.setLocation(e.getX(), e.getY());
        }
        repaint();
    }
}
