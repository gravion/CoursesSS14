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
        return new Color(texture.getRGB((int)(texture.getWidth() * alpha), (int)(texture.getHeight() * beta)));
    }
}