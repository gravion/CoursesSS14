package myu07;

import java.awt.Color;

public class PhongIllumination {
    private double I_a; // Intensität des Umgebungslichts
    private double k_ambient; // Materialkonstante
    
    private double I_in; // Intensität der Lichtquelle
    private double k_diffus; // Materialkonstane
    private double[] N; // Normalenvektor
    private double[] L; // Richtungsvektor der Lichtquelle 
    
    private double k_specular; // Materialkonstante
    private double[] R; // ideale Reflexionsrichtung
    private double[] V; // Blickrichtung des Betrachters 
    private double n; // Oberflächenbeschaffenheit
    
    public PhongIllumination(){
        
    }
    
    public Color calculateColorInPoint(double[] I_a, double[] k_ambient, double[] I_in, double[] k_diffuse,
            double[] N, double[] L, double[] k_specular, double[] R, double[] V, double n){
        if(I_a.length != 3 || k_ambient.length != 3 || I_in.length != 3 || k_diffuse.length != 3|| N == null || N.length != 3
                || L.length != 3 || k_specular.length != 3 || R.length != 3 || V.length != 3){
            return Color.BLACK;
        }else{
            double[] I_rgb = new double[3];
            for(int i = 0; i < 3; i++){
                I_rgb[i] = this.calculateColorInPoint(I_a[i], k_ambient[i], I_in[i], k_diffuse[i], N, L, k_specular[i], R, V, n);
                if(I_rgb[i] > 1){
                    I_rgb[i] = 1;
                }else if(I_rgb[i] < 0){
                    I_rgb[i] = 0;
                }
            }
            return new Color((float)I_rgb[0], (float)I_rgb[1], (float)I_rgb[2]);
        }
    }
    
    private double calculateColorInPoint(double I_a, double k_ambient, double I_in, double k_diffuse,
            double[] N, double[] L, double k_specular, double[] R, double[] V, double n){
        this.setMaterialConstants(k_ambient, k_diffuse, k_specular, n);
        this.setLightIntensitis(I_a, I_in);
        return this.calculateColorInPoint(N, L, R, V);
    }
 
    private double calculateColorInPoint(double[] N, double[] L, double[] R, double[] V){
        this.N = this.normalise(N);
        this.L = this.normalise(L);
        this.R = this.normalise(R);
        this.V = this.normalise(V);
        //return this.calculateAmbientComponent();
        //return this.calculateDiffuseComponent();
        //return this.calculateReflectingComponent();
        double aC = this.calculateAmbientComponent();
        double dC = this.calculateDiffuseComponent();
        double rC = this.calculateReflectingComponent();
        if(dC > 0){
            if(rC > 0){
                return aC + dC + rC;
            }else{
                return aC + dC;
            }
        }else{
            return aC;
        }
    }
    
    public void setMaterialConstants(double k_ambient, double k_diffuse, double k_specular, double n){
        this.k_ambient = k_ambient;
        this.k_diffus = k_diffuse;
        this.k_specular = k_specular;
        this.n = n;
    }
    
    public void setLightIntensitis(double I_a, double I_in){
        this.I_a = I_a;
        this.I_in = I_in;
    }
    
    private double calculateAmbientComponent(){
        return I_a * k_ambient;
    }
    
    private double calculateDiffuseComponent(){
        return I_in * k_diffus * (scalarproduct(L, N));
    }
    
    private double calculateReflectingComponent(){
        return I_in * k_specular * ((n+2)/(2*Math.PI)) * Math.pow(scalarproduct(R, V), n); 
    }
    
    private double scalarproduct(double[] a, double[] b){
        double result = 0.0d;
        if(a.length != b.length){
            throw new ArithmeticException("Vectors a and b do not have the same size while trying to calculate the scalarproduct.");
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
