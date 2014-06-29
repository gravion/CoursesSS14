package u08;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Jama.Matrix;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{
    static int x = 600;
    static int y = 400;
    
    public MainFrame(Scene s){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(x, y);
        
        this.setContentPane(new TexturePanel(s));
        
        // Display the window.
        this.setVisible(true);
    }
    
    public static void main(String[] args){
        Scene s = a24();
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
        f1.add(0);f1.add(1);f1.add(2);f1.add(3);
        ArrayList<Integer> f2 = new ArrayList<Integer>();
        f2.add(0);f2.add(1);f2.add(4);f2.add(5);
        ArrayList<Integer> f3 = new ArrayList<Integer>();
        f3.add(4);f3.add(5);f3.add(6);f3.add(7);
        ArrayList<Integer> f4 = new ArrayList<Integer>();
        f4.add(2);f4.add(3);f4.add(6);f4.add(7);
        ArrayList<Integer> f5 = new ArrayList<Integer>();
        f5.add(0);f5.add(2);f5.add(4);f5.add(6);
        ArrayList<Integer> f6 = new ArrayList<Integer>();
        f6.add(1);f6.add(3);f6.add(5);f6.add(7);
        faces.add(f1);faces.add(f2);faces.add(f3);faces.add(f4);faces.add(f5);faces.add(f6);

        ArrayList<Texture> textures = new ArrayList<Texture>();
        textures.add(new Texture("u08/u08/textures/Schachbrett.jpg"));
        textures.add(new Texture("u08/u08/textures/sechser.jpg"));
        textures.add(new Texture("u08/u08/textures/Schachbrett.jpg"));
        textures.add(new Texture("u08/u08/textures/sechser.jpg"));
        textures.add(new Texture("u08/u08/textures/Schachbrett.jpg"));
        textures.add(new Texture("u08/u08/textures/sechser.jpg"));
        
        Field cube = new Field(vertices, faces, textures);
        
        Scene s = new Scene(camera, cameraDirection, hFlareAngle, vFlareAngle, near, far, x, y, cube);
        s.setMOW(0, new Matrix(StandardMatrices.linearScaling(0.4)),
                    new Matrix(StandardMatrices.zTurn(80)),
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
