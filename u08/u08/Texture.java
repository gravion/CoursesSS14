package u08;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {
    BufferedImage texture;
    
    public Texture(String path){
        File f = new File(path);
        try {
            texture = ImageIO.read(f);
        } catch (IOException e) { ; }
    }
    
    public Color colorAtPosition(double alpha, double beta){
        System.out.println("alpha: " + alpha + " beta: " + beta);
        System.out.println("w: " + (int)(texture.getWidth() * alpha) + " h: " + (int)(texture.getHeight() * beta));
        if(alpha < 0 || beta < 0){
            return null;
        }
        return new Color(texture.getRGB((int)(texture.getWidth() * alpha), (int)(texture.getHeight() * beta)));
    }
}
