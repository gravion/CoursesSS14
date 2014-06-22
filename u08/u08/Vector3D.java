package u08;

public class Vector3D {
    public double x;
    public double y;
    public double z;
    
    public Vector3D(){
        this.x = 0.0d;
        this.y = 0.0d;
        this.z = 0.0d;
    }

    public Vector3D(double[] points){
        if(points.length != 3){
            this.x = 0.0d;
            this.y = 0.0d;
            this.z = 0.0d;
        }
        this.x = points[0];
        this.y = points[1];
        this.z = points[2];
    }

    public Vector3D(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Vector3D minus(Vector3D a){
        return new Vector3D(this.x - a.x, this.y - a.y, this.z - a.z);
    }
    
    public double scalarProduct(Vector3D v){
        return this.x * v.x + this.y + v.y + this.z + v.z;
    }
    
    public Vector3D cartesianProduct(Vector3D v){
        return new Vector3D(    this.y*v.z - this.z*v.y,
                                this.z*v.x - this.x*v.z,
                                this.x*v.y - this.y*v.x);
    }
    
    public Vector3D normalise(){
        double length = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        return new Vector3D(this.x / length, this.y / length, this.z / length);
    }
    
    public double[] toArray(){
        double[] res = {this.x, this.y, this.z};
        return res;
    }
}
