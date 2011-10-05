public class Triangle implements RenderObject
{
	/***variables***/
    Vector3 vertexA, vertexB, vertexC;
	Vector3 normal;
	double area, planeD;
    
	/***constructors***/
    public Triangle()
    {
        vertexA = vertexB = vertexC = normal = new Vector3();
		area = planeD = 0;
    }
    public Triangle(Vector3 _vertexA, Vector3 _vertexB, Vector3 _vertexC, boolean FlipNormal)
    {
        vertexA = _vertexA; vertexB = _vertexB; vertexC = _vertexC;
		
		Vector3 crossP = Vector3.cross(vertexB.subtract(vertexA), vertexC.subtract(vertexA));
		normal = crossP.normalize();
		area = crossP.magnitude();
		
		if (FlipNormal) {normal = normal.negative();}
		planeD = -normal.dot(vertexA);
    }
    
    public double intersectionDepth(Vector3 ray, Vector3 rayOrigin)
    {  
        return -(rayOrigin.dot(normal) + planeD) / ray.dot(normal);
		//return rayOrigin.add(ray.scale(d));
    }
    
    public Vector3 normalAtPoint(Vector3 point)
    {
        return normal;   
    }

}