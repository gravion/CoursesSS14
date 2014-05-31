package myu06;

public class Ellipsoid {
    // form: ax²+bxy+cy²+dx+ey+f<=0
    private double a, b, c, d, e, f;
    
    public Ellipsoid(){
        this.a = 0.0;
        this.b = 0.0;
        this.c = 0.0;
        this.d = 0.0;
        this.e = 0.0;
        this.f = 0.0;
    }
    
    public Ellipsoid(double a, double b, double c,
            double d, double e, double f){
       this.a = a;
       this.b = b;
       this.c = c;
       this.d = d;
       this.e = e;
       this.f = f;
    }
    
    public boolean pointInEllipsoid(double x, double y){
        if(a*x*x+b*x*y+c*y*y+d*x+e*y+f <= 0){
            return true;
        }
        return false;
    }
}
