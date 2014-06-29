package u07;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
    ArithmeticFunctions f;

    public MainFrame(){
        f = new ArithmeticFunctions();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 750);
        this.setLayout(new GridLayout(5,4));
        

        double[] light = {5.0d, 5.0d, 5.0d};

        double[] I_a = {0.9d, 0.9d, 0.9d}; // Intensität des Umgebungslichts
        double[] k_ambient = {0.5d, 0.5d, 0.0d}; // Materialkonstante
     
        double[] I_in = {0.33d, 0.33d, 0.33d}; // Intensität der Lichtquelle
        double[] k_diffus = {0.6d, 0.6d, 0.6d}; // Materialkonstane
        
        double[] k_specular = {0.1d, 0.1d, 0.1d}; // Materialkonstante
        
        double n = 64.0d; // Oberflächenbeschaffenheit
        this.getContentPane().add(new SpherePanel(light, I_a, k_ambient, I_in, k_diffus, k_specular, n));
        n = 1.0d; // Oberflächenbeschaffenheit
        this.getContentPane().add(new SpherePanel(light, I_a, k_ambient, I_in, k_diffus, k_specular, n));
        
        n = 32.0d; // Oberflächenbeschaffenheit
        this.getContentPane().add(new SpherePanel(light, I_a, k_ambient, I_in, k_diffus, k_specular, n));
        n = 128.0d; // Oberflächenbeschaffenheit
        this.getContentPane().add(new SpherePanel(light, I_a, k_ambient, I_in, k_diffus, k_specular, n));
        n = 64.0d;

        
        double[] k_diffus2 = {0.3d, 0.3d, 0.3d}; // Materialkonstane
        this.getContentPane().add(new SpherePanel(light, I_a, k_ambient, I_in, k_diffus2, k_specular, n));
        double[] k_diffus3 = {0.9d, 0.9d, 0.9d}; // Materialkonstane
        this.getContentPane().add(new SpherePanel(light, I_a, k_ambient, I_in, k_diffus3, k_specular, n));

        double[] k_specular2 = {0.0d, 0.0d, 0.0d}; // Materialkonstante
        this.getContentPane().add(new SpherePanel(light, I_a, k_ambient, I_in, k_diffus, k_specular2, n));
        double[] k_specular3 = {0.4d, 0.4d, 0.4d}; // Materialkonstante
        this.getContentPane().add(new SpherePanel(light, I_a, k_ambient, I_in, k_diffus, k_specular3, n));
        
        
        double[] k_ambient2 = {0.8d, 0.8d, 0.0d}; // Materialkonstante
        this.getContentPane().add(new SpherePanel(light, I_a, k_ambient2, I_in, k_diffus, k_specular, n));        
        double[] k_ambient3 = {0.2d, 0.2d, 0.0d}; // Materialkonstante
        this.getContentPane().add(new SpherePanel(light, I_a, k_ambient3, I_in, k_diffus, k_specular, n));

        double[] I_a2 = {0.6d, 0.6d, 0.6d}; // Intensität des Umgebungslichts
        this.getContentPane().add(new SpherePanel(light, I_a2, k_ambient, I_in, k_diffus, k_specular, n));
        double[] I_a3 = {0.3d, 0.3d, 0.3d}; // Intensität des Umgebungslichts
        this.getContentPane().add(new SpherePanel(light, I_a3, k_ambient, I_in, k_diffus, k_specular, n));
        

        double[] I_in2 = {0.66d, 0.66d, 0.66d}; // Intensität der Lichtquelle
        this.getContentPane().add(new SpherePanel(light, I_a, k_ambient, I_in2, k_diffus, k_specular, n));
        double[] I_in3 = {0.166d, 0.166d, 0.166d}; // Intensität der Lichtquelle
        this.getContentPane().add(new SpherePanel(light, I_a, k_ambient, I_in3, k_diffus, k_specular, n));

        double[] light2 = {0.0d, 5.0d, 5.0d};
        this.getContentPane().add(new SpherePanel(light2, I_a, k_ambient, I_in, k_diffus, k_specular, n));
        double[] light3 = {5.0d, 0.0d, 5.0d};
        this.getContentPane().add(new SpherePanel(light3, I_a, k_ambient, I_in, k_diffus, k_specular, n));

        double[] k_ambient4 = {0.25d, 0.2d, 0.6d}; // Materialkonstante
        double[] light4 = {-5.0d, -5.0d, 2.0d};
        this.getContentPane().add(new SpherePanel(light4, I_a, k_ambient4, I_in, k_diffus2, k_specular, n));
        this.getContentPane().add(new SpherePanel(light, I_a, k_ambient4, I_in, k_diffus3, k_specular, n));
        
        double[] k_ambient5 = {0.6d, 0.2d, 0.25d}; // Materialkonstante
        this.getContentPane().add(new SpherePanel(light, I_a2, k_ambient5, I_in2, k_diffus3, k_specular, 32.0d));
        double[] light5 = {0.0d, 0.0d, -5.0d};
        this.getContentPane().add(new SpherePanel(light5, I_a, k_ambient5, I_in, k_diffus, k_specular, n));
        // Display the window.
        this.setVisible(true);
    }

    
    public static void main(String[] args){
        new MainFrame();
    }
    
    class SpherePanel extends JPanel{
        private PhongIllumination pg;
        private double[] V = {0.0d, 0.0d, 1.0d};
        
        private double[] light = {0.0d, 0.0d, 5.0d};

        private double[] I_a = {0.9d, 0.9d, 0.9d}; // Intensität des Umgebungslichts
        private double[] k_ambient = {0.5d, 0.5d, 0.0d}; // Materialkonstante
     
        private double[] I_in = {0.33d, 0.33d, 0.33d}; // Intensität der Lichtquelle
        private double[] k_diffus = {0.6d, 0.6d, 0.6d}; // Materialkonstane
        
        private double[] k_specular = {0.1d, 0.1d, 0.1d}; // Materialkonstante
        private double n; // Oberflächenbeschaffenheit
        public SpherePanel(double[] light, double[] I_a, double[] k_ambient,
                double[] I_in, double[] k_diffus, double[] k_specular, double n){
            super();
            pg = new PhongIllumination();
            
            this.light = light;
            
            this.I_a = I_a;
            this.k_ambient = k_ambient;
            
            this.I_in = I_in;
            this.k_diffus = k_diffus;
            
            this.k_specular = k_specular;
            this.n = n;
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for(int i = 0; i < this.getWidth(); i++){
                for(int j = 0; j < this.getHeight(); j++){
                    double x = i/(((double)this.getWidth()-1)/2) - 1;
                    double y = j/(((double)this.getHeight()-1)/2) - 1;
                    Color c = pg.calculateColorInPoint(I_a, k_ambient, I_in, k_diffus, f.calculateNormalvector(x, y), f.calculateL(x, y, light), k_specular, f.calculateReflectiondirection(x, y, light), V, n);
                    g.setColor(c);
                    g.fillRect(i, j, 1, 1);
                }
            }
        }
    }
}
