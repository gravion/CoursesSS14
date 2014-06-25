package u08;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Jama.Matrix;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{

    public MainFrame(Scene s){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        
        this.setContentPane(new TexturePanel(s));
        
        // Display the window.
        this.setVisible(true);
    }
    
    public static void main(String[] args){
        Scene s = a24();
//        s.printMatrices();
        
//        double[][] point = {{-1.0d},{-1.0d},{-1.0d},{-1.0d}};
//        Matrix p = new Matrix(point);
//        s.getCompleteTransformation(0).print(4, 20);
//        Matrix p_strich = s.getCompleteTransformation(0).times(p);
//        p_strich.timesEquals(1/p_strich.get(3, 0));
//        p_strich.print(4, 20);
//        
//        int x = (int)(p_strich.get(0, 0) * 300) + 300;
//        int y = (int)(p_strich.get(1, 0) * 200) + 200;
//        
//        System.out.println(x);
//        System.out.println(y);
//        
//        System.out.println(s.colorInPoint(x, y));
        new MainFrame(s);
    }
    
    private static Scene a24(){
        Vector3D camera = new Vector3D(4,5,3);
        Vector3D cameraDirection = new Vector3D(3,0,1);
        double hFlareAngle = 30;
        double vFlareAngle = 20;
        double near = 3;
        double far = 20;
        ArrayList<Vector3D> vertices = new ArrayList<Vector3D>();
        vertices.add(new Vector3D(1,1,1));
        vertices.add(new Vector3D(1,1,-1));
        vertices.add(new Vector3D(1,-1,1));
        vertices.add(new Vector3D(1,-1,-1));
        vertices.add(new Vector3D(-1,1,1));
        vertices.add(new Vector3D(-1,1,-1));
        vertices.add(new Vector3D(-1,-1,1));
        vertices.add(new Vector3D(-1,-1,-1));
        
        ArrayList<ArrayList<Integer>> faces = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> f1 = new ArrayList<Integer>();
        f1.add(0);f1.add(1);f1.add(3);f1.add(2);
        ArrayList<Integer> f2 = new ArrayList<Integer>();
        f2.add(0);f2.add(1);f2.add(5);f2.add(4);
        ArrayList<Integer> f3 = new ArrayList<Integer>();
        f3.add(4);f3.add(5);f3.add(7);f3.add(6);
        ArrayList<Integer> f4 = new ArrayList<Integer>();
        f4.add(2);f4.add(3);f4.add(7);f4.add(6);
        ArrayList<Integer> f5 = new ArrayList<Integer>();
        f5.add(0);f5.add(2);f5.add(6);f5.add(4);
        ArrayList<Integer> f6 = new ArrayList<Integer>();
        f6.add(1);f6.add(3);f6.add(7);f6.add(5);
        faces.add(f1);faces.add(f2);faces.add(f3);faces.add(f4);faces.add(f5);faces.add(f6);

        ArrayList<Texture> textures = new ArrayList<Texture>();
        textures.add(new Texture("/home/gravion/test.jpeg"));
        textures.add(new Texture("/home/gravion/test.jpeg"));
        textures.add(new Texture("/home/gravion/test.jpeg"));
        textures.add(new Texture("/home/gravion/test.jpeg"));
        textures.add(new Texture("/home/gravion/test.jpeg"));
        textures.add(new Texture("/home/gravion/test.jpeg"));
        
        Field cube = new Field(vertices, faces, textures);
        
        Scene s = new Scene(camera, cameraDirection, hFlareAngle, vFlareAngle, near, far, cube);
        s.setMOW(0, new Matrix(StandardMatrices.linearScaling(0.2)),
                    new Matrix(StandardMatrices.zTurn(60)),
                    new Matrix(StandardMatrices.translate(9, 6, 5)));
        
        return s;
    }
    
    class TexturePanel extends JPanel{
        Scene s;
        /**
         * 
         * @param texturePath Pfade zu den Texturen
         * @param points Punkte der Flächen
         * @param faces Flächen mit Referenzen auf points
         */
        public TexturePanel(Scene s){
            this.s = s;
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for(int i = 0; i < this.getWidth(); i++){
                for(int j = 0; j < this.getHeight(); j++){
                    g.setColor(s.colorInPoint(i, j));
                    g.fillRect(i, j, 1, 1);
                }
            }
        }
    }
}
