public interface Light {
	
    public Vector3 colorAtPoint(Vector3 point);
	public double intensityAtPoint(Vector3 point);
	public Vector3 directionAtPoint(Vector3 point);
	//public Vector3 color(); ?no color yet?

}