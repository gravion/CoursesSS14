package u08;

import java.util.ArrayList;


// cube structure
public class Field {
    
    // Points of the cube 
    private ArrayList<Vector3D> vertices;
    // points for each surface
    private ArrayList<ArrayList<Integer>> faces;
    // textures
    private ArrayList<Texture> textures;
    
    /*------------------ constructors --------------------------------------*/
    public Field(){
        vertices = new ArrayList<Vector3D>();
        faces = new ArrayList<ArrayList<Integer>>();
        textures = new ArrayList<Texture>();
    }
    
    public Field(ArrayList<Vector3D> vertices, ArrayList<ArrayList<Integer>> faces, ArrayList<Texture> textures){
        this.setVertices(vertices);
        this.setFaces(faces);
        this.setTextures(textures);
    }
    
    /*------------------ methods --------------------------------------------*/
    private void checkPointsInPlane(ArrayList<Integer> face){
        Plane p = new Plane(vertices.get(face.get(0)), vertices.get(face.get(1)), vertices.get(face.get(2)));
        Vector3D normalvector = p.normalVector();
        normalvector = normalvector.normalise();
        for(int i = 3; i < face.size(); i++){
            if((vertices.get(face.get(i)).minus(p.positionVector())).scalarProduct(normalvector) != 0){
                // TODO fix planes
            }
        }
    }
    
    public void setVertices(ArrayList<Vector3D> vertices){
        this.vertices = vertices;
    }
    
    public void setFaces(ArrayList<ArrayList<Integer>> faces){
        for(int i = 0; i < faces.size(); i++){
            if(faces.get(i).size() < 3){
                return;
            }if(faces.get(i).size() == 3){
                continue;
            }else{
                this.checkPointsInPlane(faces.get(i));
            }
        }
        this.faces = faces;
    }
    
    public void setTextures(ArrayList<Texture> textures){
        this.textures = textures;
    }
    
    public ArrayList<ArrayList<Integer>> getFaces(){
        return this.faces;
    }
    
    public ArrayList<Vector3D> getVertices(){
        return this.vertices;
    }
    
    public ArrayList<Texture> getTextures(){
        return this.textures;
    }
}
