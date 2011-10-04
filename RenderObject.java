public interface RenderObject
{

    public Vector3 intersectionWithRay(Vector3 ray, Vector3 rayOrigin);
    public Vector3 normalAtPoint(Vector3 point);
   // public Vector3 colorAtPoint(); materialAtPoint(); ?

}