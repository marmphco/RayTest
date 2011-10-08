public class PointLight implements Light
{

    Vector3 position;
    double intensity;
    Vector3 color;
    
	public PointLight(Vector3 _position, Vector3 _color, double _intensity)
    {
		
		position = _position;
		color = _color;
        intensity = _intensity;
		
	}
    
    public double intensityAtPoint(Vector3 point)
    {
        
        double dist = position.subtract(point).magnitude();
        return intensity/(dist*dist); //basic pure quadratic attentuation
        
    }
	
	public Vector3 colorIntensityAtPoint(Vector3 point)
    {
		
		return color.scale(intensityAtPoint(point)); //temp
		
	}
	
	public Vector3 directionAtPoint(Vector3 point)
    {
		
        return point.subtract(position).normalize();
		
	}

}