package u05;

import java.awt.Graphics;
import java.awt.Point;

public class AlgorithmXp {
    private int weight;
    
    public AlgorithmXp(int weight){
        this.setWeight(weight);
    }
    
    public void setWeight(int weight){
        if(weight < 1){
            this.weight = 1;
        }else{
            this.weight = weight;
        }
    }
    
    public void mk1(Point p1, Point p2, Graphics g){
        int x = p2.x - p1.x;
        int y = p2.y - p1.y;
        int s = 4*y + 2*x + weight;
        int ne = 4*(x+y);
        int e = 4*y;
        int j = 0;
        for(int i = 0; i < -x; i+=weight){
            g.fillRect(p1.x - i, p1.y + j, weight, weight);
            if(s > 0){
                j += weight;
                s += ne;
            }else{
                s += e;
            }
        }
    }

    public void mg1(Point p1, Point p2, Graphics g){
        int x = p2.x - p1.x;
        int y = p2.y - p1.y;
        int s = 4*x + 2*y + weight;
        int ne = 4*(x+y);
        int e = 4*x;
        int i = 0;
        for(int j = 0; j < -y; j+=weight){
            g.fillRect(p1.x + i, p1.y - j, weight, weight);
            if(s > 0){
                i += weight;
                s += ne;
            }else{
                s += e;
            }
        }
    }
    
    public void mkm1(Point p1, Point p2, Graphics g){
        int x = p2.x - p1.x;
        int y = p2.y - p1.y;
        int s = 4*x - 2*y + weight;
        int ne = 4*(x-y);
        int e = 4*x;
        int i = 0;
        for(int j = 0; j < y; j+=weight){
            g.fillRect(p1.x + i, p1.y + j, weight, weight);
            if(s > 0){
                i += weight;
                s += ne;
            }else{
                s += e;
            }
        }
    }
    
    public void mgm1(Point p1, Point p2, Graphics g){
        int x = p2.x - p1.x;
        int y = p2.y - p1.y;
        int s = 4*y - 2*x + weight;
        int ne = 4*(y-x);
        int e = 4*y;
        int j = 0;
        for(int i = 0; i < x; i+=weight){
            g.fillRect(p1.x + i, p1.y+j, weight, weight);
            if(s > 0){
                j += weight;
                s += ne;
            }else{
                s += e;
            }
        }
    }    
}
