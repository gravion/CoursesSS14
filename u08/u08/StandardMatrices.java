package u08;

public class StandardMatrices {
    public static double[][] xTurn(double degree){
        double[][] ret = {  {1, 0, 0, 0},
                            {0, Math.cos(degree), -Math.sin(degree), 0},
                            {0, Math.sin(degree), Math.cos(degree), 0},
                            {0, 0, 0, 1}};
        return ret;
    }
    
    public static double[][] yTurn(double degree){
        double[][] ret = {  {Math.cos(degree), 0, Math.sin(degree), 0},
                            {0, 1, 0, 0},
                            {-Math.sin(degree), 0, Math.cos(degree), 0},
                            {0, 0, 0, 1}};
        return ret;
    }
    
    public static double[][] zTurn(double degree){
        double[][] ret = {  {Math.cos(degree), -Math.sin(degree), 0, 0},
                            {Math.sin(degree), Math.cos(degree), 0, 0},
                            {0, 0, 1, 0},
                            {0, 0, 0, 1}};
        return ret;
    }
    
    public static double[][] linearScaling(double scal){
        return scaling(scal, scal, scal);
    }
    
    public static double[][] scaling(double x, double y, double z){
        double[][] ret = {  {x, 0, 0, 0},
                            {0, y, 0, 0},
                            {0, 0, z, 0},
                            {0, 0, 0, 1}};
        return ret;
    }
    
    public static double[][] translate(double x, double y, double z){
        double[][] ret = {  {1, 0, 0, x},
                            {0, 1, 0, y},
                            {0, 0, 1, z},
                            {0, 0, 0, 1}};
        return ret;
    }
}
