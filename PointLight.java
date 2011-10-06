public class PointLight implements Light {

    Vector3 position;
    double intensity;
    
	public PointLight(Vector3 _position, double _intensity) {
		
		position = _position;
        intensity = _intensity;
		
	}
    
    public double intensityAtPoint(Vector3 point) {
        
        double dist = position.subtract(point).magnitude();
        return intensity/(dist*dist); //basic pure quadratic attentuation
        
    }
	
	public Vector3 colorAtPoint(Vector3 point) {
		
		return new Vector3(1, 1, 1); //temp
		
	}
	
	public Vector3 directionAtPoint(Vector3 point) {
		
        return point.subtract(position).normalize();
		
	}

}