import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.util.ArrayList;
import javax.swing.*;

public class RayTest
{
    
	public static void main(String[] args) throws IOException
	{
		int width = 640, height = 480;
		
		Vector3 camerapos = new Vector3(0, 0, -5);
        
		double viewportHeight = 5;
				
		ArrayList<RenderObject> renderObjects = new ArrayList<RenderObject>();
		renderObjects.add(new Sphere(new Vector3(1, 1 , 10), 5));
        renderObjects.add(new Sphere(new Vector3(-5, -4 , 7), 3.5));
		renderObjects.add(new Sphere(new Vector3(0, -1, 15), 5));
		renderObjects.add(new Triangle(new Vector3(0, 10, 10), new Vector3(20, 0, 20), new Vector3(-20, 0, 20)));
        
        ArrayList<Light> lights = new ArrayList<Light>();
        lights.add(new PointLight(new Vector3(0, -10, 0), new Vector3(.5, 1, .5), 100));
        lights.add(new PointLight(new Vector3(5, 10, 0), new Vector3(1, .5, .5), 100));
        lights.add(new PointLight(new Vector3(-5, 5, 0), new Vector3(.5, .5, 1), 100));
        
        RayTracer bob = new RayTracer(width, height, viewportHeight);
        bob.setRenderObjects(renderObjects);
        bob.setLights(lights);
        bob.setCamera(camerapos);
        
        //rudimentary java swing ui for viewing the output image. nothing realtime yet.
        JFrame window = new JFrame("Image Output");
        window.setSize(width, height);
        window.setResizable(false);
        window.addWindowListener(new VIUWindowManager());
        window.setVisible(true);
        
        JLabel imageLabel = new JLabel(new ImageIcon(bob.buffer));
        window.add(imageLabel);
        
        bob.renderImage();
        
        imageLabel.updateUI();
		
		saveImage(bob.buffer, "RayTest.png");
		
	}
	
	public static boolean saveImage(BufferedImage image, String fileName)
	{	
		try 
		{
			File file = new File(fileName);
			ImageIO.write(image, "png", file);
			return true;
		}
		catch(IOException e) 
		{
			System.out.println("Unable to write image, sorry.");
			return false;
		}
	}
	
}