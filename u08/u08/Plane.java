package u08;

import Jama.Matrix;

public class Plane {

    private Vector3D a, b, c;
    
    public Plane(Vector3D a, Vector3D b, Vector3D c){
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public Vector3D normalVector(){
        Vector3D s = b.minus(a);
        Vector3D r = c.minus(a);
        
        return s.cartesianProduct(r);
    }
    
    public Vector3D positionVector(){
        return a;
    }
    
    public Matrix planeVector(){
        Vector3D n = this.normalVector();
        double w = n.scalarProduct(a);
        double[][] e = {{n.x}, {n.y}, {n.z}, {-w}};
        return new Matrix(e);
    }
    
    public Vector3D getS(){
        return b.minus(a);
    }
    
    public Vector3D getR(){
        return c.minus(a);
    }
}
