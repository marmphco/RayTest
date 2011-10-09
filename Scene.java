import java.util.ArrayList;

public class Scene 
{
    
    public ArrayList<RenderObject> renderObjects;
    public ArrayList<Light> lights;
    
    public Camera camera;
    
    public Scene(Camera _camera)
    {
        
        camera = _camera;
        
    }
    
    public void setRenderObjects(ArrayList<RenderObject> _renderObjects)
    {
        
        renderObjects = _renderObjects;
        
    }
    
    public void setLights(ArrayList<Light> _lights)
    {
        
        lights = _lights;
        
    }
    
    public void setCamera(Camera _camera)
    {
        
        camera = _camera;
        
    }

}