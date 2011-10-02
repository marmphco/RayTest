public class Vector2
{
    /***components***/
    public double x, y;
    
    /***constructors***/
    public Vector2()
    {
        x = y = 0;
    }
    public Vector2(double _scalar)
    {
        x = y = _scalar;
    }
    public Vector2(double _xComponent, double _yComponent)
    {
        x = _xComponent; y = _yComponent;
    }
    public Vector2(Vector3 _vector)
    {
        x = _vector.x; y = _vector.y;
    }
    
    /***swap function***/
    public Vector2 swap()
    {
        return new Vector2(y, x);
    }
    public static Vector2 swap(Vector2 _vector)
    {
        return new Vector2(_vector.y, _vector.x);
    }
    
    /***add function***/
    public Vector2 add(Vector2 _vector)
    {
        double xComponent = x + _vector.x;
        double yComponent = y + _vector.y;
        return new Vector2(xComponent, yComponent);
    }
    public static Vector2 add(Vector2 _vectorA, Vector2 _vectorB)
    {
        double xComponent = _vectorA.x + _vectorB.x;
        double yComponent = _vectorA.y + _vectorB.y;
        return new Vector2(xComponent, yComponent);
    }
    
    /***subtract function***/
    public Vector2 subtract(Vector2 _vector)
    {
        double xComponent = x - _vector.x;
        double yComponent = y - _vector.y;
        return new Vector2(xComponent, yComponent);
    }
    public static Vector2 subtract(Vector2 _vectorA, Vector2 _vectorB)
    {
        double xComponent = _vectorA.x - _vectorB.x;
        double yComponent = _vectorA.y - _vectorB.y;
        return new Vector2(xComponent, yComponent);
    }
    
    /***negative function***/
    public Vector2 negative()
    {
        return new Vector2(-x, -y);
    }
    public static Vector2 negative(Vector2 _vector)
    {
        return new Vector2(-_vector.x, -_vector.y);
    }
    
    /***scale function***/
    public Vector2 scale(double _scalar)
    {
        double xComponent = x * _scalar;
        double yComponent = y * _scalar;
        return new Vector2(xComponent, yComponent);
    }
    public static Vector2 scale(Vector2 _vector, double _scalar)
    {
        double xComponent = _vector.x * _scalar;
        double yComponent = _vector.y * _scalar;
        return new Vector2(xComponent, yComponent);
    }
    public static Vector2 scale(double _scalar, Vector2 _vector)
    {
        double xComponent = _scalar * _vector.x;
        double yComponent = _scalar * _vector.y;
        return new Vector2(xComponent, yComponent);
    }
    
    /***multiply function***/
    public Vector2 multiply(Vector2 _vector)
    {
        double xComponent = x * _vector.x;
        double yComponent = y * _vector.y;
        return new Vector2(xComponent, yComponent);
    }
    public static Vector2 multiply(Vector2 _vectorA, Vector2 _vectorB)
    {
        double xComponent = _vectorA.x * _vectorB.x;
        double yComponent = _vectorA.y * _vectorB.y;
        return new Vector2(xComponent, yComponent);
    }
    
    /***divide function***/
    public Vector2 divide(Vector2 _vector)
    {
        double xComponent = x / _vector.x;
        double yComponent = y / _vector.y;
        return new Vector2(xComponent, yComponent);
    }
    public static Vector2 divide(Vector2 _vectorA, Vector2 _vectorB)
    {
        double xComponent = _vectorA.x / _vectorB.x;
        double yComponent = _vectorA.y / _vectorB.y;
        return new Vector2(xComponent, yComponent);
    }
    
    /***inverse function***/
    public Vector2 inverse()
    {
        return new Vector2(1.0 / x, 1.0 / y);
    }
    public static Vector2 inverse(Vector2 _vector)
    {
        return new Vector2(1.0 / _vector.x, 1.0 / _vector.y);
    }
    
    /***dot function***/
    public double dot(Vector2 _vector)
    {
        return x * _vector.x + y * _vector.y;
    }
    public static double dot(Vector2 _vectorA, Vector2 _vectorB)
    {
        return _vectorA.x * _vectorB.x + _vectorA.y * _vectorB.y;
    }
    
    /***magnitude function***/
    public double magnitude()
    {
        return Math.sqrt(x * x + y * y);
    }
    public static double magnitude(Vector2 _vector)
    {
        return Math.sqrt(_vector.x * _vector.x + _vector.y * _vector.y);
    }
    
    /***normalize function***/
    public Vector2 normalize()
    {
        double inverseLength = 1.0 / magnitude();
        double xComponent = x * inverseLength;
        double yComponent = y * inverseLength;
        return new Vector2(xComponent, yComponent);
    }
    public Vector2 normalize(Vector2 _vector)
    {
        double inverseLength = 1.0 / magnitude(_vector);
        double xComponent = _vector.x * inverseLength;
        double yComponent = _vector.y * inverseLength;
        return new Vector2(xComponent, yComponent);
    }
    
    /***reflect function***/
    public Vector2 reflect(Vector2 _axis)
    {
        return subtract(scale(2 * (dot(_axis) / _axis.dot(_axis)), _axis));
    }
    public static Vector2 reflect(Vector2 _vector, Vector2 _axis)
    {
        return _vector.subtract(scale(2 * (_vector.dot(_axis) / _axis.dot(_axis)), _axis));
    }
}