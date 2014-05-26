package myue05;

import java.awt.Graphics;
import java.awt.Point;

public class Algorithm {
    public static void bla(Point p1, Point p2, Graphics g) {
        int x_start, x_end, y_start, y_end;

        x_start = p1.x;
        y_start = p1.y;
        x_end = p2.x;
        y_end = p2.y;
        if (p1.y > p2.y) {
            for (int i = x_start; i < x_end; i += 4) {
                int y;
                y = y_start + Math.round((i - x_start) * (p2.y - p1.y) / (p2.x - p1.x));
                g.fillRect(i, y, 4, 4);
            }
        } else {
            for (int i = x_start; i < x_end; i += 4) {
                int y;
                y = y_start - Math.round(i * (p2.y - p1.y) / (p2.x - p1.x));
                g.fillRect(i, y, 4, 4);
            }
        }
    }

    public static void mk1(Point p1, Point p2, Graphics g){
        System.out.println("mk1");
        for (int i = p1.x; i < p2.x; i += 4) {
            int y;
            y = p1.y + Math.round((i - p1.x) * (p2.y - p1.y) / (p2.x - p1.x));
            g.fillRect(i, y, 4, 4);
        }
    }

    public static void mg1(Point p1, Point p2, Graphics g){
        System.out.println("mg1");
        
        for (int i = p1.x; i < (p1.y-p2.y+p1.x); i += 4) {
            int y;
            y = p1.y + Math.round((i - p1.x) * ((p1.x-p2.x+p1.y) - p1.y) / ((p1.y-p2.y+p1.x) - p1.x));
            g.fillRect((p1.y-y+p1.x), (p1.x-i+p1.y), 4, 4);
        }        
    }
    
    public static void mkm1(Point p1, Point p2, Graphics g){
        System.out.println("mkm1");
        
        for (int i = p1.x; i < (p2.y-p1.y+p1.x); i += 4) {
            int y;
            y = p1.y + Math.round((i - p1.x) * ((p2.x-p1.x+p1.y) - p1.y) / ((p2.y-p1.y+p1.x) - p1.x));
            g.fillRect((y-p1.y+p1.x), (i-p1.x+p1.y), 4, 4);
        }  
    }
    
    public static void mgm1(Point p1, Point p2, Graphics g){
        System.out.println("mgm1");
        for (int i = p1.x; i < p2.x; i += 4) {
            int y;
            y = p1.y + Math.round((i - p1.x) * (p2.y - p1.y) / (p2.x - p1.x));
            g.fillRect(i, y, 4, 4);
        }
    }    
}
