public class Vector3
{
    /***components***/
    public double x, y, z;
    
    /***constructors***/
    public Vector3()
    {
        x = y = z = 0;
    }
    public Vector3(double _scalar)
    {
        x = y = z = _scalar;
    }
    public Vector3(double _xComponent, double _yComponent, double _zComponent)
    {
        x = _xComponent; y = _yComponent; z = _zComponent;
    }
    public Vector3(Vector2 _vector, double _scalar)
    {
        x = _vector.x; y = _vector.y; z = _scalar;
    }
    public Vector3(double _scalar, Vector2 _vector)
    {
        x = _scalar; y = _vector.x; z = _vector.y;
    }
    
    /***add function***/
    public Vector3 add(Vector3 _vector)
    {
        double xComponent = x + _vector.x;
        double yComponent = y + _vector.y;
        double zComponent = z + _vector.z;
        return new Vector3(xComponent, yComponent, zComponent);
    }
    public static Vector3 add(Vector3 _vectorA, Vector3 _vectorB)
    {
        double xComponent = _vectorA.x + _vectorB.x;
        double yComponent = _vectorA.y + _vectorB.y;
        double zComponent = _vectorA.z + _vectorB.z;
        return new Vector3(xComponent, yComponent, zComponent);
    }
    
    /***subtract function***/
    public Vector3 subtract(Vector3 _vector)
    {
        double xComponent = x - _vector.x;
        double yComponent = y - _vector.y;
        double zComponent = z - _vector.z;
        return new Vector3(xComponent, yComponent, zComponent);
    }
    public static Vector3 subtract(Vector3 _vectorA, Vector3 _vectorB)
    {
        double xComponent = _vectorA.x - _vectorB.x;
        double yComponent = _vectorA.y - _vectorB.y;
        double zComponent = _vectorA.z - _vectorB.z;
        return new Vector3(xComponent, yComponent, zComponent);
    }
    
    /***negative function***/
    public Vector3 negative()
    {
        return new Vector3(-x, -y, -z);
    }
    public static Vector3 negative(Vector3 _vector)
    {
        return new Vector3(-_vector.x, -_vector.y, -_vector.z);
    }
    
    /***scale function***/
    public Vector3 scale(double _scalar)
    {
        double xComponent = x * _scalar;
        double yComponent = y * _scalar;
        double zComponent = z * _scalar;
        return new Vector3(xComponent, yComponent, zComponent);
    }
    public static Vector3 scale(Vector3 _vector, double _scalar)
    {
        double xComponent = _vector.x * _scalar;
        double yComponent = _vector.y * _scalar;
        double zComponent = _vector.z * _scalar;
        return new Vector3(xComponent, yComponent, zComponent);
    }
    public static Vector3 scale(double _scalar, Vector3 _vector)
    {
        double xComponent = _scalar * _vector.x;
        double yComponent = _scalar * _vector.y;
        double zComponent = _scalar * _vector.z;
        return new Vector3(xComponent, yComponent, zComponent);
    }
    
    /***multiply function***/
    public Vector3 multiply(Vector3 _vector)
    {
        double xComponent = x * _vector.x;
        double yComponent = y * _vector.y;
        double zComponent = z * _vector.z;
        return new Vector3(xComponent, yComponent, zComponent);
    }
    public static Vector3 multiply(Vector3 _vectorA, Vector3 _vectorB)
    {
        double xComponent = _vectorA.x * _vectorB.x;
        double yComponent = _vectorA.y * _vectorB.y;
        double zComponent = _vectorA.z * _vectorB.z;
        return new Vector3(xComponent, yComponent, zComponent);
    }
    
    /***divide function***/
    public Vector3 divide(Vector3 _vector)
    {
        double xComponent = x / _vector.x;
        double yComponent = y / _vector.y;
        double zComponent = z / _vector.z;
        return new Vector3(xComponent, yComponent, zComponent);
    }
    public static Vector3 divide(Vector3 _vectorA, Vector3 _vectorB)
    {
        double xComponent = _vectorA.x / _vectorB.x;
        double yComponent = _vectorA.y / _vectorB.y;
        double zComponent = _vectorA.z / _vectorB.z;
        return new Vector3(xComponent, yComponent, zComponent);
    }
    
    /***inverse function***/
    public Vector3 inverse()
    {
        return new Vector3(1.0 / x, 1.0 / y, 1.0 / z);
    }
    public static Vector3 inverse(Vector3 _vector)
    {
        return new Vector3(1.0 / _vector.x, 1.0 / _vector.y, 1.0 / _vector.z);
    }
    
    /***dot function***/
    public double dot(Vector3 _vector)
    {
        return x * _vector.x + y * _vector.y + z * _vector.z;
    }
    public static double dot(Vector3 _vectorA, Vector3 _vectorB)
    {
        return _vectorA.x * _vectorB.x + _vectorA.y * _vectorB.y + _vectorA.z * _vectorB.z;
    }
    
    /***cross function***/
    public Vector3 cross(Vector3 _vector)
    {
        double xComponent = y * _vector.z - z * _vector.y;
        double yComponent = z * _vector.x - x * _vector.z;
        double zComponent = x * _vector.y - y * _vector.x;
        return new Vector3(xComponent, yComponent, zComponent);
    }
    public static Vector3 cross(Vector3 _vectorA, Vector3 _vectorB)
    {
        double xComponent = _vectorA.y * _vectorB.z - _vectorA.z * _vectorB.y;
        double yComponent = _vectorA.z * _vectorB.x - _vectorA.x * _vectorB.z;
        double zComponent = _vectorA.x * _vectorB.y - _vectorA.y * _vectorB.x;
        return new Vector3(xComponent, yComponent, zComponent);
    }
    
    /***magnitude function***/
    public double magnitude()
    {
        return Math.sqrt(x * x + y * y + z * z);
    }
    public static double magnitude(Vector3 _vector)
    {
        return Math.sqrt(_vector.x * _vector.x + _vector.y * _vector.y + _vector.z * _vector.z);
    }
    
    /***normalize function***/
    public Vector3 normalize()
    {
        double inverseLength = 1.0 / magnitude();
        double xComponent = x * inverseLength;
        double yComponent = y * inverseLength;
        double zComponent = z * inverseLength;
        return new Vector3(xComponent, yComponent, zComponent);
    }
    public static Vector3 normalize(Vector3 _vector)
    {
        double inverseLength = 1.0 / magnitude(_vector);
        double xComponent = _vector.x * inverseLength;
        double yComponent = _vector.y * inverseLength;
        double zComponent = _vector.z * inverseLength;
        return new Vector3(xComponent, yComponent, zComponent);
    }
	
	/***normalize fast function***/
    public Vector3 normalizeFast()
    {
        double magSquared = dot(new Vector3(x, y, z));
        
        long bits = Double.doubleToLongBits(magSquared);
        bits = (((long)0x5fe6ec85 << 32) + 0xe7de30da - (bits >> 1));
        double result = Double.longBitsToDouble(bits);
        
        double inverseLength = result * (1.5 - (0.5 * magSquared * result * result));
        double xComponent = x * inverseLength;
        double yComponent = y * inverseLength;
        double zComponent = z * inverseLength;
        return new Vector3(xComponent, yComponent, zComponent);
    }
    public static Vector3 normalizeFast(Vector3 _vector)
    {
        double magSquared = _vector.dot(_vector);
        
        long bits = Double.doubleToLongBits(magSquared);
        bits = (((long)0x5fe6ec85 << 32) + 0xe7de30da - (bits >> 1));
        double result = Double.longBitsToDouble(bits);
        
        double inverseLength = result * (1.5 - (0.5 * magSquared * result * result));
        double xComponent = _vector.x * inverseLength;
        double yComponent = _vector.y * inverseLength;
        double zComponent = _vector.z * inverseLength;
        return new Vector3(xComponent, yComponent, zComponent);
    }
    
    public Vector3 reflect(Vector3 _axis)
    {
        return subtract(scale(2 * (dot(_axis) / _axis.dot(_axis)), _axis));
    }
    public static Vector3 reflect(Vector3 _vector, Vector3 _axis)
    {
        return _vector.subtract(scale(2 * (_vector.dot(_axis) / _axis.dot(_axis)), _axis));
    }
}