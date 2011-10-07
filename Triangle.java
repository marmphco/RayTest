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
    public Triangle(Vector3 _vertexA, Vector3 _vertexB, Vector3 _vertexC)
    {
        vertexA = _vertexA; vertexB = _vertexB; vertexC = _vertexC;
		
		Vector3 crossP = Vector3.cross(vertexB.subtract(vertexA), vertexC.subtract(vertexA));
		normal = crossP.normalize();
		area = crossP.magnitude() * 0.5;
		
		planeD = -normal.dot(vertexA);
    }
    
    public double intersectionDepth(Vector3 ray, Vector3 rayOrigin)
    {  
        double depth =  -(rayOrigin.dot(normal) + planeD) / ray.dot(normal);
		Vector3 projVec = rayOrigin.add(ray.scale(depth));
		
		if (ray.dot(normal) > 0) {normal = normal.negative();}
		
		double areaAB = Vector3.cross(projVec.subtract(vertexA), projVec.subtract(vertexB)).magnitude() * 0.5;
		double areaAC = Vector3.cross(projVec.subtract(vertexA), projVec.subtract(vertexC)).magnitude() * 0.5;
		double areaBC = Vector3.cross(projVec.subtract(vertexB), projVec.subtract(vertexC)).magnitude() * 0.5;
		
		if (areaAB + areaAC + areaBC > area + 0.01) {return 0;}
		else {return depth;}
    }
    
    public Vector3 normalAtPoint(Vector3 point)
    {
        return normal;   
    }

}