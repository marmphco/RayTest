public class Sphere implements RenderObject
{
//another slight test
    Vector3 position;
    double radius;
    
    public Sphere()
    {
        position = new Vector3();
        radius = 0;
    }
    
    public Sphere(Vector3 _position, double _radius)
    {
        position = _position;
        radius = _radius;
    }
    
    public double intersectionDepth(Vector3 ray, Vector3 rayOrigin)
    {
     
        Vector3 dist = position.subtract(rayOrigin);
        double dot = Vector3.dot(ray, dist);
		double determinant = dot*dot-dist.dot(dist)+radius*radius;
		
		if (determinant <= 0) {return 0;}
        else {return dot-Math.sqrt(determinant);}
        
    }
    
    public Vector3 normalAtPoint(Vector3 point) //assuming point is on sphere. should there be error checks here, or outside this?
    {
        
        return point.subtract(position).normalize();
        
    }

}