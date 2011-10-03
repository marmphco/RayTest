public class Sphere implements RenderObject
{

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
    
    public Vector3 intersectionWithRay(Vector3 ray) //assuming ray is normalized. should this be internally or externally?
    {
        
        double posLen = position.magnitude();
        double dot = Vector3.dot(ray, position);
        double d = dot-Math.sqrt(dot*dot-posLen*posLen+radius*radius); //shamelessly copied formula.
        
        return new Vector3(ray.x*d, ray.y*d, ray.z*d);
        
    }
    
    public Vector3 normalAtPoint(Vector3 point) //assuming point is on sphere. should there be error checks here, or outside this?
    {
        
        return point.subtract(position).normalize();//psuedo (point minus position).normalize();
        
    }

}