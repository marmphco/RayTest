import java.util.ArrayList;

public interface Renderer {

    public void renderImage();
    
    //should be consolidated into a setScene()
    public void setRenderObjects(ArrayList<RenderObject> _renderObjects);
    public void setLights(ArrayList<Light> _lights);
    public void setCamera(Vector3 _camera);
    /////////////////////////////////////////

}