import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class RayTracer implements Renderer
{

    public BufferedImage buffer; //only public for simple access as of now
    
    private int bufferWidth;
    private int bufferHeight;
    
    //following should be consolidated into a scene object
    
    private double viewportHeight;
    private double viewportWidth;
    private double aspectRatio;
    
    private ArrayList<RenderObject> renderObjects;
    private ArrayList<Light> lights;
    
    private Vector3 camera;
    
    ///////////////////////////////////////////////////////
    
    public RayTracer(int _bufferWidth, int _bufferHeight, double _viewportHeight)
    {
        
        bufferWidth = _bufferWidth;
        bufferHeight = _bufferHeight;
        buffer = new BufferedImage(bufferWidth, bufferHeight, BufferedImage.TYPE_INT_ARGB);
        
        aspectRatio = (double)bufferWidth/(double)bufferHeight;
        viewportHeight = _viewportHeight;
        viewportWidth = viewportHeight*aspectRatio;
        
    }
    
    public void renderImage()
    {
        
        double[][] depthBuffer = new double[bufferWidth][bufferHeight];
        
		for (int x = 0; x < bufferWidth; x++)
		{
			for (int y = 0; y < bufferHeight; y++)
			{
				double xPos = (double)(x - bufferWidth/2) * viewportWidth / (double)(bufferWidth/2);
				double yPos = (double)(y - bufferHeight/2) * viewportHeight / (double)(bufferHeight/2);
				Vector3 cameradir = new Vector3(xPos, yPos, 0).subtract(camera).normalize();
				depthBuffer[x][y] = Double.MAX_VALUE;
				
				for(RenderObject object : renderObjects)
				{
					double depth = object.intersectionDepth(cameradir, camera);
					if (depth > 0) {depthBuffer[x][y] = Math.min(depthBuffer[x][y], depth);}
				}
			}
		}
		
		for (int x = 0; x < bufferWidth; x++)
		{
			for (int y = 0; y < bufferHeight; y++)
			{
				double xPos = (double)(x - bufferWidth/2) * viewportWidth / (double)(bufferWidth/2);
				double yPos = (double)(y - bufferHeight/2) * viewportHeight / (double)(bufferHeight/2);
				Vector3 cameradir = new Vector3(xPos, yPos, 0).subtract(camera).normalize();
				
				Vector3 color = new Vector3();
				
				for(RenderObject object : renderObjects)
				{
					
					double depth = object.intersectionDepth(cameradir, camera);
					Vector3 intersection = camera.add(cameradir.scale(depth));
                    
					if(depth <= 0) continue;
                    if(depth > depthBuffer[x][y] + 0.001) continue;
                    
                    for(Light light : lights)
                    {
                        //double attenuation = 4.0 / (1.0 + 0.1 * distance + 0.05 * distance * distance); //attenuators should be placed in lights
                        Vector3 lightDirection = light.directionAtPoint(intersection).negative().normalize(); //points away from surface
                        Vector3 cameraDirection = camera.subtract(intersection).normalize();
                        Vector3 halfVector = lightDirection.add(cameraDirection).normalize();
                        Vector3 normal = object.normalAtPoint(intersection);
                        
                        double dvalue = Math.max(0.0, lightDirection.dot(normal));
                        Vector3 diffuseColor = light.colorIntensityAtPoint(intersection).scale(dvalue * 0.5);
                        
                        double svalue = Math.pow(Math.max(0.0, halfVector.dot(normal)), 50.0);
                        Vector3 specularColor = light.colorIntensityAtPoint(intersection).scale(svalue);
                        
                        color = color.add(diffuseColor.add(specularColor).scale(255));
                    }
                    
                    color.x = Math.max(0, Math.min(255, color.x));
                    color.y = Math.max(0, Math.min(255, color.y));
                    color.z = Math.max(0, Math.min(255, color.z));
                    
				}
				
				buffer.setRGB(x, y, (255 << 24) | ((int)color.x << 16) | ((int)color.y << 8) | ((int)color.z << 0));
			}
		}
        
    }
    
    public void setRenderObjects(ArrayList<RenderObject> _renderObjects)
    {
        
        renderObjects = _renderObjects;
        
    }
    
    public void setLights(ArrayList<Light> _lights)
    {
        
        lights = _lights;
        
    }
    
    public void setCamera(Vector3 _camera)
    {
        
        camera = _camera;
        
    }

}