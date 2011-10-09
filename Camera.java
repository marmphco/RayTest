public class Camera
{

    public double viewportHeight;
    public double viewportWidth;
    
    public Vector3 position;
    
    public Camera(Vector3 _position, double _viewportWidth, double _viewportHeight) {
        
        position = _position;
        viewportHeight = _viewportHeight;
        viewportWidth = _viewportWidth;
        
    }

}