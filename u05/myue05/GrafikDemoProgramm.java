package myue05;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

//import java.awt.geom.Point2D;

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
    Point eins = new Point(); // linker mouse knopf
    Point zwei = new Point(); // rechter mouse knopf

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.gray);
        Dimension d = getSize(); // loesche die Anzeige
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.white);
        mypaint(eins, zwei, g);
        g.fillRect(eins.x, eins.y, 4, 4);
        g.fillRect(zwei.x, zwei.y, 4, 4);
    }

    public TestPanel() {
        addMouseListener(this);
    }

    // /////////////////////////
    // Bresenham Algorithmus //
    // /////////////////////////

    // soll mit übergabe die punkte entsprechend einzeichnen
    public void drawpoint() {

    }

    public void mypaint(Point p1, Point p2, Graphics g) {

        int x_start, x_end, y_start, y_end;
        double m;

        if (p1.x - p2.x != 0) {
            m = (p1.y - p2.y) / (p1.x - p2.x);
        }else {
            m=1;
        }

        // Unterscheidung nach der Steigung
        if (m < -1) {
            // p1 über p2
            if (p1.x < p2.x) {
                x_start = p1.x;
                x_end = p2.x;
                y_start = p1.y;
                y_end = p2.y;
            } else {
                // p2 über p1
                x_start = p2.x;
                x_end = p1.x;
                y_start = p2.y;
                y_end = p1.y;
            }
            g.drawLine(x_start, y_start, x_end, y_end);
            for (int i = 0; i < y_end; y = y - 4) {
                x = (int) Math.round(x_start - i * (y_start - i) / m);
                g.fillRect(x, y_start - i, 4, 4);
            }

        } else if (m <= -1 && m < 0) {
            // p1 über p2
            if (p1.x < p2.x) {

            } else {
                // p2 über p1

            }
        } else if (m <= 0 && m < 1) {
            // p1 über p2
            if (p1.x < p2.x) {

            } else {
                // p2 über p1

            }
        } else if (m <= 1) {
            // p1 über p2
            if (p1.x < p2.x) {

            } else {
                // p2 über p1

            }
        }

        /*
         * if (p1.x < p2.x) { x_start = p1.x; y_start = p1.y; x_end = p2.x; if
         * (p1.y > p2.y) { for (int i = x_start; i < x_end; i += 4) { int y; y =
         * y_start + Math.round((i-x_start) * (p2.y - p1.y) / (p2.x - p1.x));
         * g.fillRect(i, y, 4, 4); } }else { for (int i = x_start; i < x_end; i
         * += 4) { int y; y = y_start - Math.round(i * (p2.y - p1.y) / (p2.x -
         * p1.x)); g.fillRect(i,y, 4, 4); } } } else { x_start = p2.x; y_start =
         * p2.y; x_end = p1.x; for (int i = x_start; i < x_end; i += 4) { int y;
         * y = y_start + Math.round((i-x_start) * (p2.y - p1.y) / (p2.x -
         * p1.x)); g.fillRect(i, y_start + y, 4, 4);
         * 
         * } }
         */
    }

    // ////////////////////
    // Eingabe (Maus) //
    // ////////////////////

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        // x = e.getX();
        // y = e.getY();
        // repaint();
        if (e.getButton() == MouseEvent.BUTTON1) {
            eins.setLocation(e.getX(), e.getY());
        } else {
            zwei.setLocation(e.getX(), e.getY());
        }
        // System.out.println("Punkt Eins = "+eins.x+" "+eins.y);
        // System.out.println("Punkt Zwei = "+zwei.x+" "+zwei.y);
        // mypaint();
        repaint();
    }

    // TODO linie zeichnen

}