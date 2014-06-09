package myu07;

public class ArithmeticFunctions {
    // Kugelfunktion: x² + y² + z² = 1
    
    public double[] calculateNormalvector(double x, double y){
        // Gradiant f = (2x, 2y, 2z)
        double z = this.calculateZ(x, y);
        if(!Double.isNaN(z)){
            double[] result = {2*x,2*y,2*z};
            return this.normalise(result);
        }
        return null;
    }
    
    public double[] calculateReflectiondirection(double x, double y, double[] lightsource){
        double[] normalVector = this.calculateNormalvector(x, y);
        double[] L = this.calculateL(x, y, lightsource);

        if(normalVector != null && L != null){
            double[] result = new double[3];
            result = this.factorMultiplication(2, this.factorMultiplication(this.scalarproduct(L, normalVector),normalVector));
            for(int i = 0; i < 3; i++){
                result[i] = result[i] - L[i];
            }
            return this.normalise(result);
        }
        return null;
    }
    
    public double[] calculateL(double x, double y, double[] lightsource){
        double[] result = new double[3];
        double z = this.calculateZ(x, y);
        if(!Double.isNaN(z)){
            result[0] = lightsource[0] - x;
            result[1] = lightsource[1] - y;
            result[2] = lightsource[2] - z;
            return this.normalise(result);
        }
        return null;
    }
    
    private double calculateZ(double x, double y){
        return Math.sqrt(1 - (x * x) - (y * y));
    }
    
    private double[] factorMultiplication(double factor, double[] vector){
        double[] result = new double[vector.length];
        for(int i = 0; i < result.length; i++){
            result[i] = factor * vector[i];
        }
        return result;
    }
    
    private double scalarproduct(double[] a, double[] b){
        double result = 0.0d;
        if(a.length != b.length){
            throw new ArithmeticException("Vectors a and b do not have the same size file trying to calculate the scalarproduct.");
        }
        for(int i = 0; i < a.length; i++){
            result += a[i] * b[i];
        }
        return result;
    }
    
    private double[] normalise(double[] vector){
        double[] result = new double[vector.length];
        double length = 0.0d;
        for(int i = 0; i < vector.length; i++){
            length += vector[i]*vector[i];
        }
        length = Math.sqrt(length);
        for(int i = 0; i < result.length; i++){
            result[i] = vector[i] / length;
        }
        return result;
    }
}
