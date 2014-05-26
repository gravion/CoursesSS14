package myue05;

import java.awt.Graphics;
import java.awt.Point;

public class Algorithm4p {
    public static void mk1(Point p1, Point p2, Graphics g){
        int x = p2.x - p1.x;
        int y = p2.y - p1.y;
        int s = 4*y + 2*x + 4;
        int ne = 4*(x+y);
        int e = 4*y;
        int j = p1.y;
        for(int i = 0; i < x; i+=4){
            g.fillRect(p1.x + i, j, 4, 4);
            if(s < 0){
                j -= 4;
                s += ne;
            }else{
                s += e;
            }
        }
    }

    public static void mg1(Point p1, Point p2, Graphics g){
        int x = p2.x - p1.x;
        int y = p2.y - p1.y;
        int s = 4*x + 2*y + 4;
        int ne = 4*(x+y);
        int e = 4*x;
        int i = p1.x;
        for(int j = 0; j > y; j-=4){
            g.fillRect(i, p1.y + j, 4, 4);
            if(s > 0){
                i += 4;
                s += ne;
            }else{
                s += e;
            }
        }
    }
    
    public static void mkm1(Point p1, Point p2, Graphics g){
        int x = p2.x - p1.x;
        int y = p2.y - p1.y;
        int s = 4*x - 2*y + 4;
        int ne = 4*(x-y);
        int e = 4*x;
        int i = p1.x;
        for(int j = 0; j < y; j+=4){
            g.fillRect(i, p1.y + j, 4, 4);
            if(s > 0){
                i += 4;
                s += ne;
            }else{
                s += e;
            }
        }
    }
    
    public static void mgm1(Point p1, Point p2, Graphics g){
        int x = p2.x - p1.x;
        int y = p2.y - p1.y;
        int s = 4*y - 2*x + 4;
        int ne = 4*(y-x);
        int e = 4*y;
        int j = p1.y;
        for(int i = 0; i < x; i+=4){
            g.fillRect(p1.x + i, j, 4, 4);
            if(s > 0){
                j += 4;
                s += ne;
            }else{
                s += e;
            }
        }
    }    
}
