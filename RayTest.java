import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;

public class RayTest
{
    
    //temporary loophole workaround for UI
    //this main file needs better organization
    private static JLabel imageLabel;
    
	public static void main(String[] args) throws IOException
	{
		int width = 640, height = 480;
		
		Vector3 camerapos = new Vector3(0, 0, -5);
        
		double viewportHeight = 5;
        double aspectRatio = (double)width/(double)height;
        double viewportWidth = viewportHeight*aspectRatio;
        
        Camera camera = new Camera(camerapos, viewportWidth, viewportHeight);
				
		ArrayList<RenderObject> renderObjects = new ArrayList<RenderObject>();
		renderObjects.add(new Sphere(new Vector3(1, 1 , 10), 5));
        renderObjects.add(new Sphere(new Vector3(-5, -4 , 7), 3.5));
		renderObjects.add(new Sphere(new Vector3(0, -1, 15), 5));
		renderObjects.add(new Triangle(new Vector3(0, 10, 10), new Vector3(20, 0, 20), new Vector3(-20, 0, 20)));
        
        ArrayList<Light> lights = new ArrayList<Light>();
        lights.add(new PointLight(new Vector3(0, -10, 0), new Vector3(.5, 1, .5), 100));
        lights.add(new PointLight(new Vector3(5, 10, 0), new Vector3(1, .5, .5), 100));
        lights.add(new PointLight(new Vector3(-5, 5, 0), new Vector3(.5, .5, 1), 100));
        
        Scene scene = new Scene(camera);
        scene.setRenderObjects(renderObjects);
        scene.setLights(lights);
        
        RayTracer bob = new RayTracer(width, height, scene);
        bob.renderViewer = new VIURenderViewer();
        
        //rudimentary java swing ui for viewing the output image. nothing realtime yet. should be placed into a separate class
        JFrame window = new JFrame("Image Output");
        window.setSize(width, height);
        window.setResizable(false);
        window.addWindowListener(new VIUWindowManager());
        window.setVisible(true);
        
        imageLabel = new JLabel(new ImageIcon(bob.buffer));
        window.add(imageLabel);
        
        bob.renderImage();
        
        imageLabel.updateUI();
		
		saveImage(bob.buffer, "RayTest.png");
		
	}
    
    public static void updateGUI()
    {
        
        imageLabel.updateUI();
        
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

class VIUWindowManager extends WindowAdapter
{
    
    public void windowClosing(WindowEvent event)
    {
        
        System.exit(0);
        
    }
    
}

class VIURenderViewer implements RenderViewer
{
    
    public void pixelRendered()
    {
        
        //RayTest.updateGUI();
        
    }
    
    public void lineRendered()
    {
        
        RayTest.updateGUI();
        
    }
    
}