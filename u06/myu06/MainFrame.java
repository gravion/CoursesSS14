package myu06;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

    MainFrame mainFrame;
    Ellipsoid e;
    PaintPanel panel;
    Options o;
    
    public MainFrame(){
        // Create and set up the window.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 700);
        panel = new PaintPanel();

        this.getContentPane().add(panel);
        // Display the window.
        this.setVisible(true);
        
        mainFrame = this;
        o = new Options(this);
        o.show();
    }
    
    public void paintEllipsoid(double a, double b, double c, double d, double e, double f){
        this.e = new Ellipsoid(a, b, c, d, e, f);
        panel.repaint();
    }
    
    public static void main(String[] args){
        new MainFrame();
    }
    
    class PaintPanel extends JPanel{
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.gray);
            Dimension d = getSize(); // loesche die Anzeige
            for(int i = 0; i < mainFrame.getWidth(); i++){
                for(int j = 0; j < mainFrame.getHeight(); j++){
                    if(e.pointInEllipsoid(((double)i-(int)(mainFrame.getWidth()/2))/10, ((double)j-(int)(mainFrame.getHeight()/2))/10)){
                        g.setColor(Color.white);
                    }else{
                        g.setColor(Color.gray);
                    }
                    g.fillRect(i, j, 1, 1);
                }
            }
        }
    }
}