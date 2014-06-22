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
    
    public Scene(Vector3D camera, Vector3D cameraDirection, double hFlareAngle, double vFlareAngle, double near, double far, Field... fields){
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
        this.horizontalPixel = 600;
        this.verticalPixel = 400;
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
        Matrix s = null;
        double z = 0;
        int i_save = 0;
        int j_save = 0;
        for(int i = 0; i < this.fields.length; i++){
            for(int j = 0; j < this.fields[i].getFaces().size(); j++){
                Plane pl_temp = new Plane(this.fields[i].getVertices().get(this.fields[i].getFaces().get(j).get(0)),
                                    this.fields[i].getVertices().get(this.fields[i].getFaces().get(j).get(1)),
                                    this.fields[i].getVertices().get(this.fields[i].getFaces().get(j).get(2)));
                Matrix a = this.getCompleteTransformation(i).inverse().transpose().times(pl_temp.planeVector());
                // <a,Q>=0
                double[][] p_array = {{(double)x/this.horizontalPixel}, {(double)y/this.verticalPixel}, {0}, {1}}; 
                Matrix p = new Matrix(p_array);
                double[][] v_array = {{0},{1},{0},{1}};
                Matrix v = new Matrix(v_array);
                // t = -(a_x*p_x+a_y*p_y)/(a_y+a_w)
                if(a.get(2, 0) + a.get(3, 0) == 0){
                    continue; // TODO
                }
                double t = -(a.get(0, 0) * p.get(0, 0) + a.get(1, 0) * p.get(1, 0))/(a.get(2, 0) + a.get(3, 0));
                Matrix q_temp = p.plus(v.times(t));
                // TODO is this rly working?
                // should be
                pl = pl_temp;
                Matrix q = q_temp;
                Matrix q_strich = (this.getCompleteTransformation(i_save)).times(q);
                double[][] b_array = {  {pl.getR().x, pl.getS().x}, 
                                        {pl.getR().y, pl.getS().y}, 
                                        {pl.getR().z, pl.getS().z},
                                        {1, 1}};
                Matrix b = new Matrix(b_array);
                double[][] a_array = {{pl.positionVector().x}, {pl.positionVector().y}, {pl.positionVector().z}, {1}};
                s = b.qr().solve(q_strich.minus(new Matrix(a_array)));
                if(z < q.get(2, 0) && s.get(0, 0) >= 0 && s.get(0, 0) <= 1
                                                && s.get(1, 0) >= 0 && s.get(1, 0) <= 1){
                    z = q_temp.get(2, 0);
                    i_save = i;
                    j_save = j;
                    q = q_temp;
                }
            }
        }
        return this.fields[j_save].getTextures().get(i_save).colorAtPosition(s.get(0, 0), s.get(1, 0));
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
//        double[][] baseChange = {{u[0], u[1], u[2], 0},
//                            {v[0], v[1], v[2], 0},
//                            {minusN[0], minusN[1], minusN[2], 0},
//                            {0, 0, 0, 1}};
        
//        this.mWA = (new Matrix(baseChange)).times(new Matrix(StandardMatrices.translate(-camera.x, -camera.y, -camera.z)));
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
    
    private Matrix getCompleteTransformation(int field){
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
