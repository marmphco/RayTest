public interface RenderObject
{

    public double intersectionDepth(Vector3 ray, Vector3 rayOrigin);
    public Vector3 normalAtPoint(Vector3 point);
   // public Vector3 colorAtPoint(); materialAtPoint(); ?

}