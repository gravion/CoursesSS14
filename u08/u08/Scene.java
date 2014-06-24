package u08;

import java.awt.Color;

import Jama.*;

public class Scene {
    private Vector3D camera;
    private Vector3D cameraDirection; // -n
    private Field[] fields;
    private Matrix[] mOWs;
    private Matrix mWA;
    private Matrix mANDC;
    private double hFlareAngle, vFlareAngle;
    private double near, far;
    private int verticalPixel, horizontalPixel;
    
    public Scene(Vector3D camera, Vector3D cameraDirection, double hFlareAngle, double vFlareAngle,
                    double near, double far, int horizontalPixel, int verticalPixel, Field... fields){
        this.mOWs = new Matrix[fields.length];
        this.mWA = new Matrix(4,4);
        this.mANDC = new Matrix(4,4);
        this.camera = camera;
        this.setCameraDirection(cameraDirection);
        this.fields = fields;
        if(!this.setCameraProperties(near, far, hFlareAngle, vFlareAngle)){
            this.setCameraProperties();
        }
        this.calculateANDC();this.calculateMWA();
        this.horizontalPixel = horizontalPixel;
        this.verticalPixel = verticalPixel;
    }
    
    public boolean setMOW(int object, Matrix... matrices){
        if(object > mOWs.length -1){
            return false;
        }else{
            Matrix res;
            if(matrices.length == 1){
                res = matrices[0];
            }else if(matrices.length > 1){
                res = matrices[0];
                for(int i = 1; i < matrices.length; i++){
                    res = matrices[i].times(res);
                }
            }else{
                return false;
            }
            mOWs[object] = res;
        }
        return true;
    }
    
    public boolean setCamera(Vector3D camera){
        this.camera = camera;
        this.calculateMWA();
        return true;
    }
    
    public boolean setCameraDirection(Vector3D cameraDirection){
        this.cameraDirection = cameraDirection;
        this.calculateMWA();
        return true;
    }
    
    public boolean setCameraProperties(){
        return setCameraProperties(1, 10, 30, 20);
    }
    
    public boolean setCameraProperties(double near, double far, double hFlareAngle, double vFlareAngle){
        if(near < 0 || far <= 0 || hFlareAngle <= 0 || vFlareAngle <= 0
                || near > far){
            return false;
        }
        this.near = near;
        this.far = far;
        this.hFlareAngle = hFlareAngle;
        this.vFlareAngle = vFlareAngle;
        this.calculateANDC();
        return true;
    }
    
    public Color colorInPoint(int x, int y){
        Plane pl = null;
        Matrix s_save = null;
        Double z = null;
        int i_save = -1;
        int j_save = -1;
        for(int i = 0; i < this.fields.length; i++){
            i_save = -1;
            j_save = -1;
            for(int j = 0; j < this.fields[i].getFaces().size(); j++){
                // 1
                pl = new Plane(this.fields[i].getVertices().get(this.fields[i].getFaces().get(j).get(0)),
                                    this.fields[i].getVertices().get(this.fields[i].getFaces().get(j).get(1)),
                                    this.fields[i].getVertices().get(this.fields[i].getFaces().get(j).get(2)));
                Matrix a = this.getCompleteTransformation(i).inverse().transpose().times(pl.planeVector());
                // 2
                double[][] p_array = {{(double)(x-(this.horizontalPixel/2))/(this.horizontalPixel/2)}, {(double)((this.verticalPixel/2)-y)/(this.verticalPixel/2)}, {0}, {1}}; 
                Matrix p = new Matrix(p_array);
                double[][] v_array = {{0},{0},{1},{0}};
                Matrix v = new Matrix(v_array);
                // t = -(a_x*p_x+a_y*p_y+a_z*p_z+a_w*p_w)/(a_z)
                if(a.get(2, 0)  == 0){
                    continue;
                }
                double t = -(a.get(0, 0) * p.get(0, 0) + a.get(1, 0) * p.get(1, 0)
                                + a.get(2, 0) * p.get(2, 0) + a.get(3, 0) * p.get(3, 0))
                                /(a.get(2, 0));
                Matrix q = p.plus(v.times(t));
                
                Matrix q_strich = (this.getCompleteTransformation(i).inverse()).times(q);
                q_strich.timesEquals((double)1/q_strich.get(3, 0));
                
                double[][] q_strich_temp = {{q_strich.get(0, 0)},{q_strich.get(1, 0)},{q_strich.get(2, 0)}};
                q_strich = new Matrix(q_strich_temp);
                // 3
                double[][] b_array = {  {pl.getS().x, pl.getR().x}, 
                                        {pl.getS().y, pl.getR().y}, 
                                        {pl.getS().z, pl.getR().z}};
                Matrix b = new Matrix(b_array);
                double[][] a_array = {{pl.positionVector().x}, {pl.positionVector().y}, {pl.positionVector().z}};
                Matrix  s = b.solve(q_strich.minus(new Matrix(a_array)));
                if((z == null || z > q.get(2, 0)) && s.get(0, 0) >= 0 && s.get(0, 0) <= 1
                                                && s.get(1, 0) >= 0 && s.get(1, 0) <= 1){
                    z = q.get(2, 0);
                    i_save = i;
                    j_save = j;
                    s_save = s;
                }
            }
        }
        // 4
        if(i_save >= 0 && j_save >= 0){
            return this.fields[i_save].getTextures().get(j_save).colorAtPosition(s_save.get(0, 0), s_save.get(1, 0));
        }
        return Color.CYAN;
    }
    
    private void calculateMWA(){
        double[] temp = {0,0,1};
        
        double[] minusN = this.normalise(cameraDirection.toArray());
        double[] u = this.normalise(this.cartesianProduct3(minusN, temp));
        double[] v = this.normalise(this.cartesianProduct3(u, minusN));
        
        double[][] baseChange = {{u[0], v[0], -minusN[0], camera.x},
                {u[1], v[1], -minusN[1], camera.y},
                {u[2], v[2], -minusN[2], camera.z},
                {0, 0, 0, 1}};
        
        this.mWA = (new Matrix(baseChange)).inverse();
    }
    
    private void calculateANDC(){
        double r = this.near * Math.tan(this.hFlareAngle/360*Math.PI);
        double l = -r;
        double t = this.near * Math.tan(this.vFlareAngle/360*Math.PI);
        double b = -t;
        
        double[][] m = {{(2*this.near)/(r-l), 0, (r+l)/(r-l), 0},
                        {0, (2*this.near)/(t-b), (t+b)/(t-b), 0},
                        {0, 0, -(this.far+this.near)/(this.far-this.near), (-2*this.far*this.near)/(this.far - this.near)},
                        {0, 0, -1, 0}
                        };
        this.mANDC = new Matrix(m);
    }
    
    public Matrix getCompleteTransformation(int field){
        return this.mANDC.times(this.mWA.times(this.mOWs[field]));
    }
    
    private double[] cartesianProduct3(double[] v1, double[] v2){
        if(v1.length != v2.length && v1.length != 3){
            return null;
        }
        double[] res = {v1[1]*v2[2] - v1[2]*v2[1],
                        v1[2]*v2[0] - v1[0]*v2[2],
                        v1[0]*v2[1] - v1[1]*v2[0]};
        return res;
    }
    
    private double[] normalise(double[] vector){
        double[] res = new double[vector.length];
        double length = 0.0d;
        for(int i = 0; i < vector.length; i++){
            length += vector[i]*vector[i];
        }
        length = Math.sqrt(length);
        for(int i = 0; i < res.length; i++){
            res[i] = vector[i] / length;
        }
        return res;
    }
    
    public void printMatrices(){
        this.mOWs[0].print(4, 4);
        this.mWA.print(4,4);
        this.mANDC.print(4, 4);
        this.getCompleteTransformation(0).print(4, 4);
        
    }
}
