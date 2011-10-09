import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class RayTracer implements Renderer
{

    public BufferedImage buffer; //only public for simple access as of now
    
    private int bufferWidth;
    private int bufferHeight;
    
    private Scene scene;
    
    public RenderViewer renderViewer;
        
    public RayTracer(int _bufferWidth, int _bufferHeight, Scene _scene)
    {
        
        bufferWidth = _bufferWidth;
        bufferHeight = _bufferHeight;
        buffer = new BufferedImage(bufferWidth, bufferHeight, BufferedImage.TYPE_INT_ARGB);
        
        scene = _scene;
        
    }
    
    public void renderImage()
    {
        
        double[][] depthBuffer = new double[bufferWidth][bufferHeight];
        
		for (int x = 0; x < bufferWidth; x++)
		{
			for (int y = 0; y < bufferHeight; y++)
			{
				double xPos = (double)(x - bufferWidth/2) * scene.camera.viewportWidth / (double)(bufferWidth/2);
				double yPos = (double)(y - bufferHeight/2) * scene.camera.viewportHeight / (double)(bufferHeight/2);
				Vector3 cameradir = new Vector3(xPos, yPos, 0).subtract(scene.camera.position).normalize();
				depthBuffer[x][y] = Double.MAX_VALUE;
				
				for(RenderObject object : scene.renderObjects)
				{
					double depth = object.intersectionDepth(cameradir, scene.camera.position);
					if (depth > 0) {depthBuffer[x][y] = Math.min(depthBuffer[x][y], depth);}
				}
			}
		}
		
		for (int x = 0; x < bufferWidth; x++)
		{
			for (int y = 0; y < bufferHeight; y++)
			{
				double xPos = (double)(x - bufferWidth/2) * scene.camera.viewportWidth / (double)(bufferWidth/2);
				double yPos = (double)(y - bufferHeight/2) * scene.camera.viewportHeight / (double)(bufferHeight/2);
				Vector3 cameradir = new Vector3(xPos, yPos, 0).subtract(scene.camera.position).normalize();
				
				Vector3 color = new Vector3();
				
				for(RenderObject object : scene.renderObjects)
				{
					
					double depth = object.intersectionDepth(cameradir, scene.camera.position);
					Vector3 intersection = scene.camera.position.add(cameradir.scale(depth));
                    
					if(depth <= 0) continue;
                    if(depth > depthBuffer[x][y] + 0.001) continue;
                    
                    for(Light light : scene.lights)
                    {
                        //double attenuation = 4.0 / (1.0 + 0.1 * distance + 0.05 * distance * distance); //attenuators should be placed in lights
                        Vector3 lightDirection = light.directionAtPoint(intersection).negative().normalize(); //points away from surface
                        Vector3 cameraDirection = scene.camera.position.subtract(intersection).normalize();
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
                if(renderViewer != null) renderViewer.pixelRendered();
                
			}
            if(renderViewer != null) renderViewer.lineRendered();
		}
        
    }
    
    public void setScene(Scene _scene)
    {
        
        scene = _scene;
        
    }

}